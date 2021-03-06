package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.enums.TaskStateEnum;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.DateUtil;
import com.xsl.weChat.common.util.GsonSingle;
import com.xsl.weChat.common.util.UUIdTaskIdUtil;
import com.xsl.weChat.service.TaskInfoService;
import com.xsl.weChat.service.XslTaskService;
import com.xsl.wechat.dto.XslTaskDTO;
import com.xsl.wechat.dto.XslTaskDetailDTO;
import com.xsl.wechat.mapper.*;
import com.xsl.wechat.pojo.*;
import com.xsl.wechat.vo.MyAcceptTaskVo;
import com.xsl.wechat.vo.TagVo;
import com.xsl.wechat.vo.XslTaskReqVo;
import com.xsl.wechat.vo.XslTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 任务层
 *
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/18 19:53
 */
@Service
public class XslTaskServiceImpl implements XslTaskService {

    @Autowired
    private XslTaskMapper xslTaskMapper;

    @Autowired
    private XslTaskAreaMapper xslTaskAreaMapper;

    @Autowired
    private XslFileMapper xslFileMapper;

    @Autowired
    private XslUserMapper xslUserMapper;

    @Autowired
    private XslHunterTaskMapper xslHunterTaskMapper;

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private XslTaskTagMapper xslTaskTagMapper;

    @Autowired
    private XslTagMapper xslTagMapper;

    @Autowired
    private XslTaskFileMapper xslTaskFileMapper;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private static final Logger logger = LoggerFactory.getLogger(XslTaskServiceImpl.class);

    /**
     * 分页获取微信所发布任务的列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public XslResult getTaskList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("pageNum", pageNum - 1);
        map.put("pageSize", pageSize);
        List<XslTask> xslTaskList = xslTaskMapper.getTaskList(map);
        // 当前页码
        if (xslTaskList == null && xslTaskList.size() == 0) {
            logger.info("pageNum is {}", pageNum + "and pageSize is {}" + pageSize);
            return XslResult.build(-1, "无任务数据");
        }
        try {
            List<XslTaskVo> xslTaskVoList = new ArrayList<XslTaskVo>(10);
            //补全信息
            for (XslTask xslTask : xslTaskList) {
                //type=1为补全分页任务信息
                XslTaskVo xslTaskVo = supplementXslTakVo(1,xslTask);
                xslTaskVoList.add(xslTaskVo);
            }
            return XslResult.build(1, "正常", xslTaskVoList);
        } catch (BeansException e) {
            logger.error(e.getMessage());
        }
        return XslResult.build(-1, "服务器异常");
    }

    /**
     * 发布任务
     * @param xslTaskReqVo
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)
    @Override
    public XslResult issueTask(XslTaskReqVo xslTaskReqVo) {
        try {
            if (xslTaskReqVo == null) {
                return XslResult.build(-1, "取得数据为空");
            }
            int size = 4;
            if (xslTaskReqVo.getIssueImg().size()>size){
                return XslResult.build(-1,"存储图片不能超过4张");
            }
            String taskId = UUIdTaskIdUtil.getUUID();
            initTaskArea(xslTaskReqVo, taskId);
            writeTask(xslTaskReqVo, taskId);
            List<XslFile> xslFiles = initXslFile(xslTaskReqVo);
            xslFiles.forEach(xslFile -> insertTaskFile(xslFile,taskId));
            XslResult xslResult = addTaskTags(xslTaskReqVo,taskId);
            if(!xslResult.isOK()){
                logger.info("【添加任务标签】添加任务标签失败 xslResult = {}",xslResult);
                return XslResult.build(-1,"标签添加失败");
            }

            return XslResult.build(1, "正常",taskId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("XslTaskServiceImpl.issueTask exception, the param:{}, exception:{}", GsonSingle.getGson().toJson(xslTaskReqVo), e);
            return XslResult.build(500,"服务器异常");
        }
    }

    /**
     * 得到我发布我过的任务
     * @param sendId
     * @return
     */
    @Override
    public XslResult getMyIssueTask(String sendId) {
        List<XslTask> xslTaskList = xslTaskMapper.getMyTaskList(xslTaskMapper.getMasterIdByOpenId(sendId));
        if (xslTaskList == null&&xslTaskList.size()<1) {
            logger.info("获取我的任务列表失败");
            return XslResult.build(-1, "对不起您还未发布任何任务");
        }
        try {
            List<XslTaskDTO> xslTaskVoList = new ArrayList<XslTaskDTO>(15);
            for (XslTask xslTask : xslTaskList) {
                XslTaskDTO xslTaskDTO = new XslTaskDTO();
                XslTaskArea xslTaskArea = xslTaskAreaMapper.getTaskMsgByTaskId(xslTask.getTaskId());
                xslTaskDTO.setId(xslTask.getTaskId());
                xslTaskDTO.setTitle(xslTask.getTaskTitle());
                xslTaskDTO.setAddress(xslTaskArea.getAddress());
                xslTaskDTO.setAddressNoun(xslTaskArea.getAddressNoun());
                xslTaskDTO.setIssueTime(DateUtil.dateToString(xslTask.getCreateDate()));
                xslTaskDTO.setMissionState(xslTask.getState());
                xslTaskDTO.setMoney(xslTask.getMoney().toString());
                xslTaskDTO.setType(xslTaskMapper.getCategoryNameByCid(xslTask.getCid()));
                xslTaskVoList.add(xslTaskDTO);
            }
            if (xslTaskVoList.size() != 0) {
                return XslResult.build(1, "正常", xslTaskVoList);
            }
        } catch (Exception e) {
            logger.error("服务器异常信息："+e.getMessage());
        }
        return XslResult.build(-1, "服务器异常");
    }

    /**
     * 得到我接受过的任务
     * @param userId
     * @return
     */
    @Override
    public XslResult getMyAcceptTask(String userId) {

        List<XslHunterTask> xslHunterTasks = xslTaskMapper.getMyAcceptTask(xslTaskMapper.getHunterIdByOpenId(userId));
        if(xslHunterTasks == null||xslHunterTasks.size()==0){
            return XslResult.build(403,"您还没有接受任何任务");
        }
        try {
            List<MyAcceptTaskVo> myAcceptTaskVos = new ArrayList<MyAcceptTaskVo>(15);
            for(XslHunterTask xslHunterTask:xslHunterTasks){
                XslTask xslTask = xslTaskMapper.getTaskByTasKId(xslHunterTask.getTaskId());
                MyAcceptTaskVo myAcceptTaskVo = supplementMyAcceptTaskVo(xslTask);
                myAcceptTaskVo.setMissionState(xslHunterTask.getTaskState());
                myAcceptTaskVos.add(myAcceptTaskVo);
            }
            if(xslHunterTasks.size()>0){
                return XslResult.build(1,"正常",myAcceptTaskVos);
            }
        }catch (Exception e){
            logger.error("服务器异常警告:"+e.getMessage());
            return XslResult.build(-1,"服务器异常");
        }
        return XslResult.build(-1,"服务器异常");
    }

    /**
     * 任务详情
     * @param taskId
     * @return
     */
    @Override
    public XslResult getTaskDetail(String taskId) {
        if(StringUtils.isEmpty(taskId)){
            return XslResult.build(403,"任务id为空");
        }
        try {
            XslTask xslTask = xslTaskMapper.getTaskByTasKId(taskId);
            XslTaskDetailDTO xslTaskDetailDTO = supplementXslTaskDetail(xslTask);
            if(xslTaskDetailDTO!=null){
                return XslResult.build(1,"正常",xslTaskDetailDTO);
            }
        }catch (Exception e){
            logger.error("服务器异常警告:"+e.getMessage());
            return XslResult.build(-1,"服务器异常");
        }
        return XslResult.build(-1,"服务器异常");
    }

    /**
     * 补全任务详情信息
     * @param xslTask
     * @return
     */
    private XslTaskDetailDTO supplementXslTaskDetail(XslTask xslTask){
        XslTaskDetailDTO xslTaskDetailDTO = new XslTaskDetailDTO();
        XslTaskVo xslTaskVo = supplementXslTakVo(2,xslTask);
        BeanUtils.copyProperties(xslTaskVo,xslTaskDetailDTO);
        xslTaskDetailDTO.setMissionState(xslTask.getState());
        xslTaskDetailDTO.setMissionDetail(xslTask.getContent());
        //得到任务图片
        xslTaskDetailDTO.setIssueImg(taskInfoService.getTaskImages(xslTask.getTaskId()));
        logger.info("sign="+xslTask.getState().equals(TaskStateEnum.TASK_ONGOING));
        //进行状态判断
        if(xslTask.getState().equals(TaskStateEnum.TASK_ONGOING.getState())){
            String hunterId = xslHunterTaskMapper.getSendIdByTaskId(xslTask.getTaskId());
            XslUserExample example = new XslUserExample();
            XslUserExample.Criteria criteria = example.createCriteria();
            criteria.andHunteridEqualTo(hunterId);
            List<XslUser> xslUsers = xslUserMapper.selectByExample(example);
            if (xslUsers!=null&&xslUsers.size()>0){
                xslTaskDetailDTO.setAcceptUserName(xslUsers.get(0).getName());
                XslFileExample fileExample = new XslFileExample();
                XslFileExample.Criteria fileExampleCriteria = fileExample.createCriteria();
                fileExampleCriteria.andFileidEqualTo(xslUsers.get(0).getUserid());
                List<XslFile> list = xslFileMapper.selectByExample(fileExample);
                xslTaskDetailDTO.setAcceptUserAvatarUrl(list.get(0).getUrl());
                xslTaskDetailDTO.setPhoneNum(xslUsers.get(0).getPhone());
            }
            xslTaskDetailDTO.getAcceptUserName();
        }
        return xslTaskDetailDTO;
    }

    private XslTaskDTO supplementXskTaskDTO(XslTask xslTask){
        XslTaskDTO xslTaskDTO = new XslTaskDTO();
        XslTaskVo xslTaskVo = supplementXslTakVo(2,xslTask);
        BeanUtils.copyProperties(xslTaskVo,xslTaskDTO);
        xslTaskDTO.setId(xslTask.getTaskId());
        return xslTaskDTO;
    }

    private MyAcceptTaskVo supplementMyAcceptTaskVo(XslTask xslTask){
        MyAcceptTaskVo myAcceptTaskVo = new MyAcceptTaskVo();
        XslTaskDTO xslTaskDTO = supplementXskTaskDTO(xslTask);
        BeanUtils.copyProperties(xslTaskDTO,myAcceptTaskVo);
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andMasteridEqualTo(xslTask.getSendId());
        List<XslUser> list = xslUserMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            myAcceptTaskVo.setIssueUserName(list.get(0).getName());
            myAcceptTaskVo.setIssueUserPhoneNum(list.get(0).getPhone());
        }
        myAcceptTaskVo.setIssueUserAvatarUrl(xslTaskMapper.getImageBySendId(xslTask.getSendId()));
        return myAcceptTaskVo;
    }

    /**
     * 补全信息
     * @param type 1为分页查询
     * @param xslTask
     * @return
     */
    private XslTaskVo supplementXslTakVo(int type,XslTask xslTask) {
        XslTaskVo xslTaskVo = new XslTaskVo();
        XslTaskArea xslTaskArea = xslTaskAreaMapper.getTaskMsgByTaskId(xslTask.getTaskId());
        xslTaskVo.setType(xslTaskMapper.getCategoryNameByCid(xslTask.getCid()));
        xslTaskVo.setNickName(xslTaskMapper.getUerNameBySendId(xslTask.getSendId()));
        xslTaskVo.setAvatarUrl(xslTaskMapper.getImageBySendId(xslTask.getSendId()));
        xslTaskVo.setMoney(xslTask.getMoney().toString());
        xslTaskVo.setIssueTime(DateUtil.dateToString(xslTask.getCreateDate()));
        xslTaskVo.setUserId(xslTaskMapper.getMasterByOpenId(xslTask.getSendId()));
        xslTaskVo.setTitle(xslTask.getTaskTitle());
        if(type==1){
            xslTaskVo.setTagVos(getTagVos(xslTask.getTaskId()));
        }
        BeanUtils.copyProperties(xslTaskArea, xslTaskVo);
        xslTaskVo.setTaskId(xslTask.getTaskId());
        return xslTaskVo;
    }

    private void initTaskArea(XslTaskReqVo xslTaskReqVo, String taskId) {
        XslTaskArea xslTaskArea = new XslTaskArea();
        xslTaskArea.setAddress(xslTaskReqVo.getAddress());
        xslTaskArea.setTaskId(taskId);
        xslTaskArea.setAddressNoun(xslTaskReqVo.getAddressNoun());
        xslTaskArea.setCreateDate(new Date());
        xslTaskArea.setUpdateDate(new Date());
        try {

            int result = xslTaskAreaMapper.insertTaskArea(xslTaskArea);

            if (result < 1) {
                throw new RuntimeException("猎人信息插入失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    private void writeTask(XslTaskReqVo xslTaskReqVo, String taskId) {
        XslTask xslTask = new XslTask();
        xslTask.setTaskId(taskId);
        xslTask.setTaskTitle(xslTaskReqVo.getTitle());
        xslTask.setSendId(xslTaskMapper.getMasterIdByOpenId(xslTaskReqVo.getUserId()));
        xslTask.setCid(xslTaskMapper.getCidByTypeName(xslTaskReqVo.getType()));
        xslTask.setState((byte) 0);
        xslTask.setMoney(new BigDecimal(xslTaskReqVo.getMoney()));
        xslTask.setCreateDate(DateUtil.stringToDate(xslTaskReqVo.getIssueTime()));
        xslTask.setUpdateDate(new Date());
        Date deadline = DateUtil.stringToDate(xslTaskReqVo.getIssueTime());
        deadline.setTime(deadline.getTime() + 48*60*60*1000);
        xslTask.setDeadline(deadline);
        xslTask.setSourceType(xslTaskReqVo.getSourceType());
        xslTask.setContent(xslTaskReqVo.getMissionDetail());
        try {
            int result = xslTaskMapper.insertTask(xslTask);

            if (result < 1) {
                throw new RuntimeException("任务信息插入失败");
            }
            xslTaskMapper.increaseTaskNum(xslTaskReqVo.getType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    /**
     * 初始化任务图片
     * @param xslTaskReqVo
     */
    private List<XslFile> initXslFile(XslTaskReqVo xslTaskReqVo){
        try {
            List<String> images = xslTaskReqVo.getIssueImg();
            List<XslFile> xslFiles = new ArrayList<>();
            for(String image : images){
                //初始化文件信息
                XslFile xslFile = new XslFile();
                xslFile.setFileid(UUID.randomUUID().toString());
                xslFile.setUrl(image);
                int result = xslFileMapper.insertSelective(xslFile);
                if (result < 1){
                    throw new RuntimeException("任务图片存储失败");
                }
                xslFiles.add(xslFile);
            }
            return xslFiles;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

	private XslResult insertTaskFile(XslFile xslFile,String taskId){

		if(StringUtils.isEmpty(taskId)){
			logger.info("任务id为空");
			return XslResult.build(403,"参数错误");
		}
		XslTaskFile xslTaskFile = new XslTaskFile();
		xslTaskFile.setTaskId(taskId);
		xslTaskFile.setFileId(xslFile.getFileid());
		xslTaskFile.setType("weChat");
		xslTaskFile.setCreateDate(new Date());
		xslTaskFile.setUpdateDate(new Date());
		try {
			int result = xslTaskFileMapper.insetTaskFile(xslTaskFile);
			if(result<1){
				return XslResult.build(500,"任务文件关联信息插入失败");
			}
			return XslResult.ok();
		}catch (Exception e){
		    e.printStackTrace();
			logger.error("XslTaskServiceImpl.insertTaskFile exception, the param:{}, exception:{}", GsonSingle.getGson().toJson(xslFile), e);
			return XslResult.build(500,"服务器异常");
		}
	}

    /**
     * 更新任务标签使用数
     * @param tags
     * @return
     */
    private XslResult updateTagNum(List<TagVo> tags){
        List<String> tagIds = new ArrayList<>(tags.size());
        for (TagVo tagVo : tags){
            tagIds.add(tagVo.getTagId());
        }

        XslTagExample xslTagExample = new XslTagExample();
        XslTagExample.Criteria criteria = xslTagExample.createCriteria();
        criteria.andTagidIn(tagIds);
        int i = xslTagMapper.updateUseNumByExample(xslTagExample);
        if(i < 1){
            throw new RuntimeException();
        }
        return XslResult.ok();
    }

    /**
     * 添加任务标签
     * @param xslTaskReqVo
     * @param taskId
     * @return
     */
    private XslResult addTaskTags(final XslTaskReqVo xslTaskReqVo, String taskId){
        try {
            List<TagVo> tagVos = xslTaskReqVo.getTagVos();
            if (tagVos.size() < 1){
                return XslResult.ok();
            }

            List<XslTaskTag> xslTaskTags = new ArrayList<XslTaskTag>();
            for(TagVo tagVo : tagVos){
                XslTaskTag xslTaskTag = new XslTaskTag();
                xslTaskTag.setTaskid(taskId);
                xslTaskTag.setTagid(tagVo.getTagId());
                xslTaskTags.add(xslTaskTag);
            }
            int i = xslTaskTagMapper.insertSelectiveBatch(xslTaskTags);

            if(i<xslTaskTags.size()){
                throw new RuntimeException("插入任务标签关联表失败");
            }
            //异步去处理标签使用的次数
            taskExecutor.execute(() -> updateTagNum(xslTaskReqVo.getTagVos()));
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"服务异常");
        }
        return XslResult.ok();
    }

    private List<TagVo> getTagVos(String taskId){
        if(StringUtils.isEmpty(taskId)){
            throw new RuntimeException("任务不存在");
        }
        try {
            List<String> tagIds = xslTaskTagMapper.getTagIdsByTaskId(taskId);
            if(tagIds==null&&tagIds.size()<1){
                return new ArrayList<TagVo>();
            }
            List<TagVo> tagVoList = new ArrayList<>();
            for(String tagId:tagIds){
                TagVo tagVo = new TagVo();
                tagVo.setTagId(tagId);
                String name = xslTagMapper.getTagNameByTagId(tagId);
                tagVo.setName(name);
                tagVoList.add(tagVo);
            }
            return tagVoList;
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("服务器异常");
        }
    }
}

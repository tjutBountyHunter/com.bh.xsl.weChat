package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.DateUtil;
import com.xsl.weChat.common.util.UUIdTaskIdUtil;
import com.xsl.weChat.service.XslTaskService;
import com.xsl.wechat.dto.XslTaskDTO;
import com.xsl.wechat.mapper.XslTaskAreaMapper;
import com.xsl.wechat.mapper.XslTaskMapper;
import com.xsl.wechat.pojo.XslTask;
import com.xsl.wechat.pojo.XslTaskArea;
import com.xsl.wechat.vo.XslTaskReqVo;
import com.xsl.wechat.vo.XslTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private static final Logger logger = LoggerFactory.getLogger(XslTaskServiceImpl.class);

    public XslResult getTaskList(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("pageNum", pageNum - 1);
        map.put("pageSize", pageSize);
        List<XslTask> xslTaskList = xslTaskMapper.getTaskList(map);
        // 当前页码
        if (xslTaskList == null && xslTaskList.size() == 0) {
            logger.info("pageNum is {}", pageNum + "and pageSize is {}" + pageSize);
            return XslResult.build(-1, "服务器异常");
        }
        try {
            List<XslTaskVo> xslTaskVoList = new ArrayList<XslTaskVo>(10);
            //补全信息
            for (XslTask xslTask : xslTaskList) {
                XslTaskVo xslTaskVo = supplementXslTakVo(xslTask);
                xslTaskVoList.add(xslTaskVo);
            }
            return XslResult.build(1, "正常", xslTaskVoList);
        } catch (BeansException e) {
            logger.error(e.getMessage());
        }
        return XslResult.build(-1, "服务器异常");
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public XslResult issueTask(XslTaskReqVo xslTaskReqVo) {
        if (xslTaskReqVo == null) {
            return XslResult.build(-1, "取得数据为空");
        }
        try {

            String taskId = UUIdTaskIdUtil.getUUID();
            initTaskArea(xslTaskReqVo, taskId);
            writeTask(xslTaskReqVo, taskId);
            return XslResult.build(1, "正常", xslTaskReqVo.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return XslResult.build(-1, "服务器异常");
        }
    }

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
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return XslResult.build(-1, "服务器异常");
    }

    private XslTaskVo supplementXslTakVo(XslTask xslTask) {
        XslTaskVo xslTaskVo = new XslTaskVo();
        XslTaskArea xslTaskArea = xslTaskAreaMapper.getTaskMsgByTaskId(xslTask.getTaskId());
        xslTaskVo.setType(xslTaskMapper.getCategoryNameByCid(xslTask.getCid()));
        xslTaskVo.setNickName(xslTaskMapper.getUerNameBySendId(xslTask.getSendId()));
        xslTaskVo.setAvatarUrl(xslTaskMapper.getImageBySendId(xslTask.getSendId()));
        xslTaskVo.setMoney(xslTask.getMoney().toString());
        xslTaskVo.setIssueTime(DateUtil.dateToString(xslTask.getCreateDate()));
        xslTaskVo.setUserId(xslTaskMapper.getMasterByOpenId(xslTask.getSendId()));
        xslTaskVo.setTitle(xslTask.getTaskTitle());
        BeanUtils.copyProperties(xslTaskArea, xslTaskVo);
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
        xslTask.setSendId(xslTaskMapper.getMasterByOpenId(xslTaskReqVo.getUserId()));
        xslTask.setCid(xslTaskMapper.getCidByTypeName(xslTaskReqVo.getType()));
        xslTask.setState((byte) 0);
        xslTask.setMoney(new BigDecimal(xslTaskReqVo.getMoney()));
        xslTask.setCreateDate(DateUtil.stringToDate(xslTaskReqVo.getIssueTime()));
        xslTask.setUpdateDate(new Date());
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

}

package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.XslTaskService;
import com.xsl.wechat.mapper.XslTaskMapper;
import com.xsl.wechat.pojo.XslTask;
import com.xsl.wechat.vo.XslTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务层
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/18 19:53
 */
@Service
public class XslTaskServiceImpl implements XslTaskService {

    @Autowired
    private XslTaskMapper xslTaskDao;

    private static final Logger logger = LoggerFactory.getLogger(XslTaskServiceImpl.class);

    public XslResult getTaskList(Integer pageNum, Integer pageSize) {
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        List<XslTask> xslTaskList = xslTaskDao.getTaskList(map);
        // 当前页码
        if(xslTaskList==null){
            logger.info("pageNum is {}",pageNum+"and pageSize is {}"+ pageSize);
            return XslResult.build(-1,"服务器异常");
        }
        try {
            List<XslTaskVo> xslTaskVoList = new ArrayList<XslTaskVo>(10);
            //补全信息
            for(XslTask xslTask :xslTaskList){
                XslTaskVo xslTaskVo = new XslTaskVo();
                xslTaskVo.setType(xslTaskDao.getCategoryNameByCid(xslTask.getCid()));
                xslTaskVo.setName(xslTaskDao.getUerNameBySendId(xslTask.getSendId()));
                xslTaskVo.setAvatarUrl(xslTaskDao.getImageBySendId(xslTask.getSendId()));
                BeanUtils.copyProperties(xslTask,xslTaskVo);
                xslTaskVoList.add(xslTaskVo);
            }
            return XslResult.build(1,"正常",xslTaskList);
        } catch (BeansException e) {
            logger.error(e.getMessage());
        }
        return XslResult.build(-1,"服务器异常");
    }
}

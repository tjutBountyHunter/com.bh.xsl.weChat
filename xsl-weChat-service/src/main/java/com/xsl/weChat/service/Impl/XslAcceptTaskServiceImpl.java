package com.xsl.weChat.service.Impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.mapper.XslHunterTaskMapper;
import com.xsl.weChat.mapper.XslTaskMapper;
import com.xsl.weChat.service.XslAcceptTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class XslAcceptTaskServiceImpl implements XslAcceptTaskService {
    public static final String taskState= "2";
    @Autowired
    private XslTaskMapper TaskDao;
    @Autowired
    private XslHunterTaskMapper HunterTaskDao;
    private static final Logger logger = LoggerFactory.getLogger(XslAcceptTaskServiceImpl.class);


    public XslResult AcceptTask(String userId, String taskId) {

        Map<String,String> map=new HashMap<String,String>();
      map.put("hunterId",userId);
      map.put("taskId",taskId);
      map.put("taskState",taskState);
      try {

          HunterTaskDao.updateHunterTask(map);
          TaskDao.alterTaskState(map);
          return XslResult.build(1,"接受成功");
      }
      catch (Exception e){
          logger.error(e.getMessage());
          System.out.println(e);


      }

        return XslResult.build(-1,"服务器异常");




    }
}

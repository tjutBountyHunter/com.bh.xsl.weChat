package com.xsl.weChat.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xsl.weChat.common.util.GsonSingle;
import com.xsl.weChat.common.util.JedisClientUtil;
import com.xsl.weChat.service.TaskInfoService;
import com.xsl.wechat.mapper.XslFileMapper;
import com.xsl.wechat.mapper.XslTaskFileMapper;
import com.xsl.wechat.pojo.XslFile;
import com.xsl.wechat.pojo.XslFileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/25 15:55
 */

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    private XslTaskFileMapper xslTaskFileMapper;

    @Autowired
    private XslFileMapper xslFileMapper;

    @Value("${TASK_IMAGE_URL}")
    private String TASK_IMAGE_URL;

    @Override
    public List<String> getTaskImages(String taskId) {
        Gson gson = GsonSingle.getGson();
        String taskUrl = JedisClientUtil.get(TASK_IMAGE_URL + ":" + taskId);
        if(!StringUtils.isEmpty(taskUrl)){
            return gson.fromJson(taskUrl,new TypeToken<List<String>>() {}.getType());
        }
        List<String> fileIds = xslTaskFileMapper.selectTaskImage(taskId,"weChat");

        if(fileIds!=null&&fileIds.size()>0){
            XslFileExample xslFileExample = new XslFileExample();
            xslFileExample.createCriteria().andFileidIn(fileIds);
            List<XslFile> xslFiles = xslFileMapper.selectByExample(xslFileExample);
            List<String> taskImageList = xslFiles.stream().map(xslFile -> xslFile.getUrl()).collect(Collectors.toList());
            if(taskImageList!=null&&taskImageList.size()>0){
                String json = gson.toJson(taskImageList);
                JedisClientUtil.setEx(TASK_IMAGE_URL+":"+taskId,json,300);
                return taskImageList;
            }
        }
        return new ArrayList<>();
    }
}

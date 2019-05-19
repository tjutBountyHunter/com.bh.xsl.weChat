package com.xsl.weChat.service.impl;

import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.service.HunterRecommend;
import com.xsl.wechat.mapper.XslHunterTagMapper;
import com.xsl.wechat.mapper.XslTaskTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/19 20:41
 */
@Service
public class HunterRecommendImpl implements HunterRecommend {

    @Autowired
    private XslTaskTagMapper xslTaskTagMapper;

    @Autowired
    private XslHunterTagMapper xslHunterTagMapper;

    private HashSet<String> hunters;

    @Override
    public XslResult matchingHunter(String taskId) {
        try {
            hunters = new HashSet<>();
            List<String> tagIds = xslTaskTagMapper.getTagIdsByTaskId(taskId);
            getHunters(tagIds,4);
            if(hunters.size()<1){
                return XslResult.build(403,"无与您的人任务匹配的猎人");
            }
            return XslResult.ok(hunters);
        }catch (Exception e){
            throw new RuntimeException("服务器异常");
        }
    }

    private void getHunters(List<String> tagIds, int size){
        if(tagIds.size()<1){
            return;
        }
        List<String> hunterIds = null;
        if(tagIds.size()==1){
            hunterIds = xslHunterTagMapper.getHunterIdsByTagId(tagIds.get(0));
            addHunter(hunterIds,size);
        }
        int matchSize = 2;
        if(tagIds.size()==matchSize){
            hunterIds = xslHunterTagMapper.getHunterByTagIds(tagIds);
            addHunter(hunterIds,size);
        }
        if(tagIds.size()>matchSize){
            List<String> newTagList = new ArrayList<>();
            newTagList.add(tagIds.get(0));
            newTagList.add(tagIds.get(1));
            hunterIds = xslHunterTagMapper.getHunterByTagIds(tagIds);
            addHunter(hunterIds,size);
        }
    }

    private void addHunter(List<String> hunterIds,int size){
        if(hunterIds==null&&hunterIds.size()<1){
            return;
        }
        if(hunterIds.size()<size){
            for(String hunter : hunterIds){
                hunters.add(hunter);
            }
            return;
        }
        while(true) {
            hunters.add(hunterIds.get((int) (Math.random() * hunterIds.size())));
            if (hunters.size() >= 4) {
                return;
            }
        }
    }
}

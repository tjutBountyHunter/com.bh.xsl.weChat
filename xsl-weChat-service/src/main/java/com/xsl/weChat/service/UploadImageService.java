package com.xsl.weChat.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/25 0:35
 */
public interface UploadImageService {

    /**
     *  图片上传
     * @param uploadFile
     * @return
     */
    Map uploadPicture(MultipartFile uploadFile);

}

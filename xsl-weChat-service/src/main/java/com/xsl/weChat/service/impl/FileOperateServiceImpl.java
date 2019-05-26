package com.xsl.weChat.service.impl;

import com.xsl.user.FileOperateResource;
import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.FileVo;
import com.xsl.weChat.common.pojo.XslResult;
import com.xsl.weChat.common.util.GsonSingle;
import com.xsl.weChat.service.FileOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Service
public class FileOperateServiceImpl implements FileOperateService {


	@Resource
    private FileOperateResource fileOperateResource;

	private static final Logger logger = LoggerFactory.getLogger(FileOperateServiceImpl.class);

	/**
	 * 上传图片
	 *
	 * @param uploadFile
	 * @return
	 */
	@Override
	public XslResult fileUpload(MultipartFile uploadFile) {

	    try {
            FileUploadReqVo fileUploadReqVo = new FileUploadReqVo();
            fileUploadReqVo.setName(uploadFile.getName());
            fileUploadReqVo.setBytes(uploadFile.getBytes());
            fileUploadReqVo.setContentType(uploadFile.getContentType());
            fileUploadReqVo.setInputStream(uploadFile.getBytes());
            fileUploadReqVo.setOriginalFilename(uploadFile.getOriginalFilename());
            fileUploadReqVo.setSize(uploadFile.getSize());
            FileVo fileVo = fileOperateResource.fileUpload(fileUploadReqVo);
            if(fileVo.getStatus() != 200){
                return XslResult.build(fileVo.getStatus(), fileVo.getMsg());
            }

            return XslResult.ok(fileVo.getUrl());
        }catch (Exception e){
	        e.printStackTrace();
	        logger.error("FileOperateServiceImpl.fileUpload exception, the param:{}, exception:{}", GsonSingle.getGson().toJson(uploadFile), e);
            return XslResult.build(500,"服务器异常");
        }

	}
}

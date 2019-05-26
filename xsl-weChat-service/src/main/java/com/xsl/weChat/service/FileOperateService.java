package com.xsl.weChat.service;

import com.xsl.weChat.common.pojo.XslResult;
import org.springframework.web.multipart.MultipartFile;


public interface FileOperateService {

	/**
	 * 上传图片
	 *
	 * @param uploadFiles
	 * @return
	 */
	XslResult fileUpload(MultipartFile uploadFiles);
}

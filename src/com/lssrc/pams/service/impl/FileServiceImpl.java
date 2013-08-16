/**
 * FileServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2013-01-09
 */
package com.lssrc.pams.service.impl;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.lssrc.pams.service.FileService;
import com.lssrc.pams.util.UtilFactory;

@Service("fileService")
public class FileServiceImpl implements FileService {

	public boolean upload(File uploadFile, String filePath, String fileName) {
		try {
			UtilFactory.getFileUtil().uploadFile(uploadFile, ServletActionContext.getServletContext().getRealPath("/") + filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

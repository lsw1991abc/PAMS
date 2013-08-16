/**
 * FileAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-08
 */
package com.lssrc.pams.action;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.service.FileService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("fileAction")
@Scope("prototype")
public class FileAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private File file;
	private String fileContentType;
	private String fileFileName;
	private String filePath;

	private FileService fileService;

	private String msg;

	public String upload() {
		if (filePath == null) {
			filePath = "upload";
		}
		if (fileService.upload(file, filePath, this.getFileFileName())) {
			msg = "[{result:'上传成功'}]";
		} else {
			msg = "[{result:'上传失败'}]";
		}
		return SUCCESS;
	}

	public String read() {
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Resource
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

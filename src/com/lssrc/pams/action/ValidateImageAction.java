/**
 * IndexAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-19
 */
package com.lssrc.pams.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.util.ImageUtil;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("validateImageAction")
@Scope("prototype")
public class ValidateImageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private InputStream inputStream;

	private int length = 4;
	private int width = 80;
	private int height = 35;

	public String execute() {
		ImageUtil imageUtil = UtilFactory.getImageUtil();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
			ImageIO.write(imageUtil.getValidateImage(width, height, length), "JPEG", imageOutputStream);
			inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("validateString", imageUtil.getValidateString().toLowerCase());
		return "success";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
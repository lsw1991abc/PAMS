/**
 * FileService.java
 * 
 * 
 * lssrc.com
 * 2013-01-09
 */
package com.lssrc.pams.service;

import java.io.File;

public interface FileService {
	
	public boolean upload(File uploadFile, String filePath, String fileName);
	
}

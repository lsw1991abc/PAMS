/**
 * UUIDUtil.java
 *
 * 
 * lssrc.com
 * 2013-1-30
 */
package com.lssrc.pams.util;

public class UUIDUtil {
	
	public String getUUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}

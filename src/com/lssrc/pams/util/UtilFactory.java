package com.lssrc.pams.util;

public class UtilFactory {
	private static DateUtil dateUtil = new DateUtil();
	private static FileUtil fileUtil = new FileUtil();
	private static ImageUtil imageUtil = new ImageUtil();
	private static UUIDUtil uuidUtil = new UUIDUtil();

	public static DateUtil getDateUtil() {
		return dateUtil;
	}

	public static FileUtil getFileUtil() {
		return fileUtil;
	}

	public static ImageUtil getImageUtil() {
		return imageUtil;
	}

	public static UUIDUtil getUuidUtil() {
		return uuidUtil;
	}
	
}

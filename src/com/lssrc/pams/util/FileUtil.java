/**
 * FileUtil.java
 * 
 * 
 * lssrc.com
 * 2013-01-08
 */
package com.lssrc.pams.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileUtil {

	private Workbook workbook;
	private WritableWorkbook writableWorkbook;
	private WritableSheet writableSheet;
	private Label cellLabel;
	private Sheet[] sheets;
	private Sheet readSheet;
	private List<Sheet> readSheets;
	private int rowCount;
	private int length;
	private List<List<Map<String, String>>> sheetList;
	private List<Map<String, String>> mapList;
	private Map<String, String> map;
	
	private WritableFont writableFont;
	private WritableCellFormat writableCellFormat;

	/**
	 * 文件上传
	 * @param uploadFile 上传的文件
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @return
	 */
	public boolean uploadFile(File uploadFile, String filePath, String fileName) {
		try {
			InputStream inputStream = new FileInputStream(uploadFile);
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			file = new File(filePath, fileName);
			OutputStream outputStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			length = 0;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 读取Excel文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @param columns
	 * @param sheetNames
	 * @return
	 */
	public List<List<Map<String, String>>> readExcel(String filePath, String fileName, String[] sheetNames, String[][] columns) {
		try {
			File file = new File(filePath, fileName);
			InputStream inputStream = new FileInputStream(file);
			workbook = Workbook.getWorkbook(inputStream);
			sheetList = new ArrayList<List<Map<String, String>>>();
			sheets = workbook.getSheets();
			readSheets = new ArrayList<Sheet>();
			if(sheetNames == null) {
				for (Sheet sheet : sheets) {
					readSheets.add(sheet);
				}
			} else {
				for (Sheet sheet : sheets) {
					for (int i = 0; i < sheetNames.length; i++) {
						if (sheet.getName().equalsIgnoreCase(sheetNames[i])) {
							readSheets.add(sheet);
						}
					}
				}
			}
			sheets = null;
			for (int sheetId = 0; sheetId < readSheets.size(); sheetId++) {
				readSheet = readSheets.get(sheetId);
				mapList = new ArrayList<Map<String, String>>();
				rowCount = readSheet.getRows();
				for (int rowId = 0; rowId < rowCount; rowId++) {
					map = new HashMap<String, String>();
					for (int colId = 0; colId < columns[sheetId].length; colId++) {
						map.put(columns[sheetId][colId].toString(), readSheet.getCell(colId, rowId).getContents());
					}
					mapList.add(map);
				}
				sheetList.add(mapList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			readSheets = null;
			workbook.close();
		}
		return sheetList;
	}
	
	/**
	 * 写Excel文件
	 * 
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param sheetNames 一维数组，工作表名字
	 * @param columns 二维数组，对应工作表，每个工作表中的列
	 * @param data 数据
	 * @return
	 */
	public boolean writeExcel(String filePath, String fileName, String[] sheetNames, String[][] columns, List<List<Map<String, String>>> data) {
		try {
			writableFont = new WritableFont(WritableFont.ARIAL, 13, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE2);
			writableCellFormat = new WritableCellFormat(writableFont);
			writableCellFormat.setAlignment(Alignment.CENTRE);
			
			writableWorkbook = Workbook.createWorkbook(new File(filePath, fileName));
			
			for (int sheetId = 0; sheetId < sheetNames.length; sheetId++) {
				writableSheet = writableWorkbook.createSheet(sheetNames[sheetId], sheetId);
				for (int i = 0; i < columns[sheetId].length; i++) {
					cellLabel = new Label(i, 0, columns[sheetId][i], writableCellFormat);
					writableSheet.addCell(cellLabel);
				}
				mapList = data.get(sheetId);
				for (int mapId = 0; mapId < mapList.size(); mapId++) {
					map = mapList.get(mapId);
					for (int colId = 0; colId < columns[sheetId].length; colId++) {
						cellLabel = new Label(colId, mapId+1, map.get(columns[sheetId][colId]));
						writableSheet.addCell(cellLabel);
					}
				}
			}
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

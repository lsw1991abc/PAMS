/**
 * LocationAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-08
 */
package com.lssrc.pams.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Location;
import com.lssrc.pams.service.LocationService;
import com.lssrc.pams.util.DBUtils;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("locationAction")
@Scope("prototype")
public class LocationAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer parentId;
	private String msg;
	private LocationService locationService;
	private Location location;
	private Location parentLocation;
	private List<Location> locations;
	
	private File uploadify;  
    private String uploadifyContentType;  
    private String uploadifyFileName; 
    
    public String update() {
    	try {
			uploadifyFileName = java.net.URLDecoder.decode(uploadifyFileName,"UTF-8");
			System.out.println(uploadifyFileName);
	    	String filePath = ServletActionContext.getServletContext().getRealPath("/admin/upload");
	    	System.out.println(filePath + "/" + uploadifyFileName);
	    	File file = new File(filePath + "/" + uploadifyFileName);
	    	List<List<Map<String, String>>> data = new ArrayList<List<Map<String,String>>>();
	    	List<Map<String, String>> sheetData = new ArrayList<Map<String,String>>();
	    	if(!file.exists()) {
	    		msg = "[{result:'error'}, {errorMsg:'文件不存在'}]";
	    	} else {
	    		data = UtilFactory.getFileUtil().readExcel(filePath, uploadifyFileName, new String[]{"location"}, new String[][]{{"id", "name", "parentId"}});
	    		if(data.size() == 1) {
		    		sheetData = data.get(0);
		    		DBUtils.update("delete from pams_location", null);
		    		Map itemMap;
		    		for (int i = 0; i < sheetData.size(); i++) {
		    			itemMap = sheetData.get(i);
						System.out.println(itemMap.get("id") + "---" + itemMap.get("name") + "---" + itemMap.get("parentId"));
						DBUtils.update("insert into pams_location(id,name,parentId) values(?,?,?)", new Object[]{itemMap.get("id"), itemMap.get("name"),itemMap.get("parentId")});
					}
		    		msg = "[{result:'success'}]";
		    	}
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}, {errorMsg:'文件打开错误'}]";
		}
		return SUCCESS;
	}
    
    public String upload() {
		if(UtilFactory.getFileUtil().uploadFile(uploadify, ServletActionContext.getServletContext().getRealPath("/admin/upload"), uploadifyFileName)) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String add() {
		name = name.trim();
		if (name.length() == 0 || name == null) {
			msg = "[{result:'数据格式不正确'}]";
			return SUCCESS;
		}
		if (locationService.save(new Location(name, parentId))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		location = new Location(id);
		if (locationService.findById(location) == null) {
			msg = "[{result:'您要删除的信息不存在'}]";
			return SUCCESS;
		}
		if (locationService.findByParent(location).size() != 0) {
			msg = "[{result:'您要删除的目录下还有其他数据'}]";
			return SUCCESS;
		}
		if(locationService.delete(location)) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Resource(name = "locationService")
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Location parentLocation) {
		this.parentLocation = parentLocation;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyContentType() {
		return uploadifyContentType;
	}

	public void setUploadifyContentType(String uploadifyContentType) {
		this.uploadifyContentType = uploadifyContentType;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

}

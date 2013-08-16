/**
 * AssetsTypeAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-12
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.AssetsType;
import com.lssrc.pams.service.AssetsTypeService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("assetsTypeAction")
@Scope("prototype")
public class AssetsTypeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private AssetsTypeService assetsTypeService;
	private int beginIndex;
	private int count;
	private String msg;

	private AssetsType assetsType;
	private Integer id;
	private String name;
	private Integer sort;
	private Integer rank;
	private String logo;

	public String add() {
		if (assetsTypeService.save(new AssetsType(name, 1, rank, logo))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String queryByPage() {
		Long rowCount;
		String dataMsg="[]";
		try {
			rowCount = assetsTypeService.getRowCount();
			List<AssetsType> assetsTypes = assetsTypeService.findByPage(beginIndex, count);
			if (assetsTypes.size() > 0) {
				dataMsg = "[";
				for (AssetsType assetsType : assetsTypes) {
					dataMsg += "{";
					dataMsg += "'id':'" + assetsType.getId() + "',";
					dataMsg += "'name':'" + assetsType.getName() + "',";
					dataMsg += "'sort':'" + assetsType.getSort() + "',";
					dataMsg += "'rank':'" + assetsType.getRank() + "',";
					dataMsg += "'logo':'" + assetsType.getLogo() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length()-1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'"+rowCount+"'}], data:"+dataMsg+"}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		try {
			AssetsType assetsType = assetsTypeService.findById(id);
			if (assetsType != null) {
				String dataMsg = "{";
				dataMsg += "'id':'" + assetsType.getId() + "',";
				dataMsg += "'name':'" + assetsType.getName() + "',";
				dataMsg += "'sort':'" + assetsType.getSort() + "',";
				dataMsg += "'rank':'" + assetsType.getRank() + "',";
				dataMsg += "'logo':'" + assetsType.getLogo() + "'";
				dataMsg += "}";
				msg = "[{result:'success'}," + dataMsg + "]";
			} else {
				msg = "[{result:'error'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (assetsTypeService.delete(new AssetsType(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Resource
	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public AssetsType getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}

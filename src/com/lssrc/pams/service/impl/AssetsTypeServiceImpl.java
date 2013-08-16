/**
 * AssetsTypeServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.AssetsTypeDAO;
import com.lssrc.pams.domain.AssetsType;
import com.lssrc.pams.service.AssetsTypeService;

@Service("assetsTypeService")
public class AssetsTypeServiceImpl extends BaseServiceImpl implements AssetsTypeService {
	private AssetsTypeDAO assetsTypeDAO;

	public Long getRowCount() {
		return assetsTypeDAO.getRowCount();
	}

	public List<AssetsType> findByPage(int beginIndex, int count) {
		return assetsTypeDAO.findByPage(beginIndex, count);
	}
	
	public AssetsType findById(Integer id) {
		return assetsTypeDAO.findById(id);
	}
	
	public AssetsTypeDAO getAssetsTypeDAO() {
		return assetsTypeDAO;
	}

	@Resource
	public void setAssetsTypeDAO(AssetsTypeDAO assetsTypeDAO) {
		this.assetsTypeDAO = assetsTypeDAO;
	}

}

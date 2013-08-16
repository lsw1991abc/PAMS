/**
 * BaseServiceImpl.java
 * ServiceImpl父类对象
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.service.impl;

import javax.annotation.Resource;

import com.lssrc.pams.dao.BaseDAO;
import com.lssrc.pams.domain.BasePOJO;
import com.lssrc.pams.service.BaseService;

public class BaseServiceImpl implements BaseService {
	private BaseDAO baseDAO;

	public boolean save(BasePOJO basePOJO) {
		return baseDAO.save(basePOJO);
	}

	public boolean delete(BasePOJO basePOJO) {
		return baseDAO.delete(basePOJO);
	}

	public boolean update(BasePOJO basePOJO) {
		return baseDAO.update(basePOJO);
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	@Resource
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
}

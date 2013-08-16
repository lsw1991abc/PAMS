/**
 * AssetsTypeDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.AssetsType;

public interface AssetsTypeDAO extends BaseDAO {

	public List<AssetsType> findByPage(int beginIndex, int endIndex);

	public Long getRowCount();

	public AssetsType findById(Integer id);
	
}
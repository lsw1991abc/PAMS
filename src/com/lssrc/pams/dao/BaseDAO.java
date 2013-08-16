/**
 * BaseDAO.java
 * DAO父类对象
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.dao;

import com.lssrc.pams.domain.BasePOJO;

public interface BaseDAO {
	
	public boolean save(BasePOJO basePOJO);

	public boolean delete(BasePOJO basePOJO);

	public boolean update(BasePOJO basePOJO);

}

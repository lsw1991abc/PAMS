/**
 * AdviceDAO.java
 *
 * 
 * lssrc.com
 * 2013-02-12
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Advice;

public interface AdviceDAO extends BaseDAO{
	
	public List<Advice> findAll(int beginIndex, int count);
	
	public Long getRowCount();

	public Advice findById(Integer id);

}

/**
 * MemorandumDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Memorandum;
import com.lssrc.pams.domain.PamsUser;

public interface MemorandumDAO extends BaseDAO {

	public Memorandum findById(Memorandum memorandum);

	public List<Memorandum> findByUser(PamsUser user, int beginIndex, int count, String state);
	
	public Long getRowCount();

	public List<Memorandum> findByPage(int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user, String state);
	
}
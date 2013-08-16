/**
 * PamsUserDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.PamsUser;

public interface UserDAO extends BaseDAO {

	public PamsUser findByUsername(String username);
	
	public List<PamsUser> findByPage(int beginIndex, int count, String authority);

	public Long getRowCount(String authority);

}
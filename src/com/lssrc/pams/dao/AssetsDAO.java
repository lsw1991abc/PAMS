/**
 * AssetsDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;

public interface AssetsDAO extends BaseDAO {

	public List<Assets> findByUser(PamsUser user);

	public Double getBalanceByUser(PamsUser user);

	public Assets findById(String id);
	
}
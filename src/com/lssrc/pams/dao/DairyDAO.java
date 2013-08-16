/**
 * DairyDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Dairy;
import com.lssrc.pams.domain.PamsUser;

public interface DairyDAO extends BaseDAO {

	public Dairy findById(Dairy dairy);

	public List<Dairy> findByUser(PamsUser user, int beginIndex, int count);

	public Long getCountByUser(PamsUser user);
	
}
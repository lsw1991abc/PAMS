/**
 * LifeWallDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.LifeWall;

public interface LifeWallDAO extends BaseDAO {

	public LifeWall findById(LifeWall lifeWall);

	public List<LifeWall> findByPage(int beginIndex, int count);
	
	public List<LifeWall> findByRandom(int count);
	
	public Long getRowCount();
	
}
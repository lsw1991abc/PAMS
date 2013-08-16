/**
 * LifeWallServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.LifeWallDAO;
import com.lssrc.pams.domain.LifeWall;
import com.lssrc.pams.service.LifeWallService;

@Service("lifeWallService")
public class LifeWallServiceImpl extends BaseServiceImpl implements LifeWallService {
	private LifeWallDAO lifeWallDAO;

	public LifeWall findById(LifeWall lifeWall) {
		return lifeWallDAO.findById(lifeWall);
	}

	public List<LifeWall> findByPage(int beginIndex, int count) {
		return lifeWallDAO.findByPage(beginIndex, count);
	}

	public List<LifeWall> findByRandom(int count) {
		return lifeWallDAO.findByRandom(count);
	}
	
	public Long getRowCount() {
		return lifeWallDAO.getRowCount();
	}
	
	public LifeWallDAO getLifeWallDAO() {
		return lifeWallDAO;
	}

	@Resource
	public void setLifeWallDAO(LifeWallDAO lifeWallDAO) {
		this.lifeWallDAO = lifeWallDAO;
	}

}

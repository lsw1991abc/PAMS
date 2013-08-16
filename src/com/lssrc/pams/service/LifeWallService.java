/**
 * SiteInfoService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.LifeWall;

public interface LifeWallService extends BaseService {

	public LifeWall findById(LifeWall lifeWall);

	public List<LifeWall> findByPage(int beginIndex, int count);

	public List<LifeWall> findByRandom(int count);

	public Long getRowCount();

}

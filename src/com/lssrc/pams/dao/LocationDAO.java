/**
 * LocationDAO.java
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Location;

public interface LocationDAO extends BaseDAO {

	public Location findById(Location location);

	public List<Location> findByParent(Location parent, int beginIndex, int count);
	
	public List<Location> findByParent(Location parent);

}

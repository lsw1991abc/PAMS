/**
 * LocationService.java
 * 
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Location;

public interface LocationService extends BaseService {

	public Location findById(Location location);

	public List<Location> findByParent(Location parent, int beginIndex, int count);
	
	public List<Location> findByParent(Location parent);

}

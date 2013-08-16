/**
 * LocationServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.LocationDAO;
import com.lssrc.pams.domain.Location;
import com.lssrc.pams.service.LocationService;

@Service("locationService")
public class LocationServiceImpl extends BaseServiceImpl implements LocationService {

	private LocationDAO locationDAO;

	public Location findById(Location location) {
		return locationDAO.findById(location);
	}

	public List<Location> findByParent(Location parent, int beginIndex, int count) {
		return locationDAO.findByParent(parent, beginIndex, count);
	}

	public List<Location> findByParent(Location parent) {
		return locationDAO.findByParent(parent);
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	@Resource
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

}

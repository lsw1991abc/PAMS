/**
 * LocationDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.LocationDAO;
import com.lssrc.pams.domain.Location;

@Repository("locationDAO")
public class LocationDAOImpl extends BaseDAOImpl implements LocationDAO {

	public Location findById(Location location) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			location = (Location) session.get(Location.class, location.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return location;
	}

	@SuppressWarnings("unchecked")
	public List<Location> findByParent(Location location, int beginIndex, int count) {
		List<Location> locations = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			locations = session.createCriteria(Location.class).add(Restrictions.eq("parentId", location.getId())).setFirstResult(beginIndex).setMaxResults(count).addOrder(Order.asc("locationId")).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return locations;
	}

	@SuppressWarnings("unchecked")
	public List<Location> findByParent(Location location) {
		List<Location> locations = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			locations = session.createCriteria(Location.class).add(Restrictions.eq("parentId", location.getId())).addOrder(Order.asc("locationId")).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return locations;
	}	

}

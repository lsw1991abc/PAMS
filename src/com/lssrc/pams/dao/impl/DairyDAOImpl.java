/**
 * DairyDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-20
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.DairyDAO;
import com.lssrc.pams.domain.Dairy;
import com.lssrc.pams.domain.PamsUser;

@Repository("dairyDAO")
public class DairyDAOImpl extends BaseDAOImpl implements DairyDAO {

	public Dairy findById(Dairy dairy) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			dairy = (Dairy) session.get(Dairy.class, dairy.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dairy;
	}

	@SuppressWarnings("unchecked")
	public List<Dairy> findByUser(PamsUser user, int beginIndex, int count) {
		List<Dairy> dairies = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			dairies = session.createCriteria(Dairy.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dairies;
	}
	
	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from Dairy where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

/**
 * UserDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-19
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.UserDAO;
import com.lssrc.pams.domain.PamsUser;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
	

	@SuppressWarnings("unchecked")
	public PamsUser findByUsername(String username) {
		PamsUser user;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			List<PamsUser> users = session.createCriteria(PamsUser.class).add(Restrictions.eq("username", username)).list();
			if (users.size() == 1) {
				user = users.get(0);
			} else {
				user = null;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<PamsUser> findByPage(int beginIndex, int count, String authority) {
		List<PamsUser> users;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			//add(Restrictions.eq("authority", PamsConstant.ROLE_USER))
			users = session.createCriteria(PamsUser.class).add(Restrictions.eq("authority", authority)).setFirstResult(beginIndex).setMaxResults(count).addOrder(Order.desc("registerTime")).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}

	public Long getRowCount(String authority) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from PamsUser where authority='"+authority+"'").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

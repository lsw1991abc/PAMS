/**
 * MemorandumDAOImpl.java
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

import com.lssrc.pams.dao.MemorandumDAO;
import com.lssrc.pams.domain.Memorandum;
import com.lssrc.pams.domain.PamsUser;

@Repository("memorandumDAO")
public class MemorandumDAOImpl extends BaseDAOImpl implements MemorandumDAO {

	public Memorandum findById(Memorandum memorandum) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			memorandum = (Memorandum) session.get(Memorandum.class, memorandum.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return memorandum;
	}

	@SuppressWarnings("unchecked")
	public List<Memorandum> findByUser(PamsUser user, int beginIndex, int count, String state) {
		List<Memorandum> memorandums = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			if(state == null) {
				memorandums = session.createCriteria(Memorandum.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			} else {
				memorandums = session.createCriteria(Memorandum.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("state", state)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return memorandums;
	}
	
	@SuppressWarnings("unchecked")
	public List<Memorandum> findByPage(int beginIndex, int count) {
		List<Memorandum> memorandums = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			memorandums = session.createCriteria(Memorandum.class).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return memorandums;
	}
	
	public Long getRowCount() {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from Memorandum").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}
	
	public Long getCountByUser(PamsUser user, String state) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			if (state != null) {
				rowCount = (Long) session.createQuery("select count(*) from Memorandum where user=? and state=?").setString(0, user.getUsername()).setString(1, state).uniqueResult();
			} else {
				rowCount = (Long) session.createQuery("select count(*) from Memorandum where user=?").setString(0, user.getUsername()).uniqueResult();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}
	
}

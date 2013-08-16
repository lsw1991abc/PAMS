/**
 * AdviceDAOImpl.java
 *
 * 
 * lssrc.com
 * 2013-02-12
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.AdviceDAO;
import com.lssrc.pams.domain.Advice;

@Repository("adviceDAO")
public class AdviceDAOImpl extends BaseDAOImpl implements AdviceDAO {

	@SuppressWarnings("unchecked")
	public List<Advice> findAll(int beginIndex, int count) {
		List<Advice> advices = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			advices = session.createCriteria(Advice.class).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return advices;
	}
	
	@SuppressWarnings("unchecked")
	public Advice findById(Integer id) {
		List<Advice> advices = null;
		Advice advice = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			advices = session.createCriteria(Advice.class).add(Restrictions.eq("id", id)).list();
			if(advices.size() == 1) {
				advice = advices.get(0);
			} else {
				advice = null;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return advice;
	}

	public Long getRowCount() {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from Advice").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

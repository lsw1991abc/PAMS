/**
 * BaseDAOImpl.java
 * DAOImpl父类对象
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.BaseDAO;
import com.lssrc.pams.domain.BasePOJO;

@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO {
	private SessionFactory sessionFactory;
	
	public boolean save(BasePOJO basePOJO) {
		Transaction transaction = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(basePOJO);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(BasePOJO basePOJO) {
		Transaction transaction = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(basePOJO);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(BasePOJO basePOJO) {
		Transaction transaction = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(basePOJO);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

/**
 * TransactionRecordDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-23
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.TransactionRecordDAO;
import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.TransactionRecord;

@Repository("transactionRecordDAO")
public class TransactionRecordDAOImpl extends BaseDAOImpl implements TransactionRecordDAO {

	public TransactionRecord findById(TransactionRecord transactionRecord) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			transactionRecord = (TransactionRecord) session.get(TransactionRecord.class, transactionRecord.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return transactionRecord;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionRecord> findByAssets(Assets assets, int beginIndex, int count) {
		List<TransactionRecord> transactionRecords = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			transactionRecords = session.createCriteria(TransactionRecord.class).add(Restrictions.eq("asssetsId", assets.getId())).addOrder(Order.desc("transactionRecordTime")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return transactionRecords;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionRecord> findByUser(PamsUser user, int beginIndex, int count, String type) {
		List<TransactionRecord> transactionRecords = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			if(type != null) {
				transactionRecords = session.createCriteria(TransactionRecord.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("type", type)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			} else {
				transactionRecords = session.createCriteria(TransactionRecord.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return transactionRecords;
	}
	
	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from TransactionRecord where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

	public String[][] getDataByGroup(String username, String[][] dataSetArray) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			for (int i = 0; i < 5; i++) {
				dataSetArray[i][0] = session.createQuery("select sum(amount) from TransactionRecord where user=? and time like '" + dataSetArray[i][2] + "%'").setString(0, username).uniqueResult() +"";	
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dataSetArray;
	}

}

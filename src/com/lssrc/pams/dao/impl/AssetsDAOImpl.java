/**
 * AssetsDAOImpl.java
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

import com.lssrc.pams.dao.AssetsDAO;
import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;

@Repository("assetsDAO")
public class AssetsDAOImpl extends BaseDAOImpl implements AssetsDAO {

	@SuppressWarnings("unchecked")
	public List<Assets> findByUser(PamsUser user) {
		List<Assets> assetss = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			assetss = session.createCriteria(Assets.class).add(Restrictions.eq("user", user)).addOrder(Order.asc("account")).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return assetss;
	}
	
	public Double getBalanceByUser(PamsUser user) {
		Double balance;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			balance =  (Double) session.createQuery("select sum(balance) from Assets where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
		return balance;
	}

	public Assets findById(String id) {
		Assets assets = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			assets = (Assets) session.get(Assets.class, id);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return assets;
	}

}

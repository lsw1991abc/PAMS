/**
 * AssetsTypeDAOImpl.java
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

import com.lssrc.pams.dao.AssetsTypeDAO;
import com.lssrc.pams.domain.AssetsType;

@Repository("assetsTypeDAO")
public class AssetsTypeDAOImpl extends BaseDAOImpl implements AssetsTypeDAO {

	@SuppressWarnings("unchecked")
	public List<AssetsType> findByPage(int beginIndex, int count) {
		List<AssetsType> assetsTypes = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			assetsTypes = session.createCriteria(AssetsType.class).addOrder(Order.asc("rank")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return assetsTypes;
	}
	
	@SuppressWarnings("unchecked")
	public AssetsType findById(Integer id) {
		List<AssetsType> assetsTypes = null;
		AssetsType assetsType = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			assetsTypes = session.createCriteria(AssetsType.class).add(Restrictions.eq("id", id)).list();
			if(assetsTypes.size() == 1) {
				assetsType = assetsTypes.get(0);
			} else {
				assetsType = null;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return assetsType;
	}

	public Long getRowCount() {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from AssetsType").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

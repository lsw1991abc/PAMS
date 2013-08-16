/**
 * LifeWallDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-22
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.LifeWallDAO;
import com.lssrc.pams.domain.LifeWall;

@Repository("lifeWallDAO")
public class LifeWallDAOImpl extends BaseDAOImpl implements LifeWallDAO {

	public LifeWall findById(LifeWall lifeWall) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			lifeWall = (LifeWall) session.get(LifeWall.class, lifeWall.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lifeWall;
	}

	@SuppressWarnings("unchecked")
	public List<LifeWall> findByPage(int beginIndex, int count) {
		List<LifeWall> lifeWalls = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			lifeWalls = session.createCriteria(LifeWall.class).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lifeWalls;
	}

	@SuppressWarnings("unchecked")
	public List<LifeWall> findByRandom(int count) {
		List<LifeWall> lifeWalls = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			lifeWalls = session.createSQLQuery("select * from pams_lifewall lw,pams_user u where  lw.user=u.username order by rand()").addEntity(LifeWall.class).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lifeWalls;
	}
	
	public Long getRowCount() {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from LifeWall").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

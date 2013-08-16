/**
 * EasyNoteDAOImpl.java
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

import com.lssrc.pams.dao.EasyNoteDAO;
import com.lssrc.pams.domain.EasyNote;
import com.lssrc.pams.domain.PamsUser;

@Repository("easyNoteDAO")
public class EasyNoteDAOImpl extends BaseDAOImpl implements EasyNoteDAO {

	public EasyNote findById(EasyNote easyNote) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			easyNote = (EasyNote) session.get(EasyNote.class, easyNote.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return easyNote;
	}

	@SuppressWarnings("unchecked")
	public List<EasyNote> findByUser(PamsUser user, int beginIndex, int count) {
		List<EasyNote> easyNotes = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			easyNotes = session.createCriteria(EasyNote.class).add(Restrictions.eq("user", user)).addOrder(Order.asc("title")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return easyNotes;
	}

	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from EasyNote where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}
	
}

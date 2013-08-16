/**
 * MessageStateDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-21
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.MessageStateDAO;
import com.lssrc.pams.domain.MessageState;
import com.lssrc.pams.domain.PamsUser;

@Repository("messageStateDAO")
public class MessageStateDAOImpl extends BaseDAOImpl implements MessageStateDAO {

	@SuppressWarnings("unchecked")
	public List<MessageState> findByUser(PamsUser user, int beginIndex, int count) {
		List<MessageState> messageStates = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			messageStates = session.createCriteria(MessageState.class).add(Restrictions.eq("receiver", user)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return messageStates;
	}
	
	@SuppressWarnings("unchecked")
	public MessageState findById(MessageState messageState) {
		List<MessageState> messageStates = null;		
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			messageStates = session.createCriteria(MessageState.class).add(Restrictions.eq("receiver", messageState.getReceiver())).add(Restrictions.eq("message", messageState.getMessage())).list();
			if(messageStates != null) {
				messageState = messageStates.get(0);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return messageState;
	}
	
	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from MessageState where receiver=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

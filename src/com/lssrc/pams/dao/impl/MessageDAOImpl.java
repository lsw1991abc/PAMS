/**
 * MessageDAOImpl.java
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

import com.lssrc.pams.dao.MessageDAO;
import com.lssrc.pams.domain.Message;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.util.DBUtils;

@Repository("messageDAO")
public class MessageDAOImpl extends BaseDAOImpl implements MessageDAO {
	
	public boolean saveBySelf(Message message) {
		if(super.save(message)) {
			DBUtils.update("update pams_user set tempStr1=?", new Object[]{message.getId()});
			DBUtils.update("insert into pams_messagestate(receiver,message) select username,tempStr1 from pams_user", null);
			return true;
		} else {
			return false;
		}
	}

	public Message findById(Message message) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			message = (Message) session.get(Message.class, message.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByUser(PamsUser user, int beginIndex, int count) {
		List<Message> messages = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			messages = session.createCriteria(Message.class).add(Restrictions.eq("sender", user)).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return messages;
	}
	
	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from Message where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public Message findById(String id) {
		List<Message> messages = null;
		Message message = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			messages = session.createCriteria(Message.class).add(Restrictions.eq("id", id)).list();
			if(messages.size() == 1) {
				message = messages.get(0);
			} else {
				message = null;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}

	public Long getRowCount() {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from Message").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findAll(int beginIndex, int count) {
		List<Message> messages = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			messages = session.createCriteria(Message.class).addOrder(Order.desc("time")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return messages;
	}

	public boolean deleteBySelf(Message message) {
		try {
			DBUtils.update("delete from pams_messagestate where message=?", new Object[]{message.getId()});
			DBUtils.update("delete from pams_message where id=?", new Object[]{message.getId()});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

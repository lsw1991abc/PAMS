/**
 * AddressBookDAOImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-21
 */
package com.lssrc.pams.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lssrc.pams.dao.AddressBookDAO;
import com.lssrc.pams.domain.AddressBook;
import com.lssrc.pams.domain.PamsUser;

@Repository("addressBookDAO")
public class AddressBookDAOImpl extends BaseDAOImpl implements AddressBookDAO {

	@SuppressWarnings("unchecked")
	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count) {
		List<AddressBook> addressBooks = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			addressBooks = session.createCriteria(AddressBook.class).add(Restrictions.eq("user", user)).addOrder(Order.asc("realName")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return addressBooks;
	}

	public Long getCountByUser(PamsUser user) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from AddressBook where user=?").setString(0, user.getUsername()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	public AddressBook findById(String uuid) {
		List<AddressBook> addressBooks = null;
		AddressBook addressBook = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			addressBooks = session.createCriteria(AddressBook.class).add(Restrictions.eq("uuid", uuid)).list();
			if(addressBooks.size() == 1) {
				addressBook = addressBooks.get(0);
			} else {
				addressBook = null;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return addressBook;
	}

	@SuppressWarnings("unchecked")
	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count, String realName) {
		List<AddressBook> addressBooks = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			addressBooks = session.createCriteria(AddressBook.class).add(Restrictions.eq("user", user)).add(Restrictions.like("realName", realName, MatchMode.ANYWHERE)).addOrder(Order.asc("realName")).setFirstResult(beginIndex).setMaxResults(count).list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return addressBooks;
	}

	public Long getCountByUser(PamsUser user, String realName) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			rowCount = (Long) session.createQuery("select count(*) from AddressBook where user=? and realName like ?").setString(0, user.getUsername()).setString(1, "%" + realName + "%").uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}
	
}

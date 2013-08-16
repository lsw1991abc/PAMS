/**
 * TaskDAOImpl.java
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

import com.lssrc.pams.dao.TaskDAO;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.Task;

@Repository("taskDAO")
public class TaskDAOImpl extends BaseDAOImpl implements TaskDAO {

	public Task findById(Task task) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			task = (Task) session.get(Task.class, task.getId());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return task;
	}

	@SuppressWarnings("unchecked")
	public List<Task> findByUser(PamsUser user, int beginIndex, int count, String state) {
		List<Task> tasks = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			if (state != null) {
				tasks = session.createCriteria(Task.class).add(Restrictions.eq("user", user)).add(Restrictions.eq("state", state)).addOrder(Order.asc("endTime")).setFirstResult(beginIndex).setMaxResults(count).list();
			} else {
				tasks = session.createCriteria(Task.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("endTime")).setFirstResult(beginIndex).setMaxResults(count).list();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return tasks;
	}


	public Long getCountByUser(PamsUser user, String state) {
		Long rowCount;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			if (state != null) {
				rowCount = (Long) session.createQuery("select count(*) from Task as t where t.user=? and t.state=?").setString(0, user.getUsername()).setString(1, state).uniqueResult();
			} else {
				rowCount = (Long) session.createQuery("select count(*) from Task as t where t.user=?").setString(0, user.getUsername()).uniqueResult();
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rowCount;
	}

}

/**
 * TaskDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Task;
import com.lssrc.pams.domain.PamsUser;

public interface TaskDAO extends BaseDAO {

	public Task findById(Task task);

	public List<Task> findByUser(PamsUser user, int beginIndex, int count, String state);
	
	public Long getCountByUser(PamsUser user, String state);

}
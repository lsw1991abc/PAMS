/**
 * TaskService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Task;
import com.lssrc.pams.domain.PamsUser;

public interface TaskService extends BaseService {

	public Task findById(Task task);

	public List<Task> findByUser(PamsUser user, int beginIndex, int count, String taskState);
	
	public Long getCountByUser(PamsUser user, String state);

}

/**
 * TaskServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.TaskDAO;
import com.lssrc.pams.domain.Task;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.TaskService;

@Service("taskService")
public class TaskServiceImpl extends BaseServiceImpl implements TaskService {
	private TaskDAO taskDAO;

	public Task findById(Task task) {
		return taskDAO.findById(task);
	}

	public List<Task> findByUser(PamsUser user, int beginIndex, int count, String state) {
		return taskDAO.findByUser(user, beginIndex, count, state);
	}

	public Long getCountByUser(PamsUser user, String state) {
		return taskDAO.getCountByUser(user, state);
	}
	
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	@Resource
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

}

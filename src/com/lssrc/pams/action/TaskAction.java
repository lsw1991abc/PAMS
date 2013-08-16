/**
 * EasyNoteAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-11
 */
package com.lssrc.pams.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.Task;
import com.lssrc.pams.service.TaskService;
import com.lssrc.pams.util.PamsConstant;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("taskAction")
@Scope("prototype")
public class TaskAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private TaskService taskService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private String startTime;
	private String endTime;
	private String state;
	private PamsUser user;
	private List<Task> tasks;

	public String add() {
		if (taskService.save(new Task(UtilFactory.getUuidUtil().getUUID(), startTime, endTime, title, content, PamsConstant.TASK_ING, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		if (taskService.delete(new Task(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String update() {
		if (taskService.update(new Task(id, startTime, endTime, title, content, PamsConstant.TASK_ING, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryByUser() throws UnsupportedEncodingException {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		state = java.net.URLDecoder.decode(state, "UTF-8");
		try {
			rowCount = taskService.getCountByUser(pamsUser, "".equalsIgnoreCase(state) ? null : state);
			tasks = taskService.findByUser(pamsUser, beginIndex, count, "".equalsIgnoreCase(state) ? null : state);
			if (tasks.size() > 0) {
				dataMsg = "[";
				for (Task task : tasks) {
					dataMsg += "{";
					dataMsg += "id:'" + task.getId() + "',";
					dataMsg += "title:'" + task.getTitle() + "',";
					dataMsg += "startTime:'" + task.getStartTime() + "',";
					dataMsg += "state:'" + task.getState() + "',";
					dataMsg += "endTime:'" + task.getEndTime() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'" + rowCount + "'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		Task task = new Task(id);
		try {
			task = taskService.findById(task);
			if (task != null) {
				msg = "[{result:'success'}, {";
				msg += "title:'" + task.getTitle() + "',";
				msg += "content:'" + task.getContent() + "',";
				msg += "startTime:'" + task.getStartTime() + "',";
				msg += "endTime:'" + task.getEndTime() + "',";
				msg += "state:'" + task.getState() + "'";
				msg += "}]";
			} else {
				msg = "[{result:'error'}, {value:'未找到'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}, {value:'异常'}]";
		}
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) throws UnsupportedEncodingException {
		this.state = java.net.URLDecoder.decode(state, "UTF-8");
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public PamsUser getUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			user = new PamsUser(username);
		}
		return user;
	}

	public void setUser(PamsUser user) {
		this.user = user;
	}

	@Resource
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}

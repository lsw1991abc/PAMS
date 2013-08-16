/**
 * MessageStateAction.java
 *
 * lssrc.com
 * 2013-03-22
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Message;
import com.lssrc.pams.domain.MessageState;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.MessageStateService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("messageStateAction")
@Scope("prototype")
public class MessageStateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String msg;
	private PamsUser user;
	private MessageStateService messageStateService;
	private Integer beginIndex;
	private Integer count;
	private List<MessageState> messageStates;
	private String messageId;
	
	public String update() {
		MessageState messageState = messageStateService.findById(new MessageState(new Message(messageId), this.getUser()));
		messageState.setState("已读");
		if (messageStateService.update(messageState)) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (messageStateService.delete(new MessageState(new Message(messageId), this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryByUser() {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		Message message;
		try {
			rowCount = messageStateService.getCountByUser(pamsUser);
			messageStates = messageStateService.findByUser(pamsUser, beginIndex, count);
			if (messageStates.size() > 0) {
				dataMsg = "[";
				for (MessageState messageState : messageStates) {
					message = messageState.getMessage();
					dataMsg += "{";
					dataMsg += "state:'" + messageState.getState() + "',";
					dataMsg += "id:'" + message.getId() + "',";
					dataMsg += "sender:'" + message.getSender().getUsername() + "',";
					dataMsg += "title:'" + message.getTitle() + "',";
					dataMsg += "time:'" + message.getTime() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'" + rowCount +"'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getCount() {
		return count;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Resource
	public void setMessageStateService(MessageStateService messageStateService) {
		this.messageStateService = messageStateService;
	}
	
}

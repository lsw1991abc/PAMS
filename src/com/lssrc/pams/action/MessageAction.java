/**
 * MessageAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-14
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
import com.lssrc.pams.service.MessageService;
import com.lssrc.pams.service.MessageStateService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private MessageService messageService;
	private MessageStateService messageStateService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private PamsUser user;
	private String receiver;
	private List<Message> messages;
	private List<MessageState> messageStates;
	
	private String type;
	private String state;

	public String add() {
		String messageId = UtilFactory.getUuidUtil().getUUID();
		String time = UtilFactory.getDateUtil().getRightTime();
		if (messageService.saveBySelf(new Message(messageId, title, time, content, this.getUser()))) {
			
			Message message = new Message(messageId);
			try {
				/*
				JSONArray jsonArray = new JSONArray(receiver);
				JSONObject jsonObject;
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject = jsonArray.getJSONObject(i);
					messageStateService.save(new MessageState(message, new PamsUser(jsonObject.getString("username")), PamsConstant.MESSAGE_READ_NO, time));
				}
				*/
				msg = "[{result:'success'}]";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "[{result:'error'}]";
			}
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		if (messageService.deleteBySelf(new Message(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryByPage() {
		Long rowCount;
		String dataMsg = "[]";
		try {
			rowCount = messageService.getRowCount();
			messages = messageService.findAll(beginIndex, count);
			if (messages.size() > 0) {
				dataMsg = "[";
				for (Message message : messages) {
					dataMsg += "{";
					dataMsg += "id:'" + message.getId() + "',";
					dataMsg += "title:'" + message.getTitle() + "',";
					dataMsg += "time:'" + message.getTime() + "',";
					dataMsg += "sender:'" + message.getSender().getUsername() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{result:'success',rowCount:'" + rowCount + "'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{result:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryByUser() {
		String dataMsg = "[]";
		if ("发信".equalsIgnoreCase(type)) {
			try {
				messages = messageService.findByUser(this.getUser(), beginIndex, count);
				if (messages.size() > 0) {
					dataMsg = "[";
					for (Message message : messages) {
						dataMsg += "{";
						dataMsg += "id:'" + message.getId() + "',";
						dataMsg += "title:'" + message.getTitle() + "',";
						dataMsg += "sender:'" + message.getSender().getUsername() + "',";
						dataMsg += "time:'" + message.getTime() + "'";
						dataMsg += "},";
					}
					dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
					dataMsg += "]";
				}
				msg = "{result:[{resultMsg:'success',rowCount:'0'}], data:" + dataMsg + "}";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
			}
		} else if ("收信".equalsIgnoreCase(type)) {
			messageStates = messageStateService.findByUser(this.getUser(), beginIndex, count);
			if (messageStates.size() > 0) {
				Message message;
				dataMsg = "[";
				for (MessageState messageState : messageStates) {
					message = messageState.getMessage();
					dataMsg += "{";
					dataMsg += "id:'" + message.getId() + "',";
					dataMsg += "title:'" + message.getTitle() + "',";
					dataMsg += "content:'" + message.getContent() + "',";
					dataMsg += "time:'" + message.getTime() + "',";
					dataMsg += "sender:'" + message.getSender().getUsername() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'0'}], data:" + dataMsg + "}";
		} else {
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		try {
			Message message = messageService.findById(id);
			if(message != null) {
				String dataMsg = "{";
				dataMsg += "id:'" + message.getId() + "',";
				dataMsg += "title:'" + message.getTitle() + "',";
				dataMsg += "sender:'" + message.getSender().getUsername() + "',";
				dataMsg += "content:'" + message.getContent() + "',";
				dataMsg += "time:'" + message.getTime() + "'";
				dataMsg += "}";
				msg = "[{result:'success'}, " + dataMsg + "]";
			} else {
				msg = "[{result:'error'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}]";
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Resource
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Resource
	public void setMessageStateService(MessageStateService messageStateService) {
		this.messageStateService = messageStateService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

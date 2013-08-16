/**
 * MessageServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.MessageDAO;
import com.lssrc.pams.domain.Message;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
	private MessageDAO messageDAO;

	public boolean saveBySelf(Message message) {
		return messageDAO.saveBySelf(message);
	}

	public Message findById(Message message) {
		return messageDAO.findById(message);
	}

	public List<Message> findByUser(PamsUser user, int beginIndex, int count) {
		return messageDAO.findByUser(user, beginIndex, count);
	}

	public Long getCountByUser(PamsUser user) {
		return messageDAO.getCountByUser(user);
	}
	
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	@Resource
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public Message findById(String id) {
		return messageDAO.findById(id);
	}

	public Long getRowCount() {
		return messageDAO.getRowCount();
	}

	public List<Message> findAll(int beginIndex, int count) {
		return messageDAO.findAll(beginIndex, count);
	}

	public boolean deleteBySelf(Message message) {
		return messageDAO.deleteBySelf(message);
	}
	
}

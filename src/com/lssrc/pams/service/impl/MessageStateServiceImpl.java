/**
 * MessageStateServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.MessageStateDAO;
import com.lssrc.pams.domain.MessageState;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.MessageStateService;

@Service("messageStateService")
public class MessageStateServiceImpl extends BaseServiceImpl implements MessageStateService {
	private MessageStateDAO messageStateDAO;

	public List<MessageState> findByUser(PamsUser user, int beginIndex, int count) {
		return messageStateDAO.findByUser(user, beginIndex, count);
	}

	public Long getCountByUser(PamsUser user) {
		return messageStateDAO.getCountByUser(user);
	}
	
	public MessageState findById(MessageState messageState) {
		return messageStateDAO.findById(messageState);
	}
	
	public MessageStateDAO getMessageStateDAO() {
		return messageStateDAO;
	}

	@Resource
	public void setMessageStateDAO(MessageStateDAO messageStateDAO) {
		this.messageStateDAO = messageStateDAO;
	}

}

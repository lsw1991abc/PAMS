/**
 * MessageStateService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.MessageState;
import com.lssrc.pams.domain.PamsUser;

public interface MessageStateService extends BaseService {
	
	public List<MessageState> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);

	public MessageState findById(MessageState messageState);
	
}

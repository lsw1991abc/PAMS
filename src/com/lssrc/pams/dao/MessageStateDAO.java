/**
 * MessageStateDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.MessageState;
import com.lssrc.pams.domain.PamsUser;

public interface MessageStateDAO extends BaseDAO {

	public List<MessageState> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);

	public MessageState findById(MessageState messageState);
	
}

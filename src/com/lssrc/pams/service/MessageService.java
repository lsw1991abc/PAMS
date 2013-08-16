/**
 * MessageService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Message;
import com.lssrc.pams.domain.PamsUser;

public interface MessageService extends BaseService {
	
	public Message findById(Message message);

	public List<Message> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);

	public Message findById(String id);
	
	public boolean saveBySelf(Message message);

	public Long getRowCount();

	public List<Message> findAll(int beginIndex, int count);

	public boolean deleteBySelf(Message message);
	
}

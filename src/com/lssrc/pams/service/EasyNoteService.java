/**
 * EasyNoteService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.EasyNote;
import com.lssrc.pams.domain.PamsUser;

public interface EasyNoteService extends BaseService {
	
	public EasyNote findById(EasyNote easyNote);

	public List<EasyNote> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);
	
}

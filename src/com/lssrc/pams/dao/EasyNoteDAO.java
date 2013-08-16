/**
 * EasyNoteDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.EasyNote;
import com.lssrc.pams.domain.PamsUser;

public interface EasyNoteDAO extends BaseDAO {
	
	public EasyNote findById(EasyNote easyNote);

	public List<EasyNote> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);
	
}
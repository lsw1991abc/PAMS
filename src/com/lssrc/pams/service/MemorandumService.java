/**
 * MemorandumService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Memorandum;
import com.lssrc.pams.domain.PamsUser;

public interface MemorandumService extends BaseService {
	
	public Memorandum findById(Memorandum memorandum);

	public List<Memorandum> findByUser(PamsUser user, int beginIndex, int count, String state);
	
	public Long getRowCount();

	public List<Memorandum> findByPage(int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user, String state);
	
}

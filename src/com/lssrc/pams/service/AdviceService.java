/**
 * AdviceService.java
 *
 * 
 * lssrc.com
 * 2013-02-12
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Advice;

public interface AdviceService extends BaseService {
	
	public List<Advice> findAll(int beginIndex, int count);
	
	public Advice findById(Integer id);
	
	public Long getRowCount();

}

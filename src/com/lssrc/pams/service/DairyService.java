/**
 * DairyService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Dairy;
import com.lssrc.pams.domain.PamsUser;

public interface DairyService extends BaseService {
	
	public Dairy findById(Dairy dairy);

	public List<Dairy> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);
	
}

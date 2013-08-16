/**
 * AssetsService.java
 * 
 * 
 * lssrc.com
 * 2012-12-27
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;

public interface AssetsService extends BaseService {
	
	public List<Assets> findByUser(PamsUser user);
	
	public Double getBalanceByUser(PamsUser user);

	public Assets findById(String id);
	
}

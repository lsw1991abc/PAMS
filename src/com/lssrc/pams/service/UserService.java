/**
\ * UserService.java
 * 
 * 
 * lssrc.com
 * 2012-12-26
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.PamsUser;

public interface UserService extends BaseService {
	
	public PamsUser findByUsername(String username);

	public List<PamsUser> findByPage(int beginIndex, int count, String authority);
	
	public Long getRowCount(String authority);

}

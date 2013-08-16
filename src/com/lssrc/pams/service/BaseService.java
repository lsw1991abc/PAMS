/**
 * BaseService.java
 * Service父类对象
 * 
 * lssrc.com
 * 2012-12-17
 */
package com.lssrc.pams.service;

import com.lssrc.pams.domain.BasePOJO;

public interface BaseService {
	
	public boolean save(BasePOJO basePOJO);

	public boolean delete(BasePOJO basePOJO);

	public boolean update(BasePOJO basePOJO);
	
}

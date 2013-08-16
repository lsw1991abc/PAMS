/**
 * AssetsTypeService.java
 * 
 * 
 * lssrc.com
 * 2012-12-27
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.AssetsType;

public interface AssetsTypeService extends BaseService {

	public Long getRowCount();

	public List<AssetsType> findByPage(int beginIndex, int count);

	public AssetsType findById(Integer id);

}

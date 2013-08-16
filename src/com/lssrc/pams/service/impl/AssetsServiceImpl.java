/**
 * AssetsServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-31
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.AssetsDAO;
import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.AssetsService;

@Service("assetsService")
public class AssetsServiceImpl extends BaseServiceImpl implements AssetsService {

	private AssetsDAO assetsDAO;

	public List<Assets> findByUser(PamsUser user) {
		return assetsDAO.findByUser(user);
	}

	public Double getBalanceByUser(PamsUser user) {
		return assetsDAO.getBalanceByUser(user);
	}
	
	public AssetsDAO getAssetsDAO() {
		return assetsDAO;
	}

	@Resource
	public void setAssetsDAO(AssetsDAO assetsDAO) {
		this.assetsDAO = assetsDAO;
	}

	public Assets findById(String id) {
		return assetsDAO.findById(id);
	}

}

/**
 * DairyServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-29
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.DairyDAO;
import com.lssrc.pams.domain.Dairy;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.DairyService;

@Service("dairyService")
public class DairyServiceImpl extends BaseServiceImpl implements DairyService {
	private DairyDAO dairyDAO;

	public Dairy findById(Dairy dairy) {
		return dairyDAO.findById(dairy);
	}

	public List<Dairy> findByUser(PamsUser user, int beginIndex, int count) {
		return dairyDAO.findByUser(user, beginIndex, count);
	}

	public Long getCountByUser(PamsUser user) {
		return dairyDAO.getCountByUser(user);
	}
	
	public DairyDAO getDairyDAO() {
		return dairyDAO;
	}

	@Resource
	public void setDairyDAO(DairyDAO dairyDAO) {
		this.dairyDAO = dairyDAO;
	}

}

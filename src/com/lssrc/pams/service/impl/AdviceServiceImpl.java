/**
 * AdviceServiceImpl.java
 *
 * 
 * lssrc.com
 * 2013-02-12
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.AdviceDAO;
import com.lssrc.pams.domain.Advice;
import com.lssrc.pams.service.AdviceService;

@Service("adviceService")
public class AdviceServiceImpl extends BaseServiceImpl implements AdviceService {
	
	private AdviceDAO adviceDAO;
	
	public List<Advice> findAll(int beginIndex, int count) {
		return adviceDAO.findAll(beginIndex, count);
	}
	
	public Advice findById(Integer id) {
		return adviceDAO.findById(id);
	}
	
	public Long getRowCount() {
		return adviceDAO.getRowCount();
	}

	public AdviceDAO getAdviceDAO() {
		return adviceDAO;
	}

	@Resource
	public void setAdviceDAO(AdviceDAO adviceDAO) {
		this.adviceDAO = adviceDAO;
	}

}

/**
 * MemorandumServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-29
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.MemorandumDAO;
import com.lssrc.pams.domain.Memorandum;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.MemorandumService;

@Service("memorandumService")
public class MemorandumServiceImpl extends BaseServiceImpl implements MemorandumService {
	private MemorandumDAO memorandumDAO;

	public Memorandum findById(Memorandum memorandum) {
		return memorandumDAO.findById(memorandum);
	}

	public List<Memorandum> findByUser(PamsUser user, int beginIndex, int count, String state) {
		return memorandumDAO.findByUser(user, beginIndex, count, state);
	}
	
	public List<Memorandum> findByPage(int beginIndex, int count) {
		return memorandumDAO.findByPage(beginIndex, count);
	}
	
	public Long getRowCount() {
		return memorandumDAO.getRowCount();
	}
	
	public Long getCountByUser(PamsUser user, String state) {
		return memorandumDAO.getCountByUser(user, state);
	}

	public MemorandumDAO getMemorandumDAO() {
		return memorandumDAO;
	}

	@Resource
	public void setMemorandumDAO(MemorandumDAO memorandumDAO) {
		this.memorandumDAO = memorandumDAO;
	}

}

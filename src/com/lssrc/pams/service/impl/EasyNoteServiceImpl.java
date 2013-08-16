/**
 * EasyNoteServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-29
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.EasyNoteDAO;
import com.lssrc.pams.domain.EasyNote;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.EasyNoteService;

@Service("easyNoteService")
public class EasyNoteServiceImpl extends BaseServiceImpl implements EasyNoteService {
	private EasyNoteDAO easyNoteDAO;

	public EasyNote findById(EasyNote easyNote) {
		return easyNoteDAO.findById(easyNote);
	}

	public List<EasyNote> findByUser(PamsUser user, int beginIndex, int count) {
		return easyNoteDAO.findByUser(user, beginIndex, count);
	}
	
	public Long getCountByUser(PamsUser user) {
		return easyNoteDAO.getCountByUser(user);
	}

	public EasyNoteDAO getEasyNoteDAO() {
		return easyNoteDAO;
	}

	@Resource
	public void setEasyNoteDAO(EasyNoteDAO easyNoteDAO) {
		this.easyNoteDAO = easyNoteDAO;
	}

}

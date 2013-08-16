/**
 * TransactionRecordServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-31
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.TransactionRecordDAO;
import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.TransactionRecord;
import com.lssrc.pams.service.TransactionRecordService;

@Service("transactionRecordService")
public class TransactionRecordServiceImpl extends BaseServiceImpl implements TransactionRecordService {

	private TransactionRecordDAO transactionRecordDAO;
	
	public TransactionRecord findById(TransactionRecord transactionRecord) {
		return transactionRecordDAO.findById(transactionRecord);
	}

	public List<TransactionRecord> findByAssets(Assets assets, int beginIndex, int count) {
		return transactionRecordDAO.findByAssets(assets, beginIndex, count);
	}

	public List<TransactionRecord> findByUser(PamsUser user, int beginIndex, int count, String type) {
		return transactionRecordDAO.findByUser(user, beginIndex, count, type);
	}
	
	public Long getCountByUser(PamsUser user) {
		return transactionRecordDAO.getCountByUser(user);
	}

	public TransactionRecordDAO getTransactionRecordDAO() {
		return transactionRecordDAO;
	}

	@Resource
	public void setTransactionRecordDAO(TransactionRecordDAO transactionRecordDAO) {
		this.transactionRecordDAO = transactionRecordDAO;
	}

	public String[][] getDataByGroup(String username, String[][] dataSetArray) {
		return transactionRecordDAO.getDataByGroup(username, dataSetArray);
	}

}

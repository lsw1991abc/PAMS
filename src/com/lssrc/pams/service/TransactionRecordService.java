/**
 * TransactionRecordService.java
 * 
 * 
 * lssrc.com
 * 2012-12-27
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.TransactionRecord;

public interface TransactionRecordService extends BaseService {

	public TransactionRecord findById(TransactionRecord transactionRecord);

	public List<TransactionRecord> findByAssets(Assets assets, int beginIndex, int count);

	public List<TransactionRecord> findByUser(PamsUser user, int beginIndex, int count, String type);
	
	public Long getCountByUser(PamsUser user);

	public String[][] getDataByGroup(String username, String[][] dataSetArray);

}

/**
 * TransactionRecordDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.TransactionRecord;

public interface TransactionRecordDAO extends BaseDAO {

	public TransactionRecord findById(TransactionRecord transactionRecord);

	public List<TransactionRecord> findByAssets(Assets assets, int beginIndex, int count);

	public List<TransactionRecord> findByUser(PamsUser user, int beginIndex, int count, String type);
	
	public Long getCountByUser(PamsUser user);

	public String[][] getDataByGroup(String username, String[][] dataSetArray);

}

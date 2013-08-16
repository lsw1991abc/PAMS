/**
 * AddressBookDAO.java
 * 
 * 
 * lssrc.com
 * 2012-12-18
 */
package com.lssrc.pams.dao;

import java.util.List;

import com.lssrc.pams.domain.AddressBook;
import com.lssrc.pams.domain.PamsUser;

public interface AddressBookDAO extends BaseDAO {

	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);

	public AddressBook findById(String uuid);

	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count, String realName);

	public Long getCountByUser(PamsUser user, String realName);

}
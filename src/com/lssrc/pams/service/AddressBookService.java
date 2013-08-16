/**
 * AddressBookService.java
 * 
 * 
 * lssrc.com
 * 2012-12-27
 */
package com.lssrc.pams.service;

import java.util.List;

import com.lssrc.pams.domain.AddressBook;
import com.lssrc.pams.domain.PamsUser;

public interface AddressBookService extends BaseService {

	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count);
	
	public Long getCountByUser(PamsUser user);

	public AddressBook findById(String uuid);

	public List<AddressBook> findByUser(PamsUser pamsUser, int beginIndex, int count, String realName);

	public Long getCountByUser(PamsUser pamsUser, String realName);

}

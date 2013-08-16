/**
 * AddressBookServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-30
 */
package com.lssrc.pams.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.AddressBookDAO;
import com.lssrc.pams.domain.AddressBook;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.AddressBookService;

@Service("addressBookService")
public class AddressBookServiceImpl extends BaseServiceImpl implements AddressBookService {

	private AddressBookDAO addressBookDAO;

	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count) {
		return addressBookDAO.findByUser(user, beginIndex, count);
	}
	
	public Long getCountByUser(PamsUser user) {
		return addressBookDAO.getCountByUser(user);
	}
	
	public Long getCountByUser(PamsUser user, String realName) {
		return addressBookDAO.getCountByUser(user, realName);
	}
	
	public AddressBookDAO getAddressBookDAO() {
		return addressBookDAO;
	}

	@Resource
	public void setAddressBookDAO(AddressBookDAO addressBookDAO) {
		this.addressBookDAO = addressBookDAO;
	}

	public AddressBook findById(String uuid) {
		return addressBookDAO.findById(uuid);
	}

	public List<AddressBook> findByUser(PamsUser user, int beginIndex, int count, String realName) {
		return addressBookDAO.findByUser(user, beginIndex, count, realName);
	}

}

/**
 * AddressBookAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-12
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.AddressBook;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.AddressBookService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("addressBookAction")
@Scope("prototype")
public class AddressBookAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private AddressBookService addressBookService;
	private String msg;
	private int beginIndex;
	private int count;
	private String uuid;
	private String realName;
	private String telephone;
	private String email;
	private String qq;
	private List<AddressBook> addressBooks;

	public String add() {
		msg = addressBookService.save(new AddressBook(UtilFactory.getUuidUtil().getUUID(), realName, telephone, email, qq, this.getUser())) ? "[{result:'success'}]" : "[{result:'error'}]";
		return SUCCESS;
	}

	public String queryById() {
		AddressBook addressBook = addressBookService.findById(uuid);
		if(addressBook != null) {
			String dataMsg = "{";
			dataMsg += "uuid:'" + addressBook.getUuid() + "',";
			dataMsg += "name:'" + addressBook.getRealName() + "',";
			dataMsg += "tel:'" + addressBook.getTelephone() + "',";
			dataMsg += "email:'" + addressBook.getEmail() + "',";
			dataMsg += "qq:'" + addressBook.getQq() + "'";
			dataMsg += "}";
			msg = "[{result:'success'}, " + dataMsg + "]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String update() {
		msg = addressBookService.update(new AddressBook(uuid, realName, telephone, email, qq, this.getUser())) ? "[{result:'success'}]" : "[{result:'error'}]";
		return SUCCESS;
	}
	
	public String queryByUser() {
		Long rowCount = (long) 0;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		try {
			realName = java.net.URLDecoder.decode(realName,"UTF-8");
			if(!"".equalsIgnoreCase(realName) && realName != null) {
				rowCount = addressBookService.getCountByUser(pamsUser, realName);
				addressBooks = addressBookService.findByUser(pamsUser, beginIndex, count, realName);
			} else {
				rowCount = addressBookService.getCountByUser(pamsUser);
				addressBooks = addressBookService.findByUser(pamsUser, beginIndex, count);
			}
			if (addressBooks.size() > 0) {
				dataMsg = "[";
				for (AddressBook addressBook : addressBooks) {
					dataMsg += "{";
					dataMsg += "uuid:'" + addressBook.getUuid() + "',";
					dataMsg += "name:'" + addressBook.getRealName() + "',";
					dataMsg += "tel:'" + addressBook.getTelephone() + "',";
					dataMsg += "email:'" + addressBook.getEmail() + "',";
					dataMsg += "qq:'" + addressBook.getQq() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'" + rowCount +"'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String delete() {
		msg = addressBookService.delete(new AddressBook(uuid)) ? "[{result:'success'}]" : "[{result:'error'}]";
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public PamsUser getUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			return new PamsUser(username);
		}
		return null;
	}

	@Resource
	public void setAddressBookService(AddressBookService addressBookService) {
		this.addressBookService = addressBookService;
	}

}

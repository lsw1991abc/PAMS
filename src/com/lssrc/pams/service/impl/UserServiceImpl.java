/**
 * UserServiceImpl.java
 * 
 * 
 * lssrc.com
 * 2012-12-29
 */
package com.lssrc.pams.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lssrc.pams.dao.UserDAO;
import com.lssrc.pams.dao.impl.UserDAOImpl;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService,UserDetailsService {
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PamsUser pamsUser = userDAO.findByUsername(username);
		if (pamsUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(pamsUser.getAuthority()));
		User user = new User(pamsUser.getUsername(), pamsUser.getPassword(), authorities);
		return user;
	}

	public PamsUser findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	public List<PamsUser> findByPage(int beginIndex, int count, String authority) {
		return userDAO.findByPage(beginIndex, count, authority);
	}
	
	public Long getRowCount(String authority) {
		return userDAO.getRowCount(authority);
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

}
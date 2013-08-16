/**
 * UserAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-10
 */
package com.lssrc.pams.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.UserService;
import com.lssrc.pams.util.PamsConstant;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String username;
	private String password;
	private String realName;
	private String idCard;
	private String telephone;
	private String email;
	private String qq;
	private String birthDate;
	private String birthLocation;
	private String liveLocation;
	private String livePlace;
	private String authority;
	private String signature;
	
	private File uploadify;  
    private String uploadifyContentType;  
    private String uploadifyFileName; 
    
	private String msg;
	private List<PamsUser> users;
	private int beginIndex;
	private int count;

	public String add() {
		if(userService.findByUsername(username) != null) {
			msg = "{[result:'error1']}";
			return SUCCESS;
		}
		Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
		password = md5Encoder.encodePassword(password, null);
		if(userService.save(new PamsUser(username, password, realName, idCard, telephone, email, qq, birthDate, birthLocation, liveLocation, livePlace, UtilFactory.getDateUtil().getRightTime(), "", PamsConstant.ROLE_USER, "icon/pamsdefaulticon.gif"))) {
			msg = "{[result:'success']}";
		} else {
			msg = "{[result:'error']}";
		}
		return SUCCESS;
	}
	
	public String update() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			PamsUser pamsUser = userService.findByUsername(username);
			if(password != null && password.length() != 0 &&password != "") {
				pamsUser.setPassword(password);
			}
			pamsUser.setRealName(realName);
			pamsUser.setIdCard(idCard);
			pamsUser.setLiveLocation(liveLocation);
			pamsUser.setBirthLocation(birthLocation);
			pamsUser.setBirthDate(birthDate);
			pamsUser.setTelephone(telephone);
			pamsUser.setQq(qq);
			pamsUser.setEmail(email);
			userService.update(pamsUser);
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String updateRole() {
		PamsUser pamsUser = userService.findByUsername(username);
		if(pamsUser != null) {
			pamsUser.setAuthority(authority);
			userService.update(pamsUser);
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String updateSignature() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			PamsUser pamsUser = userService.findByUsername(username);
			if(pamsUser != null) {
				System.out.println(signature);
				pamsUser.setSignature(signature);
				userService.update(pamsUser);
				msg = "[{result:'success'}]";
			} else {
				msg = "[{result:'error'}]";
			}
		}
		return SUCCESS;
	}

	public String queryById() {
		try {
			PamsUser pamsUser = userService.findByUsername(username);
			if (pamsUser != null) {
				msg = "[{result:'success'}, {";
				msg += "username:'"+pamsUser.getUsername()+"',";
				msg += "realname:'"+pamsUser.getRealName()+"',";
				msg += "telephone:'"+pamsUser.getTelephone()+"',";
				msg += "qq:'"+pamsUser.getQq()+"',";
				msg += "email:'"+pamsUser.getEmail()+"',";
				msg += "liveLocation:'"+pamsUser.getLiveLocation()+"'";
				msg += "}]";
			} else {
				msg = "[{result:'error'}, {value:'未找到'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}, {value:'异常'}]";
		}
		return SUCCESS;
	}
	
	public String queryMyInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			PamsUser pamsUser = userService.findByUsername(username);
			msg = "[{result:'success'}, {";
			msg += "realName:'"+pamsUser.getRealName()+"',";
			msg += "idCard:'"+pamsUser.getIdCard()+"',";
			msg += "liveLocation:'"+pamsUser.getLiveLocation()+"',";
			msg += "livePlace:'"+pamsUser.getLivePlace()+"',";
			msg += "birthLocation:'"+pamsUser.getBirthLocation()+"',";
			msg += "birthday:'"+pamsUser.getBirthDate()+"',";
			msg += "telephone:'"+pamsUser.getTelephone()+"',";
			msg += "qq:'"+pamsUser.getQq()+"',";
			msg += "email:'"+pamsUser.getEmail()+"'";
			msg += "}]";
		} else {
			msg = "[{result:'error'}, {value:'未找到'}]";
		}
		return SUCCESS;
	}

	public String queryByPage() {
		Long rowCount;
		String dataMsg="[]";
		try {
			rowCount = userService.getRowCount(authority);
			users = userService.findByPage(beginIndex, count, authority);
			if (users.size() > 0) {
				dataMsg = "[";
				for (PamsUser pamsUser : users) {
					dataMsg += "{";
					dataMsg += "username:'"+pamsUser.getUsername()+"',";
					dataMsg += "realname:'"+pamsUser.getRealName()+"',";
					dataMsg += "telephone:'"+pamsUser.getTelephone()+"',";
					dataMsg += "qq:'"+pamsUser.getQq()+"',";
					dataMsg += "email:'"+pamsUser.getEmail()+"',";
					dataMsg += "liveLocation:'"+pamsUser.getLiveLocation()+"'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length()-1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'"+rowCount+"'}], data:"+dataMsg+"}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String updateIcon() {
		String usernameString = this.getUsername();
		if(UtilFactory.getFileUtil().uploadFile(uploadify, ServletActionContext.getServletContext().getRealPath("/user/icon"), usernameString + uploadifyFileName.substring(uploadifyFileName.lastIndexOf(".")))) {
			PamsUser pamsUser = userService.findByUsername(usernameString);
			pamsUser.setIcon("icon/" + usernameString + uploadifyFileName.substring(uploadifyFileName.lastIndexOf(".")));
			userService.update(pamsUser);
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		if(userService.delete(new PamsUser(username))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String userIndex() {
		PamsUser pamsUser = userService.findByUsername(this.getUsername());
		msg = "[{signature:'" + pamsUser.getSignature() + "', icon:'" + pamsUser.getIcon() + "'}]";
		return SUCCESS;
	}

	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = "";
		}
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	public String getLiveLocation() {
		return liveLocation;
	}

	public void setLiveLocation(String liveLocation) {
		this.liveLocation = liveLocation;
	}

	public String getLivePlace() {
		return livePlace;
	}

	public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyContentType() {
		return uploadifyContentType;
	}

	public void setUploadifyContentType(String uploadifyContentType) {
		this.uploadifyContentType = uploadifyContentType;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

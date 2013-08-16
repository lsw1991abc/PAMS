/**
 * User
 * 用户
 * 
 * lssrc.com
 * 2012-12-13
 */
package com.lssrc.pams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pams_user")
public class PamsUser extends BasePOJO {

	private static final long serialVersionUID = 1L;
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
	private String registerTime;
	private String signature;
	private String authority;
	private String icon;
	
	/**
	 * 扩展字段
	 * 留给发送消息用
	 */
	private String tempStr1;

	public PamsUser() {
		super();
	}

	public PamsUser(String username) {
		super();
		this.username = username;
	}

	public PamsUser(String username, String password, String realName, String idCard, String telephone, String email, String qq,
			String birthDate, String birthLocation, String liveLocation, String livePlace, String registerTime, String signature, String authority, String icon) {
		super();
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.idCard = idCard;
		this.telephone = telephone;
		this.email = email;
		this.qq = qq;
		this.birthDate = birthDate;
		this.birthLocation = birthLocation;
		this.liveLocation = liveLocation;
		this.livePlace = livePlace;
		this.registerTime = registerTime;
		this.signature = signature;
		this.authority = authority;
		this.icon = icon;
	}

	@Id
	@Column(length = 32)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length = 32, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 15)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(length = 18)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(length = 15)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length = 25)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 12)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(length = 10)
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Column(length = 50)
	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	@Column(length = 50)
	public String getLiveLocation() {
		return liveLocation;
	}

	public void setLiveLocation(String liveLocation) {
		this.liveLocation = liveLocation;
	}

	@Column(length = 50)
	public String getLivePlace() {
		return livePlace;
	}

	public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
	}

	@Column(length = 20, nullable = false)
	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Column(length = 200)
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(length = 40)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Column(length = 200)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(length = 40)
	public String getTempStr1() {
		return tempStr1;
	}

	public void setTempStr1(String tempStr1) {
		this.tempStr1 = tempStr1;
	}

}
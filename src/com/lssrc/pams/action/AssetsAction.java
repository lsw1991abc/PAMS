/**
 * AssetsAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-14
 */
package com.lssrc.pams.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.AssetsType;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.AssetsService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("assetsAction")
@Scope("prototype")
public class AssetsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private AssetsService assetsService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private Integer typeId;
	private String account;
	private Double balance;
	private PamsUser user;
	private List<Assets> assetss;

	public String add() {
		try {
			account = java.net.URLDecoder.decode(account,"UTF-8");
			if (assetsService.save(new Assets(UtilFactory.getUuidUtil().getUUID(), new AssetsType(typeId), account, balance, this.getUser()))) {
				msg = "[{result:'success'}]";
			} else {
				msg = "[{result:'error'}]";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryByUser() {
		String dataMsg = "[]";
		try {
			assetss = assetsService.findByUser(this.getUser());
			if (assetss.size() > 0) {
				dataMsg = "[";
				for (Assets assets : assetss) {
					dataMsg += "{";
					dataMsg += "id:'" + assets.getId() + "',";
					dataMsg += "account:'" + assets.getAccount() + "',";
					dataMsg += "balance:'" + assets.getBalance() + "',";
					dataMsg += "type:'" + assets.getAssetsType().getName() + "',";
					dataMsg += "sort:'" + assets.getAssetsType().getSort() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'0'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		try {
			Assets assets = assetsService.findById(id);
			if(assets != null) {
				String dataMsg = "{";
				dataMsg += "id:'" + assets.getId() + "',";
				dataMsg += "account:'" + assets.getAccount() + "',";
				dataMsg += "balance:'" + assets.getBalance() + "',";
				dataMsg += "type:'" + assets.getAssetsType().getName() + "',";
				dataMsg += "sort:'" + assets.getAssetsType().getSort() + "'";
				dataMsg += "}";
				msg = "[{result:'success'}, " + dataMsg + "]";
			} else {
				msg = "[{result:'error'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		if (assetsService.delete(new Assets(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public PamsUser getUser() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			user = new PamsUser(username);
		} 
		return user;
	}

	public void setUser(PamsUser user) {
		this.user = user;
	}

	public List<Assets> getAssetss() {
		return assetss;
	}

	public void setAssetss(List<Assets> assetss) {
		this.assetss = assetss;
	}

	@Resource
	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

}

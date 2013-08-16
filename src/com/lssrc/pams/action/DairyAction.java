/**
 * EasyNoteAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-11
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Dairy;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.DairyService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("dairyAction")
@Scope("prototype")
public class DairyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private DairyService dairyService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private String time;
	private PamsUser user;
	private List<Dairy> dairies;

	public String add() {
		String uuid = UtilFactory.getUuidUtil().getUUID();
		String time = UtilFactory.getDateUtil().getRightTime();
		if (dairyService.save(new Dairy(uuid, title, content, time, this.getUser()))) {
			msg = "[{result:'success'}, {id:'"+uuid+"', title:'"+title+"', time:'"+time+"'}]";
		} else {
			msg = "[{result:'error'}, {title:'异常，请联系管理员'}]";
		}
		return SUCCESS;
	}

	public String delete() {
		if (dairyService.delete(new Dairy(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String update() {
		if (dairyService.update(new Dairy(id, title, content, time, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryByUser() {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser user = this.getUser();
		try {
			rowCount = dairyService.getCountByUser(user);
			dairies = dairyService.findByUser(user, beginIndex, count);
			if (dairies.size() > 0) {
				dataMsg = "[";
				for (Dairy dairy : dairies) {
					dataMsg += "{";
					dataMsg += "id:'" + dairy.getId() + "',";
					dataMsg += "title:'" + dairy.getTitle() + "',";
					dataMsg += "time:'" + dairy.getTime().substring(0, 10) + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'" + rowCount + "'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'错误'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		Dairy dairy = new Dairy(id);
		try {
			dairy = dairyService.findById(dairy);
			if (dairy != null) {
				msg = "[{result:'success'}, {";
				msg += "title:'" + dairy.getTitle() + "',";
				msg += "content:'" + dairy.getContent() + "',";
				msg += "time:'" + dairy.getTime() + "'";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<Dairy> getDairies() {
		return dairies;
	}

	public void setDairies(List<Dairy> dairies) {
		this.dairies = dairies;
	}

	@Resource
	public void setDairyService(DairyService dairyService) {
		this.dairyService = dairyService;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

/**
 * AdviceAction.java
 *
 * 
 * lssrc.com
 * 2013-02-12
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Advice;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.AdviceService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("adviceAction")
@Scope("prototype")
public class AdviceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String msg;
	private int beginIndex;
	private int count;
	private AdviceService adviceService;
	private List<Advice> advices;
	private Integer id;
	private String content;
	private PamsUser user;
	
	public String add() {
		if(adviceService.save(new Advice(content, UtilFactory.getDateUtil().getRightTime(), this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String queryById() {
		try {
			Advice advice = adviceService.findById(id);
			if(advice != null) {
				String dataMsg = "{";
				dataMsg += "id:'" + advice.getId() + "',";
				dataMsg += "time:'" + advice.getTime() + "',";
				dataMsg += "content:'" + advice.getContent() + "'";
				dataMsg += "}";
				msg = "[{resultMsg:'success'}, " + dataMsg + "]";
			} else {
				msg = "[{resultMsg:'error'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{resultMsg:'error'}]";
		}
		System.out.println(msg);
		return SUCCESS;
	}
	
	public String queryByPage() {
		Long rowCount;
		String dataMsg = "[]";
		try {
			rowCount = adviceService.getRowCount();
			advices = adviceService.findAll(beginIndex, count);
			if (advices.size() > 0) {
				dataMsg = "[";
				for (Advice advice : advices) {
					dataMsg += "{";
					dataMsg += "id:'" + advice.getId() + "',";
					dataMsg += "time:'" + advice.getTime() + "',";
					dataMsg += "username:'" + advice.getPamsUser().getUsername() + "',";
					dataMsg += "content:'" + advice.getContent() + "'";
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
		System.out.println(msg);
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Resource
	public void setAdviceService(AdviceService adviceService) {
		this.adviceService = adviceService;
	}

	public List<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(List<Advice> advices) {
		this.advices = advices;
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

}

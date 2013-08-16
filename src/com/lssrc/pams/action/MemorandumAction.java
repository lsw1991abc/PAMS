/**
 * EasyNoteAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-11
 */
package com.lssrc.pams.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Memorandum;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.MemorandumService;
import com.lssrc.pams.util.PamsConstant;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("memorandumAction")
@Scope("prototype")
public class MemorandumAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private MemorandumService memorandumService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private String time;
	private String state;
	private PamsUser user;
	private List<Memorandum> memorandums;

	public String add() {
		if (memorandumService.save(new Memorandum(UtilFactory.getUuidUtil().getUUID(), title, content, time, PamsConstant.MEMORANDUM_ING, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String queryByPage() {
		Long rowCount;
		String dataMsg="[]";
		try {
			rowCount = memorandumService.getRowCount();
			memorandums = memorandumService.findByPage(beginIndex, count);
			if (memorandums.size() > 0) {
				dataMsg = "[";
				for (Memorandum memorandum : memorandums) {
					dataMsg += "{";
					dataMsg += "id:'"+memorandum.getId()+"',";
					dataMsg += "title:'"+memorandum.getTitle()+"',";
					dataMsg += "content:'"+memorandum.getContent()+"',";
					dataMsg += "time:'"+memorandum.getTime()+"',";
					dataMsg += "state:'"+memorandum.getState()+"'";
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
	
	public String queryByUser() throws UnsupportedEncodingException {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		state = java.net.URLDecoder.decode(state, "UTF-8");
		System.out.println(state);
		try {
			rowCount = memorandumService.getCountByUser(pamsUser, "".equalsIgnoreCase(state) ? null : state);
			memorandums = memorandumService.findByUser(pamsUser, beginIndex, count, "".equalsIgnoreCase(state) ? null : state);
			if (memorandums.size() > 0) {
				dataMsg = "[";
				for (Memorandum memorandum : memorandums) {
					dataMsg += "{";
					dataMsg += "id:'" + memorandum.getId() + "',";
					dataMsg += "title:'" + memorandum.getTitle() + "',";
					dataMsg += "state:'" + memorandum.getState() + "',";
					dataMsg += "time:'" + memorandum.getTime() + "'";
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

	public String delete() {
		if (memorandumService.delete(new Memorandum(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String update() {
		if (memorandumService.update(new Memorandum(id, title, content, time, PamsConstant.MEMORANDUM_ING, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		Memorandum memorandum = new Memorandum(id);
		try {
			memorandum = memorandumService.findById(memorandum);
			if (memorandum != null) {
				msg = "[{result:'success'}, {";
				msg += "id:'" + memorandum.getId() + "',";
				msg += "title:'" + memorandum.getTitle() + "',";
				msg += "content:'" + memorandum.getContent() + "',";
				msg += "state:'" + memorandum.getState() + "',";
				msg += "time:'" + memorandum.getTime() + "'";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getMemorandumId() {
		return id;
	}

	public void setMemorandumId(String memorandumId) {
		this.id = memorandumId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public List<Memorandum> getMemorandums() {
		return memorandums;
	}

	public void setMemorandums(List<Memorandum> memorandums) {
		this.memorandums = memorandums;
	}

	@Resource
	public void setMemorandumService(MemorandumService memorandumService) {
		this.memorandumService = memorandumService;
	}

}

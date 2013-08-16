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

import com.lssrc.pams.domain.EasyNote;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.EasyNoteService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("easyNoteAction")
@Scope("prototype")
public class EasyNoteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private EasyNoteService easyNoteService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private PamsUser user;
	private List<EasyNote> easyNotes;

	public String add() {
		String uuid = UtilFactory.getUuidUtil().getUUID();
		if (easyNoteService.save(new EasyNote(uuid, title, content, this.getUser()))) {
			msg = "[{result:'success'}, {id:'"+uuid+"', title:'"+title+"'}]";
		} else {
			msg = "[{result:'error'}, {title:'异常，请联系管理员'}]";
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (easyNoteService.delete(new EasyNote(id))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}, {title:'异常，请联系管理员'}]";
		}
		return SUCCESS;
	}
	
	public String update() {
		if (easyNoteService.update(new EasyNote(id, title, content, this.getUser()))) {
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}, {title:'异常，请联系管理员'}]";
		}
		return SUCCESS;
	}
	
	public String queryByUser() {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		try {
			rowCount = easyNoteService.getCountByUser(pamsUser);
			easyNotes = easyNoteService.findByUser(pamsUser, beginIndex, count);
			if (easyNotes.size() > 0) {
				dataMsg = "[";
				for (EasyNote easyNote : easyNotes) {
					dataMsg += "{";
					dataMsg += "id:'" + easyNote.getId() + "',";
					dataMsg += "title:'" + easyNote.getTitle() + "',";
					dataMsg += "content:'" + easyNote.getContent() + "'";
					dataMsg += "},";
				}
				dataMsg = dataMsg.substring(0, dataMsg.length() - 1);
				dataMsg += "]";
			}
			msg = "{result:[{resultMsg:'success',rowCount:'" + rowCount +"'}], data:" + dataMsg + "}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{result:[{resultMsg:'error',errorMsg:'异常，请联系管理员'}], 'data':[]}";
		}
		return SUCCESS;
	}
	
	public String queryById() {
		EasyNote easyNote = new EasyNote(id);
		try {
			easyNote = easyNoteService.findById(easyNote);
			if (easyNote != null) {
				msg = "[{result:'success'}, {";
				msg += "id:'" + easyNote.getId() + "',";
				msg += "title:'" + easyNote.getTitle() + "',";
				msg += "content:'" + easyNote.getContent() + "'";
				msg += "}]";
			} else {
				msg = "[{result:'error'}, {value:'未找到'}]";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "[{result:'error'}, {value:'异常，请联系管理员'}]";
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

	public void setUsername(PamsUser user) {
		this.user = user;
	}

	@Resource
	public void setEasyNoteService(EasyNoteService easyNoteService) {
		this.easyNoteService = easyNoteService;
	}

}

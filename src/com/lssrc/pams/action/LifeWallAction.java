/**
 * LifeWallAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-14
 */
package com.lssrc.pams.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.LifeWall;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.service.LifeWallService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("lifeWallAction")
@Scope("prototype")
public class LifeWallAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private LifeWallService lifeWallService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String content;
	private List<LifeWall> lifeWalls;
	private PamsUser user;

	public String add() {
		String time = UtilFactory.getDateUtil().getRightTime().substring(0, 10);
		String uuid = UtilFactory.getUuidUtil().getUUID();
		if (lifeWallService.save(new LifeWall(uuid, title, time, content,  this.getUser()))) {
			msg = "[{result:'success'}, {id:'"+uuid+"', title:'"+title+"', time:'"+time+"'}]";
		} else {
			msg = "[{result:'error'}, {title:'异常，请联系管理员'}]";
		}
		return SUCCESS;
	}

	public String queryByPage() {
		Long rowCount;
		String dataMsg = "[]";
		try {
			rowCount = lifeWallService.getRowCount();
			lifeWalls = lifeWallService.findByPage(beginIndex, count);
			if (lifeWalls.size() > 0) {
				dataMsg = "[";
				for (LifeWall lifeWall : lifeWalls) {
					dataMsg += "{";
					dataMsg += "id:'" + lifeWall.getId() + "',";
					dataMsg += "title:'" + lifeWall.getTitle() + "',";
					dataMsg += "time:'" + lifeWall.getTime() + "',";
					dataMsg += "content:'" + lifeWall.getContent() + "'";
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
		LifeWall lifeWall = new LifeWall(id);
		try {
			lifeWall = lifeWallService.findById(lifeWall);
			if (lifeWall != null) {
				msg = "[{result:'success'}, {";
				msg += "title:'" + lifeWall.getTitle() + "',";
				msg += "content:'" + lifeWall.getContent() + "',";
				msg += "username:'" + lifeWall.getUser().getUsername() + "',";
				msg += "time:'" + lifeWall.getTime() + "'";
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

	public String queryByRandom() {
		String dataMsg = "[]";
		try {
			lifeWalls = lifeWallService.findByRandom(count);
			if (lifeWalls.size() > 0) {
				dataMsg = "[";
				for (LifeWall lifeWall : lifeWalls) {
					dataMsg += "{";
					dataMsg += "id:'" + lifeWall.getId() + "',";
					dataMsg += "title:'" + lifeWall.getTitle() + "',";
					dataMsg += "content:'" + lifeWall.getContent() + "',";
					dataMsg += "time:'" + lifeWall.getTime() + "'";
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

	public String delete() {
		if (lifeWallService.delete(new LifeWall(id))) {
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

	@Resource
	public void setLifeWallService(LifeWallService lifeWallService) {
		this.lifeWallService = lifeWallService;
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

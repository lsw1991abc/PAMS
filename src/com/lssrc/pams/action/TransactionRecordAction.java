/**
 * TransactionRecordAction.java
 * 
 * 
 * lssrc.com
 * 2013-01-14
 */
package com.lssrc.pams.action;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lssrc.pams.domain.Assets;
import com.lssrc.pams.domain.PamsUser;
import com.lssrc.pams.domain.TransactionRecord;
import com.lssrc.pams.service.AssetsService;
import com.lssrc.pams.service.TransactionRecordService;
import com.lssrc.pams.util.UtilFactory;
import com.opensymphony.xwork2.ActionSupport;

@Controller("transactionRecordAction")
@Scope("prototype")
public class TransactionRecordAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private TransactionRecordService transactionRecordService;
	private AssetsService assetsService;
	private String msg;
	private int beginIndex;
	private int count;
	private String id;
	private String title;
	private String type;
	private String time;
	private String content;
	private Double amount;
	private String assetsId;
	private PamsUser user;
	private List<TransactionRecord> transactionRecords;
	
	private SimpleDateFormat simpleDateFormat;
	private Font font;

	public String add() {
		Assets assets = new Assets(assetsId);
		if (transactionRecordService.save(new TransactionRecord(UtilFactory.getUuidUtil().getUUID(), title, type, assets, time, content, amount, this.getUser()))) {
			if (amount != 0.0) {
				assets = assetsService.findById(assetsId);
				assets.setBalance(assets.getBalance() + (amount * Integer.parseInt(type)));
				assetsService.update(assets);
			}
			msg = "[{result:'success'}]";
		} else {
			msg = "[{result:'error'}]";
		}
		return SUCCESS;
	}

	public String queryByUser() {
		Long rowCount;
		String dataMsg = "[]";
		PamsUser pamsUser = this.getUser();
		Assets assets;
		try {
			rowCount = transactionRecordService.getCountByUser(pamsUser);
			transactionRecords = transactionRecordService.findByUser(pamsUser, beginIndex, count, "".equalsIgnoreCase(type) ? null : type);
			if (transactionRecords.size() > 0) {
				dataMsg = "[";
				for (TransactionRecord transactionRecord : transactionRecords) {
					dataMsg += "{";
					dataMsg += "id:'" + transactionRecord.getId() + "',";
					dataMsg += "title:'" + transactionRecord.getTitle() + "',";
					dataMsg += "type:'" + transactionRecord.getType() + "',";
					assets = transactionRecord.getAssets();
					dataMsg += "assetsId:'" + assets.getAccount() + "',";
					dataMsg += "assetsAccount:'" + assets.getAccount() + "',";
					dataMsg += "amount:'" + transactionRecord.getAmount() + "',";
					dataMsg += "content:'" + transactionRecord.getContent() + "',";
					dataMsg += "time:'" + transactionRecord.getTime() + "'";
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
		TransactionRecord transactionRecord = transactionRecordService.findById(new TransactionRecord(id));
		if(transactionRecord != null) {
			Assets assets = transactionRecord.getAssets();
			assets = assetsService.findById(transactionRecord.getAssets().getId());
			assets.setBalance(assets.getBalance() - (transactionRecord.getAmount() * Integer.parseInt(transactionRecord.getType()) ));
			assetsService.update(assets);
			if (transactionRecordService.delete(transactionRecord)) {
				msg = "[{result:'success'}]";
			} else {
				msg = "[{result:'error'}]";
			}
		} else {
			msg = "[{result:'success'}]";
		}
		return SUCCESS;
	}
	
	public String update() {
		TransactionRecord transactionRecord = transactionRecordService.findById(new TransactionRecord(id));
		if(transactionRecord != null) {
			Assets assets = transactionRecord.getAssets();
			
			assets = assetsService.findById(transactionRecord.getAssets().getId());
			assets.setBalance(assets.getBalance() - (transactionRecord.getAmount() * Integer.parseInt(transactionRecord.getType()) ));
			assetsService.update(assets);
			
			assets = assetsService.findById(assetsId);
			assets.setBalance(assets.getBalance() + (amount * Integer.parseInt(type)));
			assetsService.update(assets);
			
			transactionRecord.setAmount(amount);
			transactionRecord.setContent(content);
			transactionRecord.setTime(time);
			transactionRecord.setUser(this.getUser());
			transactionRecord.setType(type);
			transactionRecord.setTitle(title);
			transactionRecord.setAssets(new Assets(assetsId));
			
			if (transactionRecordService.update(transactionRecord)) {
				msg = "[{result:'success'}]";
			} else {
				msg = "[{result:'error'}]";
			}
		}
		return SUCCESS;
	}
	
	public String queryById() {
		TransactionRecord transactionRecord = new TransactionRecord(id);
		Assets assets;
		try {
			transactionRecord = transactionRecordService.findById(transactionRecord);
			if (transactionRecord != null) {
				msg = "[{result:'success'}, {";
				msg += "id:'" + transactionRecord.getId() + "',";
				msg += "title:'" + transactionRecord.getTitle() + "',";
				msg += "type:'" + transactionRecord.getType() + "',";
				assets = transactionRecord.getAssets();
				msg += "assetsId:'" + assets.getId() + "',";
				msg += "assetsAccount:'" + assets.getAccount() + "',";
				msg += "amount:'" + transactionRecord.getAmount() + "',";
				msg += "content:'" + transactionRecord.getContent() + "',";
				msg += "time:'" + transactionRecord.getTime() + "'";
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
	
	public String showPicByUser() {
		String username = this.getUser().getUsername();
		font = new Font("宋体", Font.BOLD, 14);
		simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
		String beforeDay = null;
		Calendar calendar = new GregorianCalendar();
		String[][] dataSetArray = new String[5][3];
		
		for (int i = 4; i >= 0; i--) {
			beforeDay = simpleDateFormat.format(calendar.getTime());
			dataSetArray[i][0] = "0";
			dataSetArray[i][1] = "pams";
			dataSetArray[i][2] = beforeDay;
			calendar.add(Calendar.DATE,   -1);
		}
		dataSetArray = transactionRecordService.getDataByGroup(username, dataSetArray);
		if(dataSetArray != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < dataSetArray.length; i++) {
				if("null".equalsIgnoreCase(dataSetArray[i][0]) || dataSetArray[i][0] == null) {
					dataSetArray[i][0] = "0";
				}
				dataset.addValue(Double.parseDouble(dataSetArray[i][0]), dataSetArray[i][1], dataSetArray[i][2]);
			}
			JFreeChart chart = ChartFactory.createBarChart(null, "时间", "金额", dataset, PlotOrientation.VERTICAL, false, false, false);
			if (chart != null) {
				try {
					CategoryPlot plot = (CategoryPlot) chart.getPlot();
					plot.getDomainAxis().setLabelFont(font);
			        plot.getRangeAxis().setLabelFont(font);
			        String fileName = username + "_" + UtilFactory.getDateUtil().getTimeStamp() + ".png";
					OutputStream outputStream = new FileOutputStream(ServletActionContext.getServletContext().getRealPath("/user/transactionpic") + "/" + fileName);
					ChartUtilities.writeChartAsPNG(outputStream, chart, 680, 190);
					msg = "[{result:'success'}, {fileName:'" + fileName + "'}]";
				} catch (Exception e) {
					e.printStackTrace();
					msg = "[{result:'error'}]";
				}
			} else {
				msg = "[{result:'error'}]";
			}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Resource
	public void setTransactionRecordService(TransactionRecordService transactionRecordService) {
		this.transactionRecordService = transactionRecordService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content =content;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}

	@Resource
	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

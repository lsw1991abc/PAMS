//设置应用页脚
function setAppFoot(rowCount, pageNo, pageCount, additive) {
	$("#appFoot").html("共搜索到&nbsp;<span>" + rowCount + "</span>&nbsp;条记录&nbsp;&nbsp;当前&nbsp;<span>" + pageNo + "</span>/<span>" + pageCount + "</span>&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;<span class='page_selector' value='1'>首页</span>&nbsp;<span class='page_selector' value='"+parseInt(pageNo-1)+"'>上一页</span>&nbsp;<span class='page_selector' value='"+parseInt(parseInt(pageNo)+1)+"'>下一页</span>&nbsp;<span class='page_selector' value='"+pageCount+"'>末页</span>");
	$("#appFoot .page_selector").each(function (){
		$(this).click(function() {
			if(pageNo != $(this).attr("value")) {
				pageNo = $(this).attr("value");
				if(pageNo >= 1 && pageNo <= pageCount) {
					query_page(pageNo, additive);
				}
			}
		});
	});
}
//显示dairy
function showDairy(dataArray) {
	$(dataArray).each(function(i, item) {
		$("<li class='item'><span class='item_title' style='width:150px;' itemId='"+item.id+"'>"+item.title+"</span><span class='item_time' style='width:85px;'>"+item.time+"</span></li>").appendTo("div.dairy div.module_content ul");
	});
	$("div.dairy div.module_content ul li:gt(0)").each(function() {
		$(this).children("span").first().click(function() {
			setShadePosition();
			$("#shade_content p span.shade_content_title").html("加载中...");
			var itemId = $(this).attr("itemId");
			var item;
			var title;
			var content;
			var time;
			// AJAX GET
			$.getJSON("query_dairy_id", {id:itemId}, function(data) {
				var itemInfo = eval(data.msg);
				if("success" == itemInfo[0].result) {
					item = itemInfo[1];
					title = item.title;
					content = item.content;
					time = item.time;
					
					$("#shade_content p span.shade_content_title").html(title);
					
					/*信息详情*/
					$("<p><label>时间：</label><span class='text'>" + time + "</span></p>").appendTo("#shade_content div");
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p><label>内容：</label><span class='direction content'>" + content + "</span></p>").appendTo("#shade_content div");
					/*按钮*/
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p class='btnGroup1'><input type='button' value='修改' class='btnL' id='update' /><input type='button' value='删除' class='btn' id='delete' /></p>").appendTo("#shade_content div");
					$("<p class='btnGroup2' style='display:none'><input type='button' value='保存' class='btnL' id='save' /><input type='button' value='取消' class='btn' id='cancel' /></p>").appendTo("#shade_content div");
					//修改按钮
					$("#shade_content p #update").click(function() {
						$("#shade_content p span.shade_content_title").replaceWith("<input type='text' class='shade_content_title' name='title' value='" + title + "' />");									
						$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
						$("#shade_content p.btnGroup1").hide();
						$("#shade_content p.btnGroup2").show();
					});
					//保存按钮
					$("#shade_content p #save").click(function() {
						title = $.trim($("#shade_content p input.shade_content_title").val());
						content = $.trim($("#shade_content p textarea.content").val());
						if(title.length>=3&&title.length<=20) {
							if(content.length>=3&&content.length<=300) {
								// AJAX POST
								$.post("update_dairy", {id:itemId, title:title, time:time, content:content}, function(data) {
									if((eval(data.msg))[0].result=="success") {
										$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");									
										$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
										$("#shade_content p.btnGroup1").show();
										$("#shade_content p.btnGroup2").hide();
									} else {
										alert("修改失败");
									}
								});
							} else {
								alert("内容长度3~300");
							}
						} else {
							alert("标题长度3~20");
						}
						
					});
					//取消按钮
					$("#shade_content p #cancel").click(function() {
						$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");						
						$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
						$("#shade_content p.btnGroup1").show();
						$("#shade_content p.btnGroup2").hide();
					});
					//删除按钮
					$("#shade_content p #delete").click(function() {
						// AJAX POST
						$.post("delete_dairy", {id:itemId}, function(data) {
							if((eval(data.msg))[0].result=="success") {
								alert("删除成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("删除失败");
							}
						});
					});
					/*end 信息详情*/
				}
			});
		});
	});
}
//显示easyNote
function showEasyNote(dataArray) {
	$(dataArray).each(function(i, item) {
		$("<li class='item'><span class='item_title' style='width:270px;' itemId='"+item.id+"'>"+item.title+"</span></li>").appendTo("div.easyNote div.module_content ul");
	});
	$("div.easyNote div.module_content ul li:gt(0)").each(function() {
		$(this).children("span").first().click(function() {
			setShadePosition();
			$("#shade_content p span.shade_content_title").html("加载中...");
			var itemId = $(this).attr("itemId");
			var item;
			var title;
			var content;
			$.getJSON("query_easyNote_id", {id:itemId}, function(data) {
				var itemInfo = eval(data.msg);
				if("success" == itemInfo[0].result) {
					item = itemInfo[1];
					title = item.title;
					content = item.content;
					$("#shade_content p span.shade_content_title").html(title);
					
					/*信息详情*/					
					$("<p><label>记事内容：</label><span class='direction content'>" + content + "</span></p>").appendTo("#shade_content div");					
					/*按钮*/
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p class='btnGroup1'><input type='button' value='修改' class='btnL' id='update' /><input type='button' value='删除' class='btn' id='delete' /></p>").appendTo("#shade_content div");
					$("<p class='btnGroup2' style='display:none'><input type='button' value='保存' class='btnL' id='save' /><input type='button' value='取消' class='btn' id='cancel' /></p>").appendTo("#shade_content div");
					//修改按钮
					$("#shade_content p #update").click(function() {
						$("#shade_content p span.shade_content_title").replaceWith("<input type='text' class='shade_content_title' name='title' value='" + title + "' />");						
						$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
						$("#shade_content p.btnGroup1").hide();
						$("#shade_content p.btnGroup2").show();
					});
					//保存按钮
					$("#shade_content p #save").click(function() {
						title = $.trim($("#shade_content p input.shade_content_title").val());
						content = $.trim($("#shade_content p textarea.content").val());
						if(title.length>=3&&title.length<=20) {
							if(content.length>=3&&content.length<=300) {
								// AJAX POST
								$.post("update_easyNote", {id:itemId, title:title, content:content}, function(data) {
									if((eval(data.msg))[0].result=="success") {
										$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");												
										$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
										$("#shade_content p.btnGroup1").show();
										$("#shade_content p.btnGroup2").hide();
									} else {
										alert("修改失败");
									}
								});
							} else {
								alert("内容长度3~300");
							}
						} else {
							alert("标题长度3~20");
						}
					});
					//取消按钮
					$("#shade_content p #cancel").click(function() {
						$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");					
						$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
						$("#shade_content p.btnGroup1").show();
						$("#shade_content p.btnGroup2").hide();
					});
					//删除按钮
					$("#shade_content p #delete").click(function() {
						// AJAX POST
						$.post("delete_easyNote", {id:itemId}, function(data) {
							if((eval(data.msg))[0].result == "success") {
								alert("删除成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("删除失败");
							}
						});
					});
					/*end 信息详情*/
				}
			});
		});
	});
}
//显示memorandum
function showMemorandum(dataArray) {
	$(dataArray).each(function(i, item) {
		$("<li class='item'><span class='item_title' style='width:198px;' itemId='"+item.id+"'>"+item.title+"</span><span class='item_time'>"+item.time+"</span></li>").appendTo("div.memorandum div.module_content ul");
	});
	$("div.memorandum div.module_content ul li:gt(0)").each(function() {
		$(this).children("span").first().click(function() {
			setShadePosition();
			$("#shade_content p span.shade_content_title").html("加载中...");
			var itemId = $(this).attr("itemId");
			// AJAX GET
			$.getJSON("query_memorandum_id", {id:itemId}, function(data) {
				var itemInfo = eval(data.msg);
				var item;
				var title;
				var content;
				var state;
				var time;
				if("success" == itemInfo[0].result) {
					item = itemInfo[1];
					title = item.title;
					content = item.content;
					state = item.state;
					time = item.time;
					
					$("#shade_content p span.shade_content_title").html(title);
					
					/*信息详情*/
					$("<p><label>时间：</label><span class='text time'>" + time + "</span><label>状态：</label><span class='text state' style='width:100px;'>" + state + "</span></p>").appendTo("#shade_content div");
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p><label>内容：</label><span class='direction content'>" + content + "</span></p>").appendTo("#shade_content div");
					/*按钮*/
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p class='btnGroup1'><input type='button' value='修改' class='btnL' id='update' /><input type='button' value='删除' class='btn' id='delete' /></p>").appendTo("#shade_content div");
					$("<p class='btnGroup2' style='display:none'><input type='button' value='保存' class='btnL' id='save' /><input type='button' value='取消' class='btn' id='cancel' /></p>").appendTo("#shade_content div");
					//修改按钮
					$("#shade_content p #update").click(function() {
						$("#shade_content p span.shade_content_title").replaceWith("<input type='text' class='shade_content_title' name='title' value='" + title + "' />");
						$("#shade_content p span.time").replaceWith("<input type='text' class='text time' name='time' value='" + time + "' readonly />");				
						$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
						$("#shade_content p.btnGroup1").hide();
						$("#shade_content p.btnGroup2").show();
						$("#shade_content p input.time").datetimepicker();
					});
					//保存按钮
					$("#shade_content p #save").click(function() {
						title = $.trim($("#shade_content p input.shade_content_title").val());
						time = $.trim($("#shade_content p input.time").val());
						content = $.trim($("#shade_content p textarea.content").val());
						if(title.length>=3&&title.length<=20) {
							if(content.length>=3&&content.length<=300) {
								// AJAX POST
								$.post("update_memorandum", {id:itemId, title:title, time:time, content:content}, function(data) {
									if((eval(data.msg))[0].result=="success") {
										$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
										$("#shade_content p input.time").replaceWith("<span class='text time' name='time'>" + time + "</span>");
										$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
										$("#shade_content p.btnGroup1").show();
										$("#shade_content p.btnGroup2").hide();
									} else {
										alert("修改失败");
									}
								});
							} else {
								alert("内容长度3~300");
							}
						} else {
							alert("标题长度3~20");
						}
					});
					//取消按钮
					$("#shade_content p #cancel").click(function() {
						$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
						$("#shade_content p input.time").replaceWith("<span class='text time' name='time'>" + time + "</span>");
						$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
						$("#shade_content p.btnGroup1").show();
						$("#shade_content p.btnGroup2").hide();
					});
					//删除按钮
					$("#shade_content p #delete").click(function() {
						// AJAX POST
						$.post("delete_memorandum", {id:itemId}, function(data) {
							if((eval(data.msg))[0].result == "success") {
								alert("删除成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("删除失败");
							}
						});
					});
					/*end 信息详情*/
				}
			});
		});
	});
}
//显示task
function showTask(dataArray) {
	$(dataArray).each(function(i, item) {
		$("<li class='item'><span class='item_title' style='width:145px;' itemId='"+item.id+"'>"+item.title+"</span><span class='item_time'>"+item.startTime+"</span><span class='item_time'>"+item.endTime+"</span></li>").appendTo("div.task div.module_content ul");
	});
	$("div.task div.module_content ul li:gt(0)").each(function() {
		$(this).children("span").first().click(function() {
			setShadePosition();
			$("#shade_content p span.shade_content_title").html("加载中...");
			var itemId = $(this).attr("itemId");
			// AJAX GET
			$.getJSON("query_task_id", {id:itemId}, function(data) {
				var itemInfo = eval(data.msg);
				var item;
				var title;
				var startTime;
				var endTime;
				var content;
				var state;
				if("success" == itemInfo[0].result) {
					item = itemInfo[1];
					title = item.title;
					startTime = item.startTime;
					endTime = item.endTime;
					content = item.content;
					state = item.state;
					
					$("#shade_content p span.shade_content_title").html(title);
					
					/*信息详情*/
					$("<p><label>开始时间：</label><span class='text startTime'>" + startTime + "</span><label>结束时间：</label><span class='text endTime'>" + endTime + "</span><label>完成情况：</label><span class='text'>" + state + "</span></p>").appendTo("#shade_content div");
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p><label>任务内容：</label><span class='direction content'>" + content + "</span></p>").appendTo("#shade_content div");
					/*按钮*/
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p class='btnGroup1'><input type='button' value='修改' class='btnL' id='update' /><input type='button' value='删除' class='btn' id='delete' /></p>").appendTo("#shade_content div");
					$("<p class='btnGroup2' style='display:none'><input type='button' value='保存' class='btnL' id='save' /><input type='button' value='取消' class='btn' id='cancel' /></p>").appendTo("#shade_content div");
					//修改按钮
					$("#shade_content p #update").click(function() {
						$("#shade_content p span.shade_content_title").replaceWith("<input type='text' class='shade_content_title' name='title' value='" + title + "' />");
						$("#shade_content p span.startTime").replaceWith("<input type='text' class='text startTime' name='startTime' value='" + startTime + "' readonly />");				
						$("#shade_content p span.endTime").replaceWith("<input type='text' class='text endTime' name='endTime' value='" + endTime + "' readonly />");
						$("#shade_content p input.startTime").datetimepicker({
						      defaultDate: "+1w",
						      changeMonth: true,
						      numberOfMonths: 1,
						      onClose: function( selectedDate ) {
						    	  $("#shade_content p input.endTime").datepicker( "option", "minDate", selectedDate );
						      }
						    });
						$("#shade_content p input.endTime").datetimepicker({
						      defaultDate: "+1w",
						      changeMonth: true,
						      numberOfMonths: 1,
						      onClose: function( selectedDate ) {
						    	  $("#shade_content p input.startTime").datepicker( "option", "maxDate", selectedDate );
						      }
						});
						$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
						$("#shade_content p.btnGroup1").hide();
						$("#shade_content p.btnGroup2").show();
					});
					//保存按钮
					$("#shade_content p #save").click(function() {
						title = $.trim($("#shade_content p input.shade_content_title").val());
						startTime = $.trim($("#shade_content p input.startTime").val());
						endTime = $.trim($("#shade_content p input.endTime").val());
						content = $.trim($("#shade_content p textarea.content").val());
						if(title.length>=3&&title.length<=20) {
							if(content.length>=3&&content.length<=300) {
								if(startTime!=""&&endTime!="") {
									// AJAX POST
									$.post("update_task", {id:itemId, title:title, startTime:startTime, endTime:endTime, content:content}, function(data) {
										if((eval(data.msg))[0].result=="success") {
											$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
											$("#shade_content p input.startTime").replaceWith("<span class='text startTime' name='startTime'>" + startTime + "</span>");				
											$("#shade_content p input.endTime").replaceWith("<span class='text endTime' name='endTime'>" + endTime + "</span>");		
											$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
											$("#shade_content p.btnGroup1").show();
											$("#shade_content p.btnGroup2").hide();
										} else {
											alert("修改失败");
										}
									});
								} else {
									alert("开始时间和结束时间不能为空");
								}
							} else {
								alert("内容长度3~300");
							}
						} else {
							alert("标题长度3~20");
						}
					});
					//取消按钮
					$("#shade_content p #cancel").click(function() {
						$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
						$("#shade_content p input.startTime").replaceWith("<span class='text startTime' name='startTime'>" + startTime + "</span>");	
						$("#shade_content p input.endTime").replaceWith("<span class='text endTime' name='endTime'>" + endTime + "</span>");		
						$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
						$("#shade_content p.btnGroup1").show();
						$("#shade_content p.btnGroup2").hide();
					});
					//删除按钮
					$("#shade_content p #delete").click(function() {
						// AJAX POST
						$.post("delete_task", {id:itemId}, function(data) {
							if((eval(data.msg))[0].result=="success") {
								alert("删除成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("删除失败");
							}
						});
					});
					/*end 信息详情*/	
				}
			});
		});
	});
}
//显示transactionRecord
function showTransactionRecord(dataArray) {
	$(dataArray).each(function(i, item) {
		$("<li class='item'><span class='item_title' style='width:225px;' itemId='"+item.id+"'>"+item.title+"</span><span class='item_time'>"+item.amount+"</span><span class='item_time'>"+item.time+"</span></li>").appendTo("div.transaction div.module_content ul");
	});
	$("div.transaction div.module_content ul li:gt(0)").each(function() {
		$(this).children("span").first().click(function() {
			setShadePosition();				
			$("#shade_content p span.shade_content_title").html("加载中...");
			var itemId = $(this).attr("itemId");	
			var item;
			var title;
			var time;
			var type;
			var assetsId;
			var assetsAccount;
			var amount;
			var content;
			$.getJSON("query_transactionRecord_id", {id:itemId}, function(data) {
				var itemInfo = eval(data.msg);
				if("success" == itemInfo[0].result) {
					item = itemInfo[1];
					title = item.title;
					time = item.time;
					type = item.type;
					assetsId = item.assetsId;
					assetsAccount = item.assetsAccount;
					amount = item.amount;
					content = item.content;
					
					$("#shade_content p span.shade_content_title").html(title);			
					
					/*信息详情*/
					$("<p><label>时间：</label><span style='width:300px;' class='text time'>" + time + "</span><label>交易类型：</label><span class='text type'>" + (type == "1"? "收入" : "支出") + "</span></p>").appendTo("#shade_content div");
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p> <label>交易账号：</label><span style='width:300px;' class='text assets'>" + assetsAccount + "</span><label>交易金额：</label><span class='text amount'>" + amount + "</span></p>").appendTo("#shade_content div");
					$("<br class='spacer' />").appendTo("#shade_content div");										
					$("<p><label>交易详情：</label><span class='direction content'>" + content + "</span></p>").appendTo("#shade_content div");
	
					/*按钮*/
					$("<br class='spacer' />").appendTo("#shade_content div");
					$("<p class='btnGroup1'><input type='button' value='修改' class='btnL' id='update' /><input type='button' value='删除' class='btn' id='delete' /></p>").appendTo("#shade_content div");
					$("<p class='btnGroup2' style='display:none'><input type='button' value='保存' class='btnL' id='save' /><input type='button' value='取消' class='btn' id='cancel' /></p>").appendTo("#shade_content div");
					//修改按钮
					$("#shade_content p #update").click(function() {
						$("#shade_content p span.shade_content_title").replaceWith("<input type='text' class='shade_content_title' name='title' value='" + title + "' />");
						$("#shade_content p span.time").replaceWith("<input type='text' style='width:300px;' class='text time' name='time' value='" + time + "' readonly />");				
						$("#shade_content p input.time").datetimepicker();
						if("1" == type) {
							$("#shade_content p span.type").replaceWith("<select class='text type'><option value='-1'>支出</option><option selected='true' value='1'>收入</option></select>");
						} else {
							$("#shade_content p span.type").replaceWith("<select class='text type'><option selected='true' value='-1'>支出</option><option value='1'>收入</option></select>");
						}
						$("#shade_content p span.assets").replaceWith("<select style='width:300px;' class='assets'><option value='" + assetsId + "' selected='true'>" + assetsAccount + "</option></select>");
						$("#shade_content p span.amount").replaceWith("<input type='text' class='text amount' style='width:200px;' value='" + amount + "' />");
						$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
						$("#shade_content p.btnGroup1").hide();
						$("#shade_content p.btnGroup2").show();
						$.getJSON("query_assets_user", function(data) {
							var msgArray = eval("("+data.msg+")");
				      		var resultArray = msgArray.result;
				      		if(resultArray[0].resultMsg == "success") {
				      			var dataArray = msgArray.data;
				      			if(dataArray.length!=0) {
				      				$(dataArray).each(function(index, item) {
				          				$("<option value='"+item.id+"'>"+item.type+"---"+item.account+"</option>").appendTo("#shade_content p select.assets");
				          			});
				      			}
				      		} else {
				      			alert("账号获取失败");
				      		}
						});
					});
					//保存按钮
					$("#shade_content p #save").click(function() {
						title = $.trim($("#shade_content p input.shade_content_title").val());
						time = $.trim($("#shade_content p input.time").val());
						type = $.trim($("#shade_content p select.type").val());
						assetsId = $.trim($("#shade_content p select.assets").val());
						assetsAccount = $.trim($("#shade_content p select.assets option:selected").html());
						amount = $.trim($("#shade_content p input.amount").val());
						content = $.trim($("#shade_content p textarea.content").val());
						if(title.length>=3&&title.length<=20) {
							if(time!="") {
								if(assetsId!="") {
									if($.isNumeric(amount)) {
										if(content.length<=150) {
											// AJAX POST
											$.post("update_transactionRecord", {id:itemId, title:title, time:time, type:type, assetsId:assetsId, amount:amount, content:content}, function(data) {
												if((eval(data.msg))[0].result=="success") {
													$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
													$("#shade_content p input.time").replaceWith("<span style='width:300px;' class='text time'>" + time + "</span>");				
													$("#shade_content p select.type").replaceWith("<span class='text type'>" + (type == "1"? "收入" : "支出") + "</span>");	
													$("#shade_content p select.assets").replaceWith("<span style='width:300px;' class='text assets'>" + assetsAccount + "</span>");
													$("#shade_content p input.amount").replaceWith("<span class='text amount'>" + amount + "</span>");
													$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
													$("#shade_content p.btnGroup1").show();
													$("#shade_content p.btnGroup2").hide();
												} else {
													alert("修改失败");
												}
											});
										} else {
											alert("详情长度<=150");
										}
									} else {
										alert("金额为数字");
									}
								} else {
									alert("请选择账号");
								}
							} else {
								alert("时间不能为空");
							}
						} else {
							alert("标题长度3~20");
						}
					});
					//取消按钮
					$("#shade_content p #cancel").click(function() {					
						$("#shade_content p input.shade_content_title").replaceWith("<span class='shade_content_title'>" + title + "</span>");
						$("#shade_content p input.time").replaceWith("<span style='width:300px;' class='text time'>" + time + "</span>");				
						$("#shade_content p select.type").replaceWith("<span class='text type'>" + (type == "1"? "收入" : "支出") + "</span>");	
						$("#shade_content p select.assets").replaceWith("<span style='width:300px;' class='text assets'>" + assetsAccount + "</span>");
						$("#shade_content p input.amount").replaceWith("<span class='text amount'>" + amount + "</span>");
						$("#shade_content p textarea.content").replaceWith("<span class='direction content'>" + content + "</span>");
						$("#shade_content p.btnGroup1").show();
						$("#shade_content p.btnGroup2").hide();
					});
					//删除按钮
					$("#shade_content p #delete").click(function() {
						// AJAX POST
						$.post("delete_transactionRecord", {id:itemId}, function(data) {
							if((eval(data.msg))[0].result=="success") {
								alert("删除成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("删除失败");
							}
						});
					});
					/*end 信息详情*/
				}
			});
		});
	});
}
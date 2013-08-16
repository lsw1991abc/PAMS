$(function() {
	$("#userPanel").html("");
	
	$("<h2>我的交易记录</h2>").appendTo("#userPanel");
	
	$("<div id='appHead'></div>").appendTo("#userPanel");
	
	$("<span class='all'>刷新</span>").appendTo("#appHead");	
	$("<span class='new'>新记录</span>").appendTo("#appHead");
	$("<br class='spacer' /><img src='../image/loading.gif' id='transactionPic' style='margin:0;padding:0;' />").appendTo("#appHead");
	$.getJSON("showPicByUser", function(data) {
		var resultArray = eval(data.msg);
		if(resultArray[0].result == "success") {
			$("#transactionPic").attr("src","transactionpic/"+resultArray[1].fileName).css("width","680px").css("height","190px");
		} else {
			alert("图像生成失败");
			$("#transactionPic").attr("src","../image/index_bg1.png").css("width","680px").css("height","190px");
		}
	});
	$("#appHead").css("height","240px");
	//////////////////////////////////////
	$("#appHead span.all").click(function() {
		query_page(1, "");
	});
	$("#appHead span.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新交易记录");
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>时间：</label> <input type='text' class='text time' style='300px;' readonly /><label>交易类型：</label><select class='type'><option value='-1'>支出</option><option value='1'>收入</option></select></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>交易账号：</label><select style='width:300px;' class='assets'><option value='1'>加载中...</option></select><label>交易金额：</label><input type='text' class='text amount' style='width:200px;' maxlength='10' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>交易详情：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
		$("#shade_content p input.time").datetimepicker();
		$.getJSON("query_assets_user", function(data) {
			var msgArray = eval("("+data.msg+")");
      		var resultArray = msgArray.result;
      		if(resultArray[0].resultMsg == "success") {
      			var assetsOptionStr;
      			var dataArray = msgArray.data;
      			if(dataArray.length==0) {
      				assetsOptionStr = "<option value=''>您还没有账号</option>";
      			} else {
      				assetsOptionStr="";
      				$(dataArray).each(function(index, item) {
          				assetsOptionStr+="<option value='"+item.id+"'>"+item.type+"---"+item.account+"</option>";
          			});
      			}
      			$("#shade_content p select.assets").html(assetsOptionStr);
      		} else {
      			alert("账号获取失败");
      		}
		});
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		//保存按钮
		$("#shade_content p #save").click(function() {	
			var title = $.trim($("#shade_content p input.title").val());
			var time = $.trim($("#shade_content p input.time").val());
			var type = $.trim($("#shade_content p select.type").val());
			var assetsId = $.trim($("#shade_content p select.assets").val());
			var amount = $.trim($("#shade_content p input.amount").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(time!="") {
					if(assetsId!="") {
						if($.isNumeric(amount)) {
							if(content.length<=150) {
								// AJAX POST
								$.post("add_transactionRecord", {title:title,time:time, type:type, assetsId:assetsId, amount:amount, content:content}, function(data) {
									if((eval(data.msg))[0].result=="success") {
										alert("添加成功");
										$("#shade_content").remove();
										$("#shade").remove();
									} else {
										alert("添加失败");
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
		/* end 信息详情*/
	});
	//////////////////////////////////////
	$("<div id='appBody'><ul></ul></div>").appendTo("#userPanel");	
	$("<div id='appFoot'></div>").appendTo("#userPanel");
	
	query_page(1, "");
	
});

function query_page(pageNo, additive) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;
	$.getJSON("query_transactionRecord_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize, type:additive}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody ul").html("<li><span style='width:100px;'>标题</span><span class='item_time'>时间</span><span class='item_icon'>交易类型</span><span class='item_tel'>交易金额</span><span class='item_time'>交易账户</span><span class='item_tel'>备注</span></li>");
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_title' itemId='" + item.id + "' style='width:100px;'>" + item.title + "</span><span class='item_time'>" + item.time + "</span><span class='item_icon'>" + (item.type == "1"? "收入" : "支出") + "</span><span class='item_tel'>" + item.amount + "</span><span class='item_time'>" + item.assetsAccount + "</span><span class='item_tel'>" + item.content + "</span></li>").appendTo("#appBody ul");
			});
			$("#appBody ul li:gt(0)").each(function() {
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
								$("#shade_content p span.assets").replaceWith("<select style='width:300px;' class='assets'><option value='" + assetsId + "'>" + assetsAccount + "</option></select>");
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
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			pageCount = pageCount == 0 ? 1 : pageCount;
			setAppFoot(rowCount, pageNo, pageCount, additive);
		}
	});
}
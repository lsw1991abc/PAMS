var taskAJAXGet;
var transactionAJAXGet;
var memorandumAJAXGet;
$(function() {
	//生活墙添加按钮
	$("div.lifeWall div.module_title a.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("生活墙，说两句~~~");
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:600px;' /></p>").appendTo("#shade_content div");		
		$("<br class='spacer' />").appendTo("#shade_content div");		
		$("<p><label>内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		//保存按钮
		$("#shade_content p #save").click(function() {
			var title = $.trim($("#shade_content p input.title").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(content.length>=3&&content.length<=300) {
					// AJAX POST
					$.post("add_lifeWall", {title:title, content:content}, function(data) {
						if((eval(data.msg))[0].result == "success") {
							alert("添加成功");
							$("#shade_content").remove();
							$("#shade").remove();
						} else {
							alert("添加失败");
						}
					});
				} else {
					alert("内容长度3~300");
				}
			} else {
				alert("标题长度3~20");
			}
		});
	});
	/*随机加载生活墙*/
	$("div.lifeWall div.module_content").html("<ul><li class='item'><span class='item_head_title' style='width:330px;'>概要</span><span class='item_time'>时间</span></li></ul><img src='../image/loading.gif' />");
	$.getJSON("query_lifeWall_random", {count:5}, function(data) {
		var msgArray = eval("("+data.msg+")");
		var resultArray = msgArray.result;
		var dataArray = msgArray.data;
		$("div.lifeWall div.module_content").html("<ul><li class='item'><span class='item_head_title' style='width:330px;'>概要</span><span class='item_time'>时间</span></li></ul>");
		if(resultArray[0].resultMsg == "success") {
			$(dataArray).each(function(i, item) {
				$("<li class='item'><span class='item_title' style='width:330px;' itemId='"+item.id+"'>"+item.title+"</span><span class='item_time'>"+item.time+"</span></li>").appendTo("div.lifeWall div.module_content ul");
			});
		} else {
			alert("生活墙信息提取失败");
		}
		
		$("div.lifeWall div.module_content ul li:gt(0)").each(function() {
			$(this).children("span").first().click(function() {
				var itemId = $(this).attr("itemId");
				setShadePosition();
				$("#shade_content p span.shade_content_title").html("正在加载...");
				// AJAX GET
				$.getJSON("query_lifeWall_id", {id:itemId}, function(data) {
					var itemInfo = eval(data.msg);
					if("success" == itemInfo[0].result) {
						$("#shade_content p span.shade_content_title").html(itemInfo[1].title);
						/*信息详情*/
						$("<p><label>时间：</label><span class='text time'>" + itemInfo[1].time + "</span></p>").appendTo("#shade_content div");
						$("<br class='spacer' />").appendTo("#shade_content div");
						$("<p><label>内容：</label><span class='direction content'>" + itemInfo[1].content + "</span></p>").appendTo("#shade_content div");
						/*end 信息详情*/
					}
				});
			});
		});
	});
	//加载备忘录
	queryMemorandum("进行中");
	//加载任务
	queryTask("进行中");
	//加载交易记录
	queryTransaction("");
	
	
	//新生活记事
	$("div.dairy div.module_title a.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新生活记事");
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");		
		$("<p><label>内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		//保存按钮
		$("#shade_content p #save").click(function() {
			var title = $.trim($("#shade_content p input.title").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(content.length>=3&&content.length<=300) {
					// AJAX POST
					$.post("add_dairy", {title:title, content:content}, function(data) {
						if($.trim((eval(data.msg))[0].result) == "success") {
							alert("添加成功");
							$("#shade_content").remove();
							$("#shade").remove();
						} else {
							alert("添加失败");
						}
					});
				} else {
					alert("内容长度3~300");
				}
			} else {
				alert("标题长度3~20");
			}
		});
		/* end 信息详情*/
	});
	$("div.dairy div.module_content").html("<ul><li class='item'><span style='width:150px;'>标题</span><span class='item_time' style='width:85px;'>时间</span></li></ul><img src='../image/loading.gif' />");
	/*生活记事加载*/
	$.getJSON("query_dairy_user", {beginIndex:0,count:5}, function(data) {
		var msgArray = eval("("+data.msg+")");
		$("div.dairy div.module_content").html("<ul><li class='item'><span style='width:150px;'>标题</span><span class='item_time' style='width:85px;'>时间</span></li></ul>");
		showDairy(msgArray.data);
	});
	/*简易记事本*/
	$("div.easyNote div.module_title a.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新简易记事");
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:600px;' /></p>").appendTo("#shade_content div");		
		$("<br class='spacer' />").appendTo("#shade_content div");		
		$("<p><label>记事内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		//保存按钮
		$("#shade_content p #save").click(function() {
			var title = $.trim($("#shade_content p input.title").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(content.length>=3&&content.length<=300) {
					// AJAX POST
					$.post("add_easyNote", {title:title, content:content}, function(data) {
						var messageArray = eval(data.msg);
						if(messageArray[0].result == "success") {
							alert("添加成功");
							$("#shade_content").remove();
							$("#shade").remove();
						} else {
							alert("添加失败");
						}
					});
				} else {
					alert("内容长度3~300");
				}
			} else {
				alert("标题长度3~20");
			}
		});
	});
	$("div.easyNote div.module_content").html("<ul><li class='item'><span style='width:270px;'>标题</span></li></ul><img src='../image/loading.gif' />");
	/*简易记事本加载*/
	$.getJSON("query_easyNote_user", {beginIndex:0,count:5}, function(data) {
		var msgArray = eval("("+data.msg+")");
		$("div.easyNote div.module_content").html("<ul><li class='item'><span style='width:270px;'>标题</span></li></ul>");
		showEasyNote(msgArray.data) ;
	});
	//选项卡切换
	$(".module_title span").each(function() {
		$(this).click(function() {
			$(this).addClass("focus").siblings("span").removeClass("focus");
			var action = $(this).attr("value").split("|")[0];
			var type = $(this).attr("value").split("|")[1];
			if(action=="transaction") {
				queryTransaction(type);
			} else if(action == "task") {
				queryTask(type);
			} else if(action == "memorandum") {
				queryMemorandum(type);
			}
		});
	});
	
});
function queryMemorandum(additive){
	$("div.memorandum div.module_content").html("<ul><li class='item'><span style='width:198px;'>标题</span><span class='item_time'>提醒时间</span></li></ul><img src='../image/loading.gif' />");
	if(memorandumAJAXGet != null) {
		memorandumAJAXGet.abort();
	}
	/*备忘录加载*/
	memorandumAJAXGet = $.getJSON("query_memorandum_user", {beginIndex:0,count:5, state:encodeURIComponent(additive)}, function(data) {
		var msgArray = eval("("+data.msg+")");
		$("div.memorandum div.module_content").html("<ul><li class='item'><span style='width:198px;'>标题</span><span class='item_time'>提醒时间</span></li></ul>");
		showMemorandum(msgArray.data);
	});
}
function queryTask(additive) {
	$("div.task div.module_content").html("<ul><li class='item'><span style='width:145px;'>标题</span><span class='item_time'>开始时间</span><span class='item_time'>结束时间</span></li></ul><img src='../image/loading.gif' />");
	if(taskAJAXGet != null) {
		taskAJAXGet.abort();
	}
	/*任务加载*/
	taskAJAXGet = $.getJSON("query_task_user", {beginIndex:0, count:5, state:encodeURIComponent(additive)}, function(data) {
		var msgArray = eval("("+data.msg+")");
		$("div.task div.module_content").html("<ul><li class='item'><span style='width:145px;'>标题</span><span class='item_time'>开始时间</span><span class='item_time'>结束时间</span></li></ul>");
		showTask(msgArray.data);
	});
}
function queryTransaction(additive) {
	$("div.transaction div.module_content").html("<ul><li class='item'><span style='width:225px;'>标题</span><span class='item_time'>交易金额</span><span class='item_time'>时间</span></li></ul><img src='../image/loading.gif' />");
	if(transactionAJAXGet != null) {
		transactionAJAXGet.abort();
	}
	/*交易记录加载*/
	transactionAJAXGet = $.getJSON("query_transactionRecord_user", {beginIndex:0,count:5, type:additive}, function(data) {
		var msgArray = eval("("+data.msg+")");
		$("div.transaction div.module_content").html("<ul><li class='item'><span style='width:225px;'>标题</span><span class='item_time'>交易金额</span><span class='item_time'>时间</span></li></ul>");
		showTransactionRecord(msgArray.data);
	});
}

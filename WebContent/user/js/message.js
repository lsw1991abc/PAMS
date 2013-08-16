$(function() {
	$("#userPanel").html("");
	
	$("<h2>我的信箱</h2>").appendTo("#userPanel");
	
	//////////////////////////////////////
	$("#appHead span.receive").click(function() {		
		query_page(1, "2");
	});
	$("#appHead span.send").click(function() {
		query_page(1, "1");
	});
	//////////////////////////////////////
	$("<div id='appBody'><ul></ul></div>").appendTo("#userPanel");
	$("<div id='appFoot'></div>").appendTo("#userPanel");
	
	query_page(1, "2");
});

function query_page(pageNo, additive) {
	$("#appBody ul").html("<li><span style='width:280px;'>标题</span><span class='item_time'>时间</span><span class='item_time'>发送人</span></li>");
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;
	/*
	if(additive == "1") {
		$.getJSON("query_message_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
			msgArray = eval("("+data.msg+")");
		});
	} else {
		$.getJSON("query_messageState_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
			msgArray = eval("("+data.msg+")");
		});
	}
	*/
	$.getJSON("query_messageState_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_title' style='width:280px;' itemId='" + item.id + "'>" + item.title + "</span><span class='item_time'>" + item.time + "</span><span class='item_time'>" + item.sender + "</span></li>").appendTo("#appBody ul");
			});
			$("#appBody ul li:gt(0)").each(function() {
				$(this).children("span").first().click(function() {
					var itemId = $(this).attr("itemId");
					$.getJSON("query_message_id", {id:itemId}, function(data) {
						var itemInfo = eval(data.msg);
						if("success" == itemInfo[0].result) {
							var item = itemInfo[1];
							setShadePosition();
							$("#shade_content p span.shade_content_title").html(item.title);
							/*信息详情*/
							$("<p><label>发送人：</label><span class='text'>" + item.sender + "</span><label>发送时间：</label><span class='text'>" + item.time + "</span></p>").appendTo("#shade_content div");
							$("<br class='spacer' />").appendTo("#shade_content div");
							$("<p><label>信息内容：</label><span class='direction'>" + item.content + "</span></p>").appendTo("#shade_content div");
							/*按钮*/
							$("<br class='spacer' />").appendTo("#shade_content div");
							$("<p class='btnGroup1'><input type='button' value='删除' class='btnL' id='delete' /></p>").appendTo("#shade_content div");					
							//删除按钮
							$("#shade_content p #delete").click(function() {
								// AJAX POST
								$.post("delete_messageState", {messageId:itemId}, function(data) {
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
						} else {
							alert("获取失败");
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


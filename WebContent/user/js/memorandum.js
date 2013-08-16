$(function() {
	$("#userPanel").html("");
	
	$("<h2>备忘录</h2>").appendTo("#userPanel");
	
	$("<div id='appHead'></div>").appendTo("#userPanel");
	$("<span class='new'>新备忘</span>").appendTo("#appHead");
	//////////////////////////////////////
	$("#appHead span.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新备忘");
		
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>时间：</label><input type='text' class='text time' readonly /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		$("#shade_content p input.time").datetimepicker();
		//保存按钮
		$("#shade_content p #save").click(function() {
			var title = $.trim($("#shade_content p input.title").val());
			var time = $.trim($("#shade_content p input.time").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(content.length>=3&&content.length<=300) {
					// AJAX POST
					$.post("add_memorandum", {title:title, time:time, content:content}, function(data) {
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
		/* end 信息详情*/
	});
	$("#appHead span.option").click(function() {
		location.href="/pams/user/options.jsp";
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
	$.getJSON("query_memorandum_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize, state:additive}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody ul").html("<li><span style='width:400px;'>标题</span><span class='item_time'>提醒时间</span></li>");
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_title' style='width:400px;' itemId='" + item.id + "'>" + item.title + "</span><span class='item_time'>" + item.time + "</span></li>").appendTo("#appBody ul");
			});
			$("#appBody ul li:gt(0)").each(function() {
				$(this).children("span").first().click(function() {
					setShadePosition();
					$("#shade_content p span.shade_content_title").html("正在加载...");
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
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			pageCount = pageCount == 0 ? 1 : pageCount;
			setAppFoot(rowCount, pageNo, pageCount, additive);
		}
	});
}
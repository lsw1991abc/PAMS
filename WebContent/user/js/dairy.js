$(function() {
	$("#userPanel").html("");
	
	$("<h2>生活记事</h2>").appendTo("#userPanel");
	
	$("<div id='appHead'></div>").appendTo("#userPanel");
	$("<span class='new'>新记录</span>").appendTo("#appHead");
	//////////////////////////////////////
	$("#appHead span.new").click(function() {
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
							alert("保存成功");
							$("#shade_content").remove();
							$("#shade").remove();
						} else {
							alert("保存失败");
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
	//////////////////////////////////////
	$("<div id='appBody'><ul></ul></div>").appendTo("#userPanel");
	$("<div id='appFoot'></div>").appendTo("#userPanel");
	
	query_dairy_page(1, "");
});

function query_dairy_page(pageNo, additive) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;
	$.getJSON("query_dairy_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody ul").html("<li><span style='width:500px;'>标题</span><span class='item_time'>时间</span></li>");
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_title' style='width:500px;' itemId='" + item.id + "'>" + item.title + "</span><span class='item_time'>" + item.time + "</span></li>").appendTo("#appBody ul");
			});
			$("#appBody ul li:gt(0)").each(function() {
				$(this).children("span").first().click(function() {
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
							
							setShadePosition();
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
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			pageCount = pageCount == 0 ? 1 : pageCount;
			setAppFoot(rowCount, pageNo, pageCount, additive);
		}
	});
}
$(function() {
	$("#userPanel").html("");
	
	$("<h2>任务列表</h2>").appendTo("#userPanel");
	
	$("<div id='appHead'></div>").appendTo("#userPanel");
	$("<span class='all'>所有任务</span>").appendTo("#appHead");
	$("<span class='ing'>进行中</span>").appendTo("#appHead");
	$("<span class='end'>已结束</span>").appendTo("#appHead");
	$("<span class='new'>新任务</span>").appendTo("#appHead");
	//////////////////////////////////////
	$("#appHead span.all").click(function() {
		query_page(1, "");
	});
	$("#appHead span.ing").click(function() {
		query_page(1, "进行中");
	});
	$("#appHead span.end").click(function() {
		query_page(1, "已结束");
	});
	$("#appHead span.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新任务");
		
		/*信息详情*/
		$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>开始时间：</label><input type='text' class='text startTime' readonly /><label>结束时间：</label><input type='text' class='text endTime' readonly /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>任务内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
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
		
		/*按钮*/
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
		//保存按钮
		$("#shade_content p #save").click(function() {
			var title = $.trim($("#shade_content p input.title").val());
			var startTime = $.trim($("#shade_content p input.startTime").val());
			var endTime = $.trim($("#shade_content p input.endTime").val());
			var content = $.trim($("#shade_content p textarea.content").val());
			if(title.length>=3&&title.length<=20) {
				if(content.length>=3&&content.length<=300) {
					if(startTime!=""&&endTime!="") {
						// AJAX POST
						$.post("add_task", {title:title, startTime:startTime, endTime:endTime, content:content}, function(data) {
							if((eval(data.msg))[0].result == "success") {
								alert("添加成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("添加失败");
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
	$.getJSON("query_task_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize, state:encodeURIComponent(additive)}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody ul").html("<li><span style='width:290px;'>标题</span><span class='item_time'>开始时间</span><span class='item_time'>结束时间</span><span class='item_icon'>任务状态</span></li>");
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_title' style='width:290px;' itemId='" + item.id + "'>" + item.title + "</span><span class='item_time'>" + item.startTime + "</span><span class='item_time'>" + item.endTime + "</span><span class='item_icon'>" + item.state + "</span></li>").appendTo("#appBody ul");
			});
			$("#appBody ul li:gt(0)").each(function() {
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
								$("#shade_content p span.content").replaceWith("<textarea class='direction content' name='content'>" + content + "</textarea>");
								$("#shade_content p.btnGroup1").hide();
								$("#shade_content p.btnGroup2").show();
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
										alert("添加成功");
										$("#shade_content").remove();
										$("#shade").remove();
									} else {
										alert("添加失败");
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
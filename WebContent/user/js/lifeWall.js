$(function() {
	$("#userPanel").html("");
	$("<h2>生活墙</h2>").appendTo("#userPanel");
	$("<div id='appHead'></div>").appendTo("#userPanel");
	$("<span class='refresh'>刷新</span>").appendTo("#appHead");
	$("<span class='new'>说两句~~</span>").appendTo("#appHead");
	////////////////////////////////////////////
	$("#appHead span.refresh").click(function() {
		query_lifeWall_page();
	});
	$("#appHead span.new").click(function() {
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
	///////////////////////////////////////////////
	$("<div id='appBody'></div>").appendTo("#userPanel");
	$("<div id='appFoot'>&nbsp;</div>").appendTo("#userPanel");
	query_lifeWall_page();
});

function query_lifeWall_page() {
	var msgArray;
	var resultArray;
	var dataArray;
	$.getJSON("query_lifeWall_random", {count:30}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody").empty();
			$.each(dataArray, function(i, item) {
				$("<p class='lwItem' style='top:" + Math.random() * 385 + "px; left:" + Math.random() * 595 + "px; z-index:" + Math.random() * 99 + ";'><b class='lwItem_title'>" + item.title + "</b><b class='lwItem_body'>" + item.content + "</b><b class='lwItem_foot'>" + item.time + "</b></p>").appendTo("#appBody");
			});
			//窗口移动
			var mouseDialogWidth = 0;
			var mouseDialogHeight = 0;
			var appBodyX = $("#appBody").offset().left;
			var appBodyY = $("#appBody").offset().top;
			var dialogX = 0;
			var dialogY = 0;
			var ableMaxX = 0;
			var ableMaxY = 0;					
			$("#appBody p").each(function() {
				var dialog = $(this);				
				$(dialog).children("b.lwItem_title").mousedown(function(event) {
					$(dialog).addClass("focus").siblings().removeClass("focus");
					dialogX = dialog.offset().left;
					dialogY = dialog.offset().top;
					mouseDialogWidth = event.pageX - dialogX;
					mouseDialogHeight = event.pageY - dialogY;
					$(this).mousemove(function(event) {
						dialogX = parseInt(event.pageX - appBodyX - mouseDialogWidth);
						if(dialogX < 0) {
							dialogX = 0;
						}
						dialogY = parseInt(event.pageY - appBodyY - mouseDialogHeight);
						if(dialogY < 0) {
							dialogY = 0;
						}
						ableMaxX = parseInt($("#appBody").width()-dialog.width()) - 2;
						if(dialogX > ableMaxX) {
							dialogX = ableMaxX;
						}
						ableMaxY = parseInt($("#appBody").height()-dialog.height()) - 2;
						if(dialogY > ableMaxY) {
							dialogY = ableMaxY;
						}
						$(dialog).css("left",dialogX+"px").css("top",dialogY+"px");
					});
				}).mouseup(function() {
					$(this).unbind("mousemove");
				}).mouseout(function() {
					$(this).unbind("mousemove");
				});
			});
		}
	});
}
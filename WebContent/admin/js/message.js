function add_message_view() {
	$("#main-content-body").html("");
	$("#main-content-body").append("<p><label for='title' class='label'>标题：</label><span><input type='text' name='title' id='title' style='height:25px; width:230px;' /></span><span style='width:20px; color:#F00; text-align:center;'>*</span><span>标题长度3~28</span></p><br class='spacer' />");
    $("#main-content-body").append("<p><label for='content' class='label'>消息内容：</label><span  style='height:230px;'><textarea class='content' name='content' cols='80' rows='10' style='resize: none;'></textarea></span><span style='width:20px; color:#F00; text-align:center;'>*</span><span>内容长度3~500</span></p><br class='spacer' />");
	$("#main-content-body").append("<p><span name='button' style='width:100px; margin-left:130px; cursor:pointer; background:#EEEEEE;'>添加</span></p> ");
	$("#main-content-body span[name='button']").bind("click", add_message_fun);
}

function add_message_fun() {
	var title = $.trim($("#main-content-body input[name='title']").val());
	var content = $.trim($("#main-content-body textarea[name='content']").val());
	if(title.length<3||title.length>28) {
		alert("标题长度不合格");
	} else if(content.length<3||content.length>500) {
		alert("内容长度不合格");
	} else {
		$("#main-content-body span[name='button']").html("保存中...").css("cursor","text").unbind("click");
		$.post("add_message", {title:title, content:content}, function() {
			$("#main-content-body span[name='button']").html("添加").css("cursor","pointer").bind("click", add_message_fun);
			alert("添加成功");
		});
	}
}
function query_message_page(pageNo) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_message_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].result == "success") {
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			if(pageCount == 0) {
				pageCount = 1;
			}
			dataArray = msgArray.data;
			$("#main-content-body").html("");
			$("#main-content-body").append("<p><span class='title' style='width:500px;'>标题</span><span class='time'>时间</span><span class='time'>发送人</span><span class='edit'>操作</span></p><hr class='spacer' />");
			$.each(dataArray, function(i, item) {
				$("#main-content-body").append("<p><span class='title' style='width:500px;'>"+item.title+"</span><span class='time'>"+item.time+"</span><span class='time'>"+item.sender+"</span><span class='edit'><a href=\"javascript:showMessageInfo('"+item.id+"')\">操作</a></span></p><br class='spacer' />");
			});
			$("#main-content-footer").html("<ul><li style='width:60px;'><span class='pageSelect' value='1'>首页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(pageNo-1)+"'>上一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(parseInt(pageNo)+1)+"'>下一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+pageCount+"'>末页</span></li><li>共<span>"+rowCount+"</span>条记录</li><li>每页"+pageSize+"条</li><li>当前<span>"+pageNo+"</span>/<span>"+pageCount+"</span>页</li></ul><br class='spacer' />");
			$("#main-content-footer .pageSelect").each(function(i, item) {
				$(this).click(function() {
					var tempPageNo = $(this).attr("value");
					if(tempPageNo>pageCount) {
						pageNo = pageCount;
					} else if(tempPageNo<1) {
						pageNo = 1;
					} else {
						query_message_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function showMessageInfo(itemId) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("正在加载...");
	$.getJSON("query_message_id", {id:itemId}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].result == "success") {
			$("#shade_content p span.shade_content_title").html(msgArray[1].title);
			$("<p><label>发送人：</label><span class='text'>" + msgArray[1].sender + "</span><label>发送时间：</label><span class='text'>" + msgArray[1].time + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>信息内容：</label><span class='direction'>" + msgArray[1].content + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p class='btnGroup1'><input type='button' value='删除' class='btnL' id='delete' /></p>").appendTo("#shade_content div");					
			//删除按钮
			$("#shade_content p #delete").click(function() {
				// AJAX POST
				$.post("delete_message", {id:itemId}, function(data) {
					alert((eval(data.msg))[0].result);
					$("#shade_content").remove();
					$("#shade").remove();
				});
			});
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}

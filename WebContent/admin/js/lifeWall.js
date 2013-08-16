function query_lifewall_page(pageNo) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_lifeWall_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			if(pageCount == 0) {
				pageCount = 1;
			}
			dataArray = msgArray.data;
			$("#main-content-body").html("");
			$("#main-content-body").append("<p><span class='title' style='width:500px;'>标题</span><span class='time'>时间</span><span class='edit'>操作</span></p><hr class='spacer' />");
			$.each(dataArray, function(i, item) {
				$("#main-content-body").append("<p><span class='title' style='width:500px;'>"+item.title+"</span><span class='time'>"+item.time+"</span><span class='edit'><a href=\"javascript:showLifewallInfo('"+item.id+"')\">操作</a></span></p><br class='spacer' />");
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
						query_lifewall_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function showLifewallInfo(itemId) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("正在加载...");
	$.getJSON("query_lifeWall_id", {id:itemId}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].result == "success") {
			$("#shade_content p span.shade_content_title").html(msgArray[1].title);
			$("<p><label>发布人：</label><span class='text'>" + msgArray[1].username + "</span><label>时间：</label><span class='text'>" + msgArray[1].time + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>发布内容：</label><span class='direction'>" + msgArray[1].content + "</span></p>").appendTo("#shade_content div");
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}

function query_advice_page(pageNo) {
	
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_advice_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
		console.log("-----------");
		console.log(data.msg);
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			if(pageCount == 0) {
				pageCount = 1;
			}
			$("#main-content-footer").html("<ul><li style='width:60px;'><span class='pageSelect' value='1'>首页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(pageNo-1)+"'>上一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(parseInt(pageNo)+1)+"'>下一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+pageCount+"'>末页</span></li><li>共<span>"+rowCount+"</span>条记录</li><li>每页"+pageSize+"条</li><li>当前<span>"+pageNo+"</span>/<span>"+pageCount+"</span>页</li></ul><br class='spacer' />");
			dataArray = msgArray.data;
			$("#main-content-body").html("");
			$("#main-content-body").append("<p><span class='title'>内容概要</span><span class='time'>时间</span><span class='name'>用户</span><span class='edit'>操作</span></p><hr class='spacer' />");
			$.each(dataArray, function(i, item) {
				$("#main-content-body").append("<p><span class='title'>"+item.content+"</span><span class='time'>"+item.time+"</span><span class='name'>"+item.username+"</span><span class='edit'><a href='javascript:showAdviceInfo(" + item.id + ");'>操作</a></span></p><br class='spacer' />");
			});
			$("#main-content-footer .pageSelect").each(function(i, item) {
				$(this).click(function() {
					var tempPageNo = $(this).attr("value");
					if(tempPageNo>pageCount) {
						pageNo = pageCount;
					} else if(tempPageNo<1) {
						pageNo = 1;
					} else {
						query_advice_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function showAdviceInfo(itemId) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("正在加载...");
	$.getJSON("query_advice_id", {id:itemId}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].resultMsg == "success") {
			$("#shade_content p span.shade_content_title").html(msgArray[1].time);
			$("<p><label>建议内容：</label><span class='direction'>" + msgArray[1].content + "</span></p>").appendTo("#shade_content div");
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}


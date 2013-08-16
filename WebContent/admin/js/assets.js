function add_assetsType_view() {
	$("#main-content-body").html("");
	$("#main-content-body").append("<p><label for='name' class='label'>类型名称：</label><span><input type='text' name='name' id='name' style='height:25px;width:230px;' /></span><span style='width:45px; color:#F00; text-align:center;'>*</span></p><br class='spacer' />");
    //$("#main-content-body").append("<p><label for='sort' class='label'>分类：</label><span><input type='text' name='sort' id='sort' style='height:33px; width:230px;' /></span><span style='width:20px; color:#F00; text-align:center;'>*</span></p><br class='spacer' />");
    $("#main-content-body").append("<p><label for='sort' class='label'>分类：</label><span><select name='rank' id='rank' style='width:230px;'><option value='1'>银行账号</option><option value='2'>网络账号</option><option value='3'>其他</option></select></span><span style='width:20px; color:#F00; text-align:center;'>*</span></p><br class='spacer' />");
	//$("#main-content-body").append("<p><label for='rank' class='label'>排序：</label><span><input name='rank' id='rank' type='text' style='height:33px; width:230px;' /></span><span style='width:60px; color:#F00; text-align:center;'></span></p><br class='spacer' />");
	$("#main-content-body").append("<p><span name='button' style='width:100px; margin-left:130px; cursor:pointer; background:#EEEEEE;'>添加</span></p> ");
	$("#main-content-body span[name='button']").bind("click", admin_add_assetsType);
}

function admin_add_assetsType() {
	var name = $("#main-content-body input[name='name']").val();
	//var sort = $("#main-content-body input[name='sort']").val();
	var rank = $("#main-content-body select[name='rank']").val();
	if($.isNumeric(rank)) {
		$("#main-content-body span[name='button']").html("保存中...").css("cursor","text").unbind("click");
		//$.post("add_assetsType", {name:name, sort:sort, rank:rank}, function() {
		$.post("add_assetsType", {name:name, rank:rank}, function() {
			$("#main-content-body span[name='button']").html("添加").css("cursor","pointer").bind("click", admin_add_assetsType);
			alert("添加成功");
		});
	} else {
		$("#main-content-body label[for='rank']").siblings("span").eq(1).html("非法数字");
	}
	
}

function query_assetsType_page(pageNo) {
	
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_assetsType_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize}, function(data) {
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
			$("#main-content-body").append("<p><span class='title'>名称</span><span class='name'>分类</span><span class='edit'>操作</span></p><hr class='spacer' />");
			var assetsTypeRank = "";
			$.each(dataArray, function(i, item) {
				assetsTypeRank = item.rank;
				if(assetsTypeRank == 1) {
					assetsTypeRank = "银行账号";
				} else if(assetsTypeRank == 2) {
					assetsTypeRank = "网络账号";
				} else if(assetsTypeRank == 3) {
					assetsTypeRank = "其他";
				} else {
					assetsTypeRank = "错误";
				}
				$("#main-content-body").append("<p><span class='title'>"+item.name+"</span><span class='name'>"+assetsTypeRank+"</span><span class='edit'><a href=\"javascript:showAssetsTypeInfo("+item.id+")\">操作</a></span></p><br class='spacer' />");
			});
			$("#main-content-footer").html("<ul><li style='width:60px;'><span class='pageSelect' value='1'>首页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(pageNo - 1) +"'>上一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+parseInt(parseInt(pageNo) + 1) +"'>下一页</span></li><li style='width:60px;'><span class='pageSelect' value='"+pageCount+"'>末页</span></li><li>共<span>"+rowCount+"</span>条记录</li><li>每页"+pageSize+"条</li><li>当前<span>"+pageNo+"</span>/<span>"+pageCount+"</span>页</li></ul><br class='spacer' />");
			$("#main-content-footer .pageSelect").each(function() {
				$(this).click(function() {
					var tempPageNo = $(this).attr("value");
					if(tempPageNo>pageCount) {
						pageNo = pageCount;
					} else if(tempPageNo<1) {
						pageNo = 1;
					} else {
						query_assetsType_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function showAssetsTypeInfo(itemId) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("正在加载...");
	$.getJSON("query_assetsType_id", {id:itemId}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].result == "success") {
			var assetsTypeRank = msgArray[1].rank;
			if(assetsTypeRank == 1) {
				assetsTypeRank = "银行账号";
			} else if(assetsTypeRank == 2) {
				assetsTypeRank = "网络账号";
			} else if(assetsTypeRank == 3) {
				assetsTypeRank = "其他";
			} else {
				assetsTypeRank = "错误";
			}
			$("#shade_content p span.shade_content_title").html(msgArray[1].name);
			$("<p><label>名称：</label><span class='text'>" + msgArray[1].name + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>分类：</label><span class='text'>" + assetsTypeRank + "</span></p>").appendTo("#shade_content div");
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}
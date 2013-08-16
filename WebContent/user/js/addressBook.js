$(function() {
	$("#userPanel").html("");
	
	$("<h2>通讯录</h2>").appendTo("#userPanel");
	
	$("<div id='appHead'></div>").appendTo("#userPanel");
	$("<b style='display:block;float:left;'>快速检索：</b>").appendTo("#appHead");
	$("<input type='text' class='searchInfo' style='float:left; height:30px; width:200px; line-height:30px; margin-top:3px;' />").appendTo("#appHead");
	$("<input type='button' class='quickSearch' value='查找' style='float:left; height:30px; margin:5px 5px 0;' />").appendTo("#appHead");
	$("<span class='all'>所有</span>").appendTo("#appHead");
	$("<span class='new'>添加新好友</span>").appendTo("#appHead");
	//////////////////////////////////////
	$("#appHead span.all").click(function() {	
		query_addressBook_page(1,"");
	});
	$("#appHead input.quickSearch").click(function() {	
		query_addressBook_page(1,encodeURIComponent($.trim($("#appHead input.searchInfo").val())));
	});
	$("#appHead span.new").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("添加新好友");
		/*信息详情*/
		$("<p><label>姓名：</label><input type='text' class='text realName' style='width:300px;' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>电话：</label><input type='text' class='text telephone' style='width:300px;' maxlength='12' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>邮件：</label><input type='text' class='text email' style='width:300px;' maxlength='30' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><label>QQ：</label><input type='text' class='text qq' style='width:300px;' maxlength='12' /></p>").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<br class='spacer' />").appendTo("#shade_content div");
		$("<p><input type='button' id='saveAddrBookBtn' style='width:100px;height:30px;margin-left:100px;' value='保存' /></p>").appendTo("#shade_content div");
		$("#saveAddrBookBtn").click(function() {
			var realName = $.trim($("#shade_content div input.realName").val());
			var telephone = $.trim($("#shade_content div input.telephone").val());
			var email = $.trim($("#shade_content div input.email").val());
			var qq = $.trim($("#shade_content div input.qq").val());
			if(realName.length<=20&&realName.length>=1) {
				if($.isNumeric(telephone)||telephone=="") {
					if($.isNumeric(qq)||telephone=="") {
						$.post("add_addressBook", {realName:realName, telephone:telephone, email:email, qq:qq}, function(data) {
							if((eval(data.msg))[0].result == "success") {
								alert("添加成功");
								$("#shade_content").remove();
								$("#shade").remove();
							} else {
								alert("添加失败");
							}
						});
					} else {
						alert("QQ号码为数字");
					}
				} else {
					alert("电话号码为数字");
				}
			} else {
				alert("姓名长度1~20");
			}
		});
	});	
	//////////////////////////////////////
	$("<div id='appBody'><ul></ul></div>").appendTo("#userPanel");	
	$("<div id='appFoot'></div>").appendTo("#userPanel");
	
	query_addressBook_page(1,"");
});

function query_addressBook_page(pageNo, additive) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;
	$.getJSON("query_addressBook_user", {beginIndex:(pageNo-1)*pageSize,count:pageSize, realName:additive}, function(data) {
		msgArray = eval("("+data.msg+")");
		resultArray = msgArray.result;
		if(resultArray[0].resultMsg == "success") {
			dataArray = msgArray.data;
			$("#appBody ul").html("<li><span class='item_time' style='width:500px;'>姓名</span><span class='item_tel'>操作</span></li>");
			$.each(dataArray, function(i, item) {
				$("<li><span class='item_time' style='width:500px;text-align:center;'>" + item.name + "</span><span class='item_tel'><a href=\"javascript:showAddressBookInfo('"+item.uuid+"');\">查看</a></span></li>").appendTo("#appBody ul");
			});
			rowCount = resultArray[0].rowCount;
			pageCount = Math.ceil(rowCount/pageSize);
			pageCount = pageCount == 0 ? 1 : pageCount;
			setAppFoot(rowCount, pageNo, pageCount, additive);
		}
	});
}

function showAddressBookInfo(id) {
	setShadePosition();
	$.getJSON("query_addressBook_id", {uuid:id}, function(data) {
		var itemInfo = eval(data.msg);
		if("success" == itemInfo[0].result) {
			var item = itemInfo[1];
			$("#shade_content p span.shade_content_title").html(item.name);
			/*信息详情*/
			$("<p><label>姓名：</label><span class='text'>" + item.name + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p> <label>电话：</label><span class='text'>" + item.tel + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>E-mail：</label><span class='text' style='width:300px;'>" + item.email + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>QQ：</label><span class='text'>" + item.qq + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			/*按钮*/
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><input type='button' value='删除' class='btnL' id='delete' /></p>").appendTo("#shade_content div");
			//删除按钮
			$("#shade_content p #delete").click(function() {
				// AJAX POST
				$.post("delete_addressBook", {uuid:id}, function(data) {
					if((eval(data.msg))[0].result == "success") {
						alert("删除成功");
						$("#shade_content").remove();
						$("#shade").remove();
					}
				});
			});
			/*end 信息详情*/
		}
	});
}
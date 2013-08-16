function add_admin_view() {
	$("#main-content-body").html("");
	$("#main-content-body").append("<p><label for='username' class='label'>用户名：</label><span><input type='text' name='username' id='username' style='height:25px; width:230px;' /></span><span style='width:20px; color:#F00; text-align:center;'>*</span><span>用户名长度6~20</span></p><br class='spacer' />");
    $("#main-content-body").append("<p><label for='password' class='label'>密码：</label><span><input type='password' name='password' id='password' style='height:25px; width:230px;' /></span><span style='width:20px; color:#F00; text-align:center;'>*</span><span>密码长度6~20</span></p><br class='spacer' />");
	$("#main-content-body").append("<p><label for='password2' class='label'>密码确认：</label><span><input name='password2' id='password2' type='password' style='height:25px; width:230px;' /></span><span style='width:20px; color:#F00; text-align:center;'>*</span><span></span></p><br class='spacer' />");
	$("#main-content-body").append("<p><span name='button' style='width:100px; margin-left:130px; cursor:pointer; background:#EEEEEE;'>添加</span></p> ");
	$("#main-content-body span[name='button']").bind("click", add_admin_fun);
}

function add_admin_fun() {
	var userName = $.trim($("#main-content-body input[name='username']").val());
	var password = $.trim($("#main-content-body input[name='password']").val());
	var password2 = $.trim($("#main-content-body input[name='password2']").val());
	if(userName.length<6||userName.length>20) {
		alert("用户名长度不符合");
	} else if(password.length<6||password.length>20) {
		conalert("密码长度不符合");
	} else if(password!=password2) {
		alert("两次密码不同");
	} else {
		$("#main-content-body span[name='button']").html("保存中...").css("cursor","text").unbind("click");
		$.post("add_user", { username: userName, password: password ,realName:'', idCard:'', telephone:'', email:'', qq:'', birthDate:'', birthLocation:'', liveLocation:'', livePlace:''}, function() {
			$("#main-content-body span[name='button']").html("添加").css("cursor","pointer").bind("click", add_admin_fun);
			alert("添加成功");
		});
	}
}

function query_user_page(pageNo) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_user_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize, authority:"ROLE_USER"}, function(data) {
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
			$("#main-content-body").append("<p><span class='name'>用户名</span><span class='name'>真实姓名</span><span class='telephone'>电话</span><span class='qq'>QQ</span><span class='email'>E-mail</span><span class='liveLocation'>现居住地</span><span class='edit'>操作</span></p><hr class='spacer' />");
			$.each(dataArray, function(i, item) {
				$("#main-content-body").append("<p><span class='name'>"+item.username+"</span><span class='name'>"+item.realname+"</span><span class='telephone'>"+item.telephone+"</span><span class='qq'>"+item.qq+"</span><span class='email'>"+item.email+"</span><span class='liveLocation'>"+item.liveLocation+"</span><span class='edit'><a href=\"javascript:showUserInfo('" + item.username + "');\">操作</a></span></p><br class='spacer' />");
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
						query_user_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function query_admin_page(pageNo) {
	var pageSize = 10;
	var pageCount;
	var msgArray;
	var resultArray;
	var dataArray;
	var rowCount;

	$.getJSON("query_user_page", {beginIndex:(pageNo-1)*pageSize,count:pageSize, authority:"ROLE_ADMIN"}, function(data) {
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
			$("#main-content-body").append("<p><span class='name'>用户名</span><span class='name'>真实姓名</span><span class='telephone'>电话</span><span class='qq'>QQ</span><span class='email'>E-mail</span><span class='liveLocation'>现居住地</span><span class='edit'>操作</span></p><hr class='spacer' />");
			$.each(dataArray, function(i, item) {
				$("#main-content-body").append("<p><span class='name'>"+item.username+"</span><span class='name'>"+item.realname+"</span><span class='telephone'>"+item.telephone+"</span><span class='qq'>"+item.qq+"</span><span class='email'>"+item.email+"</span><span class='liveLocation'>"+item.liveLocation+"</span><span class='edit'><a href=\"javascript:showUserInfo('" + item.username + "');\">操作</a></span></p><br class='spacer' />");
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
						query_admin_page(tempPageNo);
					}
				});
			});
		} else {
			$("#main-content-body").html(resultArray[0].errorMsg);
		}
	});
}

function showUserInfo(itemId) {
	setShadePosition();
	$("#shade_content p span.shade_content_title").html("正在加载...");
	$.getJSON("query_user_id", {username:itemId}, function(data) {
		var msgArray = eval(data.msg);
		if(msgArray[0].result == "success") {
			$("#shade_content p span.shade_content_title").html(msgArray[1].username);
			$("<p><label>用户名：</label><span class='text'>" + msgArray[1].username + "</span><label>真实姓名：</label><span class='text'>" + msgArray[1].realname + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>联系电话：</label><span class='text'>" + msgArray[1].telephone + "</span><label>E-mail：</label><span class='text'>" + msgArray[1].email + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>QQ：</label><span class='text'>" + msgArray[1].qq + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>出生地：</label><span class='text'>" + msgArray[1].liveLocation + "</span><label>居住地：</label><span class='text'>" + msgArray[1].liveLocation + "</span></p>").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<br class='spacer' />").appendTo("#shade_content div");
			$("<p><label>修改权限：</label><select onchange=\"updateUserRole('"+msgArray[1].username+"');\" id='user_role' name='user_role' style='height:20px;'><option value='ROLE_USER'>用户</option><option value='ROLE_ADMIN'>管理员</option></select><i></i></p>").appendTo("#shade_content div");
		} else {
			$("#shade_content p span.shade_content_title").html("获取失败");
		}
	});
}

function updateUserRole(username) {
	$("#shade_content div #user_role").next("i").html("正在修改...");
	$.post("updateRole", {username:username, authority:$.trim($("#shade_content div #user_role").val())}, function() {
		$("#shade_content div #user_role").next("i").html("修改成功");
	});
}
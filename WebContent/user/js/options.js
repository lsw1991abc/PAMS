$(function() {
	$("#header").load("../header.jsp");
	$("#footer").load("../footer.jsp");
	
	var leftUL=$(".main_left ul");
	var contentUL=$(".main_content > ul");
	
	$(leftUL).not(":first").hide();
	$(contentUL).not(":first").hide();
	$(".main_left ul li:first-child").addClass("selected");
	
	$(".main_head ul li").each(function(index) {
		$(this).click(function() {
			if($(leftUL).eq(index).is(":hidden")){
				$(leftUL).hide().eq(index).show().children().eq(0).click();
				$(this).siblings().removeClass("focus").end().addClass("focus");
			}																	  	
		});								   
	});
	
	$(".main_left li").each(function(index) {
		$(this).click(function() {
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
			$(contentUL).hide().eq(index).show();
		});
	});
	
	$("#add_assets_btn").click(function() {
		setShadePosition();
		$("#shade_content p span.shade_content_title").html("加载中...");
		
		$.getJSON("query_assetsType_page", {beginIndex:0, count:50}, function(data) {
			var msgArray = eval("("+data.msg+")");
			var resultArray = msgArray.result;
			if(resultArray[0].resultMsg == "success") {
				$("#shade_content p span.shade_content_title").html("添加新账号");
				var dataArray = msgArray.data;
				var optionStr="";
				$.each(dataArray, function(i, item) {
					optionStr+="<option value='"+item.id+"'>"+item.name+"</option>";
				});
				$("<p><label>类型：</label><select name='type' id='type' style='width:495px;'>"+optionStr+"</select></p>").appendTo("#shade_content div");
				$("<br class='spacer' /><br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>账号：</label><input type='text' id='account' class='text account' style='width:495px;' /></p>").appendTo("#shade_content div");
				/*按钮*/
				$("<br class='spacer' /><br class='spacer' />").appendTo("#shade_content div");
				$("<p><input type='button' value='保存' class='btnL' id='saveAssets'/></p>").appendTo("#shade_content div");
				$("#shade_content div #saveAssets").click(function() {
					var typeId=$.trim($("#shade_content div #type").val());
					var account=$.trim($("#shade_content div #account").val());
					if(typeId != "") {
						if(account != "" && account.length<=25) {
							$.getJSON("add_assets", {typeId:typeId, account:encodeURIComponent(account), balance:0}, function(data) {
								if((eval(data.msg))[0].result == "success") {
									alert("添加成功");
									$("#shade_content").remove();
									$("#shade").remove();
								} else {
									alert("添加失败");
								}
							});
						} else {
							alert("账号格式不正确，不能为空，长度<=25");
						}
					} else {
						alert("请选择类型");
					}
				});
			} else {
				$("#main-content-body").html("加载失败");
			}
		});
	});
	
});

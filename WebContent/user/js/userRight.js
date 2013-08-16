$(function() {
	$(".quickPanel .module_content span").each(function(i, item) {
		$(this).click(function() {
			var action = $(this).attr("action");
			setShadePosition();
			$("#shade_content p span.shade_content_title").html($(this).html());
			if("easyNote" == action) {
				/*信息详情*/
				$("<p><label>标题：</label><input type='text' class='text title' style='width:600px;' /></p>").appendTo("#shade_content div");		
				$("<br class='spacer' />").appendTo("#shade_content div");		
				$("<p><label>记事内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
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
							$.post("add_easyNote", {title:title, content:content}, function(data) {
								if((eval(data.msg))[0].result == "success") {
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
			} else if("dairy" == action) {
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
			} else if("transaction" == action) {
				/*信息详情*/
				$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>时间：</label> <input type='text' class='text time' style='300px;' readonly /><label>交易类型：</label><select class='type'><option value='-1'>支出</option><option value='1'>收入</option></select></p>").appendTo("#shade_content div");
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>交易账号：</label><select style='width:300px;' class='assets'><option value='1'>加载中...</option></select><label>交易金额：</label><input type='text' class='text amount' style='width:200px;' maxlength='10' /></p>").appendTo("#shade_content div");
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>交易详情：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
				$("#shade_content p input.time").datetimepicker();
				$.getJSON("query_assets_user", function(data) {
					var msgArray = eval("("+data.msg+")");
		      		var resultArray = msgArray.result;
		      		if(resultArray[0].resultMsg == "success") {
		      			var assetsOptionStr;
		      			var dataArray = msgArray.data;
		      			if(dataArray.length==0) {
		      				assetsOptionStr = "<option value=''>您还没有账号</option>";
		      			} else {
		      				assetsOptionStr="";
		      				$(dataArray).each(function(index, item) {
		          				assetsOptionStr+="<option value='"+item.id+"'>"+item.type+"---"+item.account+"</option>";
		          			});
		      			}
		      			$("#shade_content p select.assets").html(assetsOptionStr);
		      		} else {
		      			alert("账号获取失败");
		      		}
				});
				/*按钮*/
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
				//保存按钮
				$("#shade_content p #save").click(function() {	
					var title = $.trim($("#shade_content p input.title").val());
					var time = $.trim($("#shade_content p input.time").val());
					var type = $.trim($("#shade_content p select.type").val());
					var assetsId = $.trim($("#shade_content p select.assets").val());
					var amount = $.trim($("#shade_content p input.amount").val());
					var content = $.trim($("#shade_content p textarea.content").val());
					if(title.length>=3&&title.length<=20) {
						if(time!="") {
							if(assetsId!="") {
								if($.isNumeric(amount)) {
									if(content.length<=150) {
										// AJAX POST
										$.post("add_transactionRecord", {title:title,time:time, type:type, assetsId:assetsId, amount:amount, content:content}, function(data) {
											if((eval(data.msg))[0].result=="success") {
												alert("添加成功");
												$("#shade_content").remove();
												$("#shade").remove();
											} else {
												alert("添加失败");
											}
										});
									} else {
										alert("详情长度<=150");
									}
								} else {
									alert("金额为数字");
								}
							} else {
								alert("请选择账号");
							}
						} else {
							alert("时间不能为空");
						}
					} else {
						alert("标题长度3~20");
					}
				});
				/* end 信息详情*/
			} else if("memorandum" == action) {
				/*信息详情*/
				$("<p><label>标题：</label><input type='text' class='text title' style='width:495px;' /></p>").appendTo("#shade_content div");
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>时间：</label><input type='text' class='text time' readonly /></p>").appendTo("#shade_content div");
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><label>内容：</label><textarea class='direction content'></textarea></p>").appendTo("#shade_content div");
				/*按钮*/
				$("<br class='spacer' />").appendTo("#shade_content div");
				$("<p><input type='button' value='保存' class='btnL' id='save'/></p>").appendTo("#shade_content div");
				$("#shade_content p input.time").datetimepicker();
				//保存按钮
				$("#shade_content p #save").click(function() {
					var title = $.trim($("#shade_content p input.title").val());
					var time = $.trim($("#shade_content p input.time").val());
					var content = $.trim($("#shade_content p textarea.content").val());
					if(title.length>=3&&title.length<=20) {
						if(content.length>=3&&content.length<=300) {
							// AJAX POST
							$.post("add_memorandum", {title:title, time:time, content:content}, function(data) {
								if((eval(data.msg))[0].result == "success") {
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
				/* end 信息详情*/
			} else if("task" == action) {
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
			} else {
				$("<p><label>丢东西了~~~~</label></p>").appendTo("#shade_content div");
			}
		});
	});
});
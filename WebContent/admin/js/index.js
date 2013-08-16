$(function() {
	
	$("#info-item .item-header").mousedown(function(e1) {
		$(this).bind("mousemove",function(e2) {
			var mxValue=e2.pageX-$("#info-item").offset().top;
			var myValue=e2.pageY-$("#info-item").offset().left;
			var xValue=$("#info-item").offset().top;
			var yValue=$("#info-item").offset().left;
			$("#info-item").css({top:e2.pageX-xValue,left:e2.pageY-yValue});
		});		
	}).mouseup(function() {
		$(this).unbind("mousemove");
	}).mouseout(function() {
		$(this).unbind("mousemove");
	});
	
	var bigTitle;
	var secondTitle;
	$("#main-nav li ul").css("display","none");
	$("#main-nav > li > span").each(function(index) {
		$(this).hover(function() {
				$(this).dequeue().animate({padding:"0 50px 0 auto"},"slow");				
			}, function() {		
				$(this).dequeue();
				if($(this).attr("class")!="focus") {
					$(this).animate({padding:"0 15px 0 auto"},"slow");
				}				
		}).click(function() {
			if(!$(this).hasClass("focus")) {
				$("#main-nav > li > span[class='focus']").removeClass("focus").animate({padding:"0 15px 0 auto"},"slow").siblings("ul").slideUp();
				$(this).addClass("focus").siblings("ul").slideDown();
			}
			bigTitle = $(this).html();
			if(index == 0) {
				//$("#main-header").dequeue().css("height","112px").slideDown("slow");
				//$("#main-content-body").css("min-height", "340px");
				//$("#main-content-body").css("min-height", "440px");
				$("#main-content-body").html("<div class='waiting' style='background:none;text-align:center;font-size:50px;font-weight:bolder;line-height:200px;'>欢迎进入后台管理</div>");
				$("#main-content-header h2").html(bigTitle);
				$("#main-content-footer").hide();
				$("#main-nav li ul li span[class='focus']").removeClass("focus");
			}
		});
	});
	
	var spanAction;
	$("#main-nav li ul li span").each(function(index) {
		$(this).click(function() {
			$("#main-nav li ul li span[class='focus']").removeClass("focus");
			$(this).addClass("focus");
			$("#main-content-header h2").html($(this).html() + "  |  " +bigTitle);
			$("#main-header").dequeue().slideUp("slow").css("height","0");
			$("#main-content-body").css("min-height", "500px").html("<div class='waiting'></div>");
			spanAction = $(this).attr("action");
			if(spanAction=="add_user_view") {
				add_user_view();
				$("#main-content-footer").hide();
			} else if(spanAction=="query_user_page") {
				query_user_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="query_lifewall_page") {
				query_lifewall_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="query_advice_page") {
				query_advice_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="add_assetsType_view") {
				add_assetsType_view();
				$("#main-content-footer").hide();
			} else if(spanAction=="query_assetsType_page") {
				query_assetsType_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="query_memorandum_page") {
				query_memorandum_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="update_memorandum_set") {
				update_memorandum_set();
				$("#main-content-footer").hide();
			} else if(spanAction=="query_admin_info") {
				query_admin_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="add_message_view") {
				add_message_view();
				$("#main-content-footer").hide();
			} else if(spanAction=="query_message_page") {
				query_message_page(1);
				$("#main-content-footer").show();
			} else if(spanAction=="update_loaction_view") {
				update_loaction_view();
				$("#main-content-footer").hide();
			}
		});
	});
	
});
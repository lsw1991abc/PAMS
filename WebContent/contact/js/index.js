$(function() {
	$("#header").load("../header.jsp");
	$("#footer").load("../footer.jsp");
	
	var leftUL=$(".main_left ul");
	var contentDIV=$(".main_content div");
	
	$(leftUL).not(":first").hide();
	$(contentDIV).not(":first").hide();
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
			$(contentDIV).hide().eq(index).show();
		});
	});
	
	
	var url=window.location.href;
	if(url.indexOf("?")>=0){
		url=url.substring(url.indexOf("?")+1);
		var menu1,menu2;
		var menuItem;
		var menuGroup=url.split("&");
		for(var i=0;i<menuGroup.length;i++) {
			menuItem=menuGroup[i].split("=");
			if(menuItem[0]=="menu1"){
				menu1=menuItem[1];
			} else if(menuItem[0]=="menu2") {
				menu2=menuItem[1];
			}
		}
		$(".main_head ul li").eq(menu1).click();
		$(".main_left ul").eq(menu1).children("li").eq(menu2).click();
	}

});
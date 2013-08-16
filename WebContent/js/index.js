$(function() {   
	$("#header").load("header.jsp");
	$("#footer").load("footer.jsp");
	$("#validataImg").click(function() {
		$(this).attr("src","validateImageAction");
	});
	var showDiv;
	var indexBgDIV=$("#indexBg div");
	$(indexBgDIV).not(":first").hide();
	$(".selector span").each(function(index) {
		$(this).click(function() {
			showDiv=$(indexBgDIV).eq(index);
			if($(showDiv).is(":hidden")){
				$(this).css("background-position","bottom").siblings().css("background-position","top");				
				$(showDiv).fadeIn("slow").siblings().fadeOut("slow");
			}
        });
    });
    $(".loginbtn").mouseover(function() {
		$(this).css("background-position","bottom");
	}).mouseout(function() {
		$(this).css("background-position","top");
	}).click(function() {
		if($.trim($("#j_username").val()).length>=6&&$.trim($("#j_username").val()).length<=20) {
			if($.trim($("#j_password").val()).length>=6&&$.trim($("#j_password").val()).length<=20) {
				$("#loginForm").submit();
			} else {
				alert("密码长度6~20");
			}
		} else {
			alert("用户名长度6~20");
		}
    });
});
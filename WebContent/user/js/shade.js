var winWidth;
var winHeight;
var dialogHeight;
var dialogLeft;
var docuScrollLeft;

function setShadePosition() {
	$("<div id='shade_content'><p class='shade_content_head'><span class='shade_content_title'>正在加载</span><span class='shade_content_close'>关闭</span></p></div>").prependTo("body");
	$("<div></div>").appendTo("#shade_content");
	$("<div id='shade'></div>").prependTo("body");
	
	winWidth = $(window).width();
	winHeight = $(window).height();
	dialogHeight = $(window).height()-60;
	dialogLeft = ($(window).width()-$("#shade_content").outerWidth())/2;
	docuScrollLeft = $(document).scrollLeft();
	if(dialogLeft<0) {
		dialogLeft=0;
	}
	if(dialogHeight<0) {
		dialogHeight=0;
	}
	$("#shade").css("width",winWidth+"px").css("height",winHeight+"px").show();
	$("#shade_content").css("height",dialogHeight+"px").css("left",dialogLeft-docuScrollLeft+"px").fadeIn();
	
	$("#shade_content p span.shade_content_close").click(function() {
		$("#shade_content").remove();
		$("#shade").remove();
	});
}

$(window).resize(function() {
	if($("#shade") != null) {
		winWidth=$(window).width();
		winHeight=$(window).height();
		dialogLeft=($(window).width()-$("#shade_content").outerWidth())/2;
		dialogHeight=$(window).height()-60;
		if(dialogLeft<0) {
			dialogLeft=0;
		}
		if(dialogHeight<0) {
			dialogHeight=0;
		}
		$("#shade").css("width",winWidth).css("height",winHeight);
		$("#shade_content").css("height",dialogHeight+"px").css("left",dialogLeft+"px");
	}
});

$(window).scroll(function() {
	if($("#shade") != null) {
		docuScrollLeft=$(document).scrollLeft();
		if(docuScrollLeft>0) {
			$("#shade_content").css("left",dialogLeft-docuScrollLeft+"px");
		}
	}
});
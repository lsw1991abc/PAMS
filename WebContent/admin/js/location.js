function update_loaction_view() {
	$("#main-content-body").html("");
	$("#main-content-body").append("<p><input type='file' name='uploadify' id='uploadify' /></p><br class='spacer' />");
	$("#main-content-body").append("<p><span name='button' style='width:100px; margin-left:130px; cursor:pointer; background:#EEEEEE;'>上传</span></p> ");
	$("#main-content-body").append("<hr />");
	$("#main-content-body").append("<p>文件名：<input type='text' name='fileName' id='fileName' style='width:400px;height:30px;line-height:30px;font-size:18px;' /></p><br class='spacer' />");
	$("#main-content-body").append("<p><span name='updateLocation' style='width:100px; margin-left:130px; cursor:pointer; background:#EEEEEE;'>更新</span><i style='display:block;width:100px;height:30px;float:left;margin-left:30px;line-height:30px;'></i></p> ");
	$("#main-content-body span[name='button']").bind("click", upload_location_fun);
	$("#main-content-body span[name='updateLocation']").click(function() {
		var updateBtn = $(this);
		var fileName = $.trim($("#main-content-body #fileName").val());
		$(updateBtn).next().html("正在更新...");
		$.getJSON("update_location", {uploadifyFileName:encodeURIComponent(fileName)}, function(data) {
			console.log(data.msg);
			var msgArray = eval(data.msg);
			if(msgArray[0].result == "success") {
				$(updateBtn).next().html("更新完成");
			} else {
				$(updateBtn).next().html(msgArray[1].errorMsg);
			}
			//
		});
	});
	
	$("#uploadify").each(function(i,obj) {  
    	$(obj).uploadify({	      
     	   'swf':'js/uploadify.swf',
			'uploader':'upload_location.action',
			'auto':false,
			'buttonText':'浏览...',
	        'debug':false,
	        'fileObjName':'uploadify',//文件对象的名称中使用您的服务器端脚本
	        'fileSizeLimit':'2048KB',//限制文件大小
			'fileTypeDesc' : 'All Files',//'fileTypeDesc' : 'Any old file you want...',
			'fileTypeExts' : '*.xls',//必须有上条,才能有这条
	        'method':'post',
			'multi':false,//设置为false,以允许只有一个文件选择在一个时间
			'preventCaching':true,//如果设置为true,一个随机值添加到SWF文件的URL,这样它就不会缓存。这将冲突与任何现有的查询字符串参数对SWF文件的URL
			'removeCompleted':false,//是否自动删除完成的项目
			'uploadLimit':1,//上传数量限制
	     //   'width':300,//按钮宽度
	     //   'height':20
	   	 });
	});
}

function upload_location_fun() {
	$("#uploadify").uploadify("upload","*");
}

var jq = jQuery.noConflict();
	jq(function(){
			jq("#upLoadForm").submit(function(){
				var fileVar = jq("#thisFile").val();
				var validateFileVar = fileVar.toLowerCase();
 				var lens = validateFileVar.length;
 				var extName = validateFileVar.substring((lens - 4), lens);
 				if (validateFileVar == "") {
 					jq("#redId").val("目录不能为空!");
 					return false;
 				} else if (extName != ".xls") {
 					jq("#redId").val("必须是以.xls结尾的execl文件");
 					return false;
 				}
					return true;
				}); 
		
});
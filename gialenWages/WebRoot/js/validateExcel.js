var jq = jQuery.noConflict();
	jq(function(){
			jq("#upLoadForm").submit(function(){
				var fileVar = jq("#thisFile").val();
				var validateFileVar = fileVar.toLowerCase();
 				var lens = validateFileVar.length;
 				var extName = validateFileVar.substring((lens - 4), lens);
 				if (validateFileVar == "") {
 					jq("#redId").val("Ŀ¼����Ϊ��!");
 					return false;
 				} else if (extName != ".xls") {
 					jq("#redId").val("��������.xls��β��execl�ļ�");
 					return false;
 				}
					return true;
				}); 
		
});
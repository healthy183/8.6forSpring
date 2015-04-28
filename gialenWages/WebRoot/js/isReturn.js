var jq = jQuery.noConflict();
 	jq(function(){
 		jq("#returnButton").click(function(){
 			var isReturn = confirm("离开本页面前,您确定已经保存相关操作?");
 				if(isReturn != true){
 					return false;
 				}else{
 					javascript:history.go(-1);
 				}
 		});
 	});
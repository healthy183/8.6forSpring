var jq = jQuery.noConflict();
 	jq(function(){
 		jq("#returnButton").click(function(){
 			var isReturn = confirm("�뿪��ҳ��ǰ,��ȷ���Ѿ�������ز���?");
 				if(isReturn != true){
 					return false;
 				}else{
 					javascript:history.go(-1);
 				}
 		});
 	});
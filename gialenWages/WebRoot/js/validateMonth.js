		var jq = jQuery.noConflict();
 		jq(function() {
 			jq("#button").click(function() {
 				var fileVar = jq("#thisFile").val();
 				//alert(fileVar);
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
 				// realpath = dwr.util.getValue("thisFile");
 				realpath = document.getElementById("thisFile");
 				//alert(realpath);
 				//alert(realpath.value);
 				dwrShowError.uploadExecl(realpath,function(fileString){
 					//alert(fileString);
 					jq("#hiddenFile").val(fileString); 
 					dwrShowError.showError(fileString,test);	
 				});
 			});	

 			function test(data){
 				if(data.length == 0){ 
 					var fileString = jq("#hiddenFile").val();
 					dwrShowError.showMonthError(fileString,function back(date){
 						if(date.length == 0){
 							jq("#upLoadForm").submit();
 						}else if(date.length > 0){
 							var	span = jq("#span");
 							 var num = date.length;
 							 span.html("");
 							 for(var i=0;i<num;i++){
 								 var v  = date[i];
 								jq("<font>"+(i+1)+","+v+"</font><br>").appendTo(span);
 							 }
 						}
 					});
 				}else if(data.length > 0){
 				var	span = jq("#span");
 				 var num = data.length;
 				 span.html("");
 				 for(var i=0;i<num;i++){
 					 var v  = data[i];
 					jq("<font>"+(i+1)+","+v+"</font><br>").appendTo(span);
 				 }							
 				}					
 			};	
 		});
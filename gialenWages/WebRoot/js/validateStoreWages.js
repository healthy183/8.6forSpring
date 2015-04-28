	var jq = jQuery.noConflict();
		jq(function() {
			
			jq("#button").click(function() {

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
				
				var fileVar2 = jq("#thisFile").val();
				fileVar2.select();
				var realpath = document.selection.createRange().text;
				dwrShowError.showStoreError(realpath,test);		
			});	

			function test(data){
				if(data.length == 0){
					jq("#upLoadForm").submit();
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
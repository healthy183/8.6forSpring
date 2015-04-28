var jq = jQuery.noConflict();
	jq(function(){
		jq("#updtmiddleTable").click(function(){
			var productProjectNameVar = jq("#productProjectName").val();
			var productProjectTypeVar =	jq("#productProjectType").val();
			var productProjectVal =	jq("#productProjectVal").val();
			var	isAddBrandWagesVar = jq("#isAddBrandWages").val();
			
			if(productProjectNameVar ==""){
				alert("单品方案名称不允许为空!");
				return false;
			}			
		
			if(productProjectVal ==""){
				alert("提点内容不允许为空!");
				return false;
			}if(isNaN(productProjectVal)){
				alert("提点内容必须是正数!");
				return false;
			}
			
			if(productProjectVal.substring(0,1)=="0"){
				if(productProjectVal.substring(0,2)!="0."){
					alert("提点内容必须是正数!");
					return false;
				}
			}else if(productProjectVal <= 0){
				alert("提点内容必须是正数!");
				return false;
			}
			
			var proIdClassVar =	jq(".proIdClass:checked");
			if(proIdClassVar.length <= 1){
				alert("你没有选择单品!");
				return false;
			}
			
			var proStr = "";
			jq.each(proIdClassVar,function(){
				proStr += jq(this).val()+",";
			});
			//proStr +"adc";
			
			alert("截取先:"+proStr);
			proStr = proStr.substring(3,(proStr.length-1));//截取01，和最后的，
			alert("截取后:"+proStr);
			
			
			
			var projectTypeVar = jq("#projectType").val();
			var operatingMonthIdVar = jq("#operatingMonthId").val();
			
			//alert(projectTypeVar+":"+operatingMonthIdVar);
			var productProjectIdVar = jq("#productProjectId").val();
			alert(productProjectIdVar);
			
			if(productProjectIdVar =="01"){
				dwrAddBrand.addProductProject(operatingMonthIdVar,proStr,productProjectNameVar,productProjectTypeVar,
						productProjectVal,isAddBrandWagesVar,projectTypeVar,function(data){

					jq("#productProjectId").val(data);
					var answer = confirm("添加单品项目成功!是否返回项目清单页面?");
					if(answer){
						//return false; operatingMonthId
						location.href ="admin_arrangeBrand_addBrand.action?"+
						"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
							"operatingMonthId="+jq("#operatingMonthId").val();
						
					}else{
						return false;	
					}
				});
			}else{
				alert("执行修改");
				dwrAddBrand.updtProductProject(proStr,productProjectIdVar,
					productProjectNameVar,productProjectTypeVar,
						productProjectVal,isAddBrandWagesVar,function(){
					
					var answer = confirm("修改项目成功!是否返回项目清单页面?");
					if(answer){
						location.href ="admin_arrangeBrand_addBrand.action?"+
						"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
							"operatingMonthId="+jq("#operatingMonthId").val();
					}else{
						return false;	
					}
					
				});
				alert("修改完成");
				return false;
			}
			
			
			
			
			
			
		});
	});

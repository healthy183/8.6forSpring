var jq = jQuery.noConflict();
	jq(function(){
		jq("#updtmiddleTable").click(function(){
			var productProjectNameVar = jq("#productProjectName").val();
			var productProjectTypeVar =	jq("#productProjectType").val();
			var productProjectVal =	jq("#productProjectVal").val();
			var	isAddBrandWagesVar = jq("#isAddBrandWages").val();
			
			if(productProjectNameVar ==""){
				alert("��Ʒ�������Ʋ�����Ϊ��!");
				return false;
			}			
		
			if(productProjectVal ==""){
				alert("������ݲ�����Ϊ��!");
				return false;
			}if(isNaN(productProjectVal)){
				alert("������ݱ���������!");
				return false;
			}
			
			if(productProjectVal.substring(0,1)=="0"){
				if(productProjectVal.substring(0,2)!="0."){
					alert("������ݱ���������!");
					return false;
				}
			}else if(productProjectVal <= 0){
				alert("������ݱ���������!");
				return false;
			}
			
			var proIdClassVar =	jq(".proIdClass:checked");
			if(proIdClassVar.length <= 1){
				alert("��û��ѡ��Ʒ!");
				return false;
			}
			
			var proStr = "";
			jq.each(proIdClassVar,function(){
				proStr += jq(this).val()+",";
			});
			//proStr +"adc";
			
			alert("��ȡ��:"+proStr);
			proStr = proStr.substring(3,(proStr.length-1));//��ȡ01�������ģ�
			alert("��ȡ��:"+proStr);
			
			
			
			var projectTypeVar = jq("#projectType").val();
			var operatingMonthIdVar = jq("#operatingMonthId").val();
			
			//alert(projectTypeVar+":"+operatingMonthIdVar);
			var productProjectIdVar = jq("#productProjectId").val();
			alert(productProjectIdVar);
			
			if(productProjectIdVar =="01"){
				dwrAddBrand.addProductProject(operatingMonthIdVar,proStr,productProjectNameVar,productProjectTypeVar,
						productProjectVal,isAddBrandWagesVar,projectTypeVar,function(data){

					jq("#productProjectId").val(data);
					var answer = confirm("��ӵ�Ʒ��Ŀ�ɹ�!�Ƿ񷵻���Ŀ�嵥ҳ��?");
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
				alert("ִ���޸�");
				dwrAddBrand.updtProductProject(proStr,productProjectIdVar,
					productProjectNameVar,productProjectTypeVar,
						productProjectVal,isAddBrandWagesVar,function(){
					
					var answer = confirm("�޸���Ŀ�ɹ�!�Ƿ񷵻���Ŀ�嵥ҳ��?");
					if(answer){
						location.href ="admin_arrangeBrand_addBrand.action?"+
						"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
							"operatingMonthId="+jq("#operatingMonthId").val();
					}else{
						return false;	
					}
					
				});
				alert("�޸����");
				return false;
			}
			
			
			
			
			
			
		});
	});

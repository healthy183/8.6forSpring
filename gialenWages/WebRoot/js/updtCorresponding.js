	var jq = jQuery.noConflict();
	jq(function(){
		
		jq(".updtButton").live("click",function(){
			
			var lastTrvar = jq(this).parent().parent().attr("id");
			//alert("id:"+id);
			if(jq("#addButton"+lastTrvar).val() =="���"){
				
				var r1= /^[0-9]*[1-9][0-9]*$/; //������ 
				var updtButton = jq(this).val();//��ǰ��ť��ֵ 
				//alert(updtButton);
				var braId = jq("#braId"+lastTrvar);
				var braName = jq("#braName"+lastTrvar);
				var unitid = jq("#unitid"+lastTrvar);
				var unitName = jq("#unitname"+lastTrvar);
				var storeTypeName = jq("#storeTypeName"+lastTrvar);
				var storeTypeTD = storeTypeName.parent();//������td
				
				var correspondingIdVar = jq("#correspondingId"+lastTrvar).val();
				var braIdVar = braId.val();//��ǰtr�е��ŵ����ֵ 
				var unitidVar = unitid.val(); //��ǰtr�е�HR�ŵ����ֵ
				//var unitNameVar = unitName.val();//��ǰtr�е�HR�ŵ�����ֵ 
				var storeTypeNameVar = storeTypeName.val(); //��ǰtr�е���Ȧֵ
				
				//alert("a");//alert("b");
				if(updtButton == "�޸�"){
					
					braId.css("border","1px solid gray");braId.attr("readonly",false);
					unitid.css("border","1px solid gray");unitid.attr("readonly",false);
					storeTypeTD.html("");//alert("c");
					if(storeTypeNameVar == "С��"){
						storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='��ҵ'>��ҵ</option></select>");
					}else{
						storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='С��'>С��</option></select>");
					}
					jq(this).val("�ύ");//alert("d");
					
				}else{
					//alert(correspondingIdVar+","+braIdVar+","+unitidVar+","+storeTypeNameVar);
					
					if(braIdVar == ""){
						alert("�ŵ���벻��Ϊ��!");
						return false;
					}else if(!r1.test(braIdVar)){
						alert("�ŵ�������Ϊ����!");
						return false;
					}else if(unitidVar ==""){
						alert("HR�ŵ���벻��Ϊ��!");
						return false;
					}else if(!r1.test(unitidVar)){
						alert("HR�ŵ�������Ϊ����!");
						return false;
					}
					
					dwrSynchronizationStores.ishadBrand(correspondingIdVar,braIdVar,function(data){
						if(data == "nofound"){
							alert("û���ڼ�Ѷϵͳ���ҵ��ŵ����Ϊ��"+braIdVar+"�����ŵ�,��ȷ������д���ŵ����!");
							return false;
						}else if(data != "ok"){
							alert("�ŵ����Ϊ"+braIdVar+"���ŵ�"+data+"�Ѿ���Ӧ����,��ȷ������д���ŵ����!");
							return false;
						}else{
							dwrSynchronizationStores.ishadOrgstdStruct(correspondingIdVar,unitidVar,function(date){
								
								//alert(date);
								if(date == "nofound"){
									alert("û����HRϵͳ���ҵ��ŵ����Ϊ��"+unitidVar+"�����ŵ�,��ȷ������д���ŵ����!");
									return false;
								}else if(date != "ok"){
									alert("�ŵ����Ϊ"+unitidVar+"���ŵꡮ"+date+"���Ѿ���Ӧ����,��ȷ������д���ŵ����!");
									return false;
								}else{
									
									dwrSynchronizationStores.getBrandName(braIdVar,function(data){
										braName.val(data);
									});
								
									dwrSynchronizationStores.getHRbrandName(unitidVar,function(data){
										unitName.val(data);
									}); 	
									
									dwrSynchronizationStores.updtCorresponding
										(correspondingIdVar,braIdVar,unitidVar,storeTypeNameVar);
									
									braId.css("border","none");braId.attr("readonly",true);
									unitid.css("border","none");unitid.attr("readonly",true);
									storeTypeTD.html(""); //storeTypeName.css("border","none");storeTypeName.attr("readonly",true);
									storeTypeTD.append("<input id='storeTypeName"+lastTrvar+"' class='storeTypeName' name='storeTypeName' value="+storeTypeNameVar+" readonly='readonly'></input>");
									jq("#updtButton"+lastTrvar).val("�޸�");
									
								};
							
							});
								
						};
						
					});
					
				};
			};
		});
			
	});
		

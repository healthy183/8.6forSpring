	var jq = jQuery.noConflict();
	jq(function(){
		
		jq(".updtButton").live("click",function(){
			
			var lastTrvar = jq(this).parent().parent().attr("id");
			//alert("id:"+id);
			if(jq("#addButton"+lastTrvar).val() =="添加"){
				
				var r1= /^[0-9]*[1-9][0-9]*$/; //正整数 
				var updtButton = jq(this).val();//当前按钮的值 
				//alert(updtButton);
				var braId = jq("#braId"+lastTrvar);
				var braName = jq("#braName"+lastTrvar);
				var unitid = jq("#unitid"+lastTrvar);
				var unitName = jq("#unitname"+lastTrvar);
				var storeTypeName = jq("#storeTypeName"+lastTrvar);
				var storeTypeTD = storeTypeName.parent();//下拉框td
				
				var correspondingIdVar = jq("#correspondingId"+lastTrvar).val();
				var braIdVar = braId.val();//当前tr中的门店代码值 
				var unitidVar = unitid.val(); //当前tr中的HR门店编码值
				//var unitNameVar = unitName.val();//当前tr中的HR门店名称值 
				var storeTypeNameVar = storeTypeName.val(); //当前tr中的商圈值
				
				//alert("a");//alert("b");
				if(updtButton == "修改"){
					
					braId.css("border","1px solid gray");braId.attr("readonly",false);
					unitid.css("border","1px solid gray");unitid.attr("readonly",false);
					storeTypeTD.html("");//alert("c");
					if(storeTypeNameVar == "小区"){
						storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='商业'>商业</option></select>");
					}else{
						storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='小区'>小区</option></select>");
					}
					jq(this).val("提交");//alert("d");
					
				}else{
					//alert(correspondingIdVar+","+braIdVar+","+unitidVar+","+storeTypeNameVar);
					
					if(braIdVar == ""){
						alert("门店代码不能为空!");
						return false;
					}else if(!r1.test(braIdVar)){
						alert("门店代码必须为整数!");
						return false;
					}else if(unitidVar ==""){
						alert("HR门店编码不能为空!");
						return false;
					}else if(!r1.test(unitidVar)){
						alert("HR门店编码必须为整数!");
						return false;
					}
					
					dwrSynchronizationStores.ishadBrand(correspondingIdVar,braIdVar,function(data){
						if(data == "nofound"){
							alert("没有在佳讯系统中找到门店代码为‘"+braIdVar+"’的门店,请确认您填写的门店代码!");
							return false;
						}else if(data != "ok"){
							alert("门店代码为"+braIdVar+"的门店"+data+"已经对应好了,请确认您填写的门店代码!");
							return false;
						}else{
							dwrSynchronizationStores.ishadOrgstdStruct(correspondingIdVar,unitidVar,function(date){
								
								//alert(date);
								if(date == "nofound"){
									alert("没有在HR系统中找到门店编码为‘"+unitidVar+"’的门店,请确认您填写的门店编码!");
									return false;
								}else if(date != "ok"){
									alert("门店代码为"+unitidVar+"的门店‘"+date+"’已经对应好了,请确认您填写的门店代码!");
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
									jq("#updtButton"+lastTrvar).val("修改");
									
								};
							
							});
								
						};
						
					});
					
				};
			};
		});
			
	});
		

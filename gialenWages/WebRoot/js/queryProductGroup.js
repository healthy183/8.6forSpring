	var jq = jQuery.noConflict();
	jq(function(){
		jq("#queryProduct").click(function(){
			var proIdKeywordVar = jq("#proIdKeyword").val();
			var barcodeKeywordVar = jq("#barcodeKeyword").val();			
			var proNameKeyWordVar = jq("#proNameKeyWord").val();
			var bigBrandVar = jq("#bigBrand").val();
			var middleBrandVar = jq("#middleBrand").val();
			var smallBrandVar = jq("#smallBrand").val();
			var bigClassVar = jq("#bigClass").val();
			var middleClassVar = jq("#middleClass").val();
			var smallClassVar = jq("#smallClass").val();
		
			if(proIdKeywordVar =="" && barcodeKeywordVar =="" && proNameKeyWordVar ==""){
				if(bigBrandVar == 0 && middleBrandVar == 0 && smallBrandVar == 0){
					if(bigClassVar == 0 && middleClassVar == 0 && smallClassVar == 0){
						alert("��ѯ��������Ϊ��!");
						return false;
					}
				}
			}
			
			var proIdClassStr = "";
			var proIdClassVar = jq(".proIdClass");
			jq.each(proIdClassVar,function(){
				proIdClassStr += jq(this).val()+",";
			});
			
			proIdClassStr = proIdClassStr.substring(0,(proIdClassStr.length-1));
			//alert(proIdClassStr);
			
			var ProFlagStr = "";
			var ProFlagCheckedStr =	jq(".ProFlag:checked");
			
			if(ProFlagCheckedStr.length<1){
				ProFlagStr = "0,1,2,3,4,5,6,7,8,9,";
			}else{
				ProFlagCheckedStr.each(function(){
					ProFlagStr += jq(this).val()+",";
				});
			}
			
			ProFlagStr = ProFlagStr.substring(0,(ProFlagStr.length-1));
			
			var ProStatusStr = "";
			var ProStatusCheckedStr = jq("input[name='Status']:checked");
			if(ProStatusCheckedStr.length<1){
				ProStatusStr ="0,1,2,3,4,5,6,7,8,9,";
			}else{
				ProStatusCheckedStr.each(function(){
					ProStatusStr += jq(this).val()+",";
				});
			}
			ProStatusStr = ProStatusStr.substring(0,(ProStatusStr.length-1));
			
		dwrAddBrand.findProductForjson(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
			bigBrandVar,middleBrandVar,smallBrandVar,
				bigClassVar,middleClassVar,smallClassVar,
					ProFlagStr,ProStatusStr,proIdClassStr,function(data){
						//alert(data);
						
						if(data !="û�в�ѯ����Ӧ��Ʒ���ߵ�Ʒ�Ѿ�������ҳ����,��ȷ�����Ĳ�ѯ����!"){
							var json = eval("("+data+")");//ǰ̨��ʽ������
							//alert(json);
							
							var lastIndex =	jq("#proTable>tbody>tr:last").children().html();
							if(isNaN(lastIndex)){
								 lastIndex = 0;
							}
							
							//jq("#proTable:not(#abc,#)").html("");
							jq("#allCheckbox").attr("checked",true);
							var checkboxString = "";
							jq.each(json,function(n,v){
								
								var tdproFlag = "";
								var proFlag = v[7];
								
								if(proFlag =="0"){
									tdproFlag  = "������Ʒ";
								}else if(proFlag =="1"){
									tdproFlag  = "��Ʒ";
								}else if(proFlag =="2"){
									tdproFlag  = "���ϺĲ�";
								}else if(proFlag =="3"){
									tdproFlag  = "��ͨ��Ʒ����װ";
								}else if(proFlag =="4"){
									tdproFlag  = "��������װ";
								}else if(proFlag =="5"){
									tdproFlag  = "��̯����";
								}else if(proFlag =="6"){
									tdproFlag  = "��ͨ����";
								}
								
								var tdStatus = "";
								var proStatus = v[6];
								if(proStatus =="0"){
									tdStatus ="δʹ��";
								}else if(proStatus =="1"){
									tdStatus ="��Ʒ";
								}else if(proStatus =="2"){
									tdStatus ="����";
								}else if(proStatus =="3"){
									tdStatus ="�����Խ�ֹ�ɹ�";
								}else if(proStatus =="4"){
									tdStatus ="ֹͣ�ɹ�";
								}else if(proStatus =="5"){
									tdStatus ="ֹͣҪ��";
								}else if(proStatus =="8"){
									tdStatus ="ֹͣ����";
								}else if(proStatus =="9"){
									tdStatus ="��ȫͣ��";
								}
								
								checkboxString += 
								"<tr class='mybgcolor'>"+
									"<td>"+((lastIndex*1)+n+1)+"</td>"+
									"<td><input type='checkbox' class='proIdClass' name='items' value='"+((lastIndex*1)+n+1)+"' checked='checked'></input></td>"+
									"<td>"+v[0]+"</td>"+
									"<td>"+v[3]+"</td>"+
									"<td>"+v[4]+"</td>"+
									"<td>"+v[5]+"</td>"+
									"<td>"+v[2]+"</td>"+
									"<td>"+v[1]+"</td>"+
									"<td>"+tdproFlag+"</td>"+
									"<td>"+tdStatus+"</td>"+
									"<td><input id='quantity' name='quantity' class='quantity' value='1' /></td>"
									+ "<td><input id='proid' class='proIdClass' value='"+v[0]+"' type='hidden' /> </td>"+
								"</tr>";
							});
							jq("#proTable").append(checkboxString);
							return false;
						}else{
							alert(data);
							return false;
						}			
			});
		});
	});
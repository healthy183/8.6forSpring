<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showOrUpdtCorresponding.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/test.css">
	<style type="text/css">
		
		.braId{
			border: none;
			text-align: right;
		}
		.braName{
			border: none;
		}
		.unitid{
			border: none;
			text-align: right;
		}
		.unitname{
			border: none;
		}
		.storeTypeName{
			border: none;
		}
	
	</style>
  <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
  <script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
  <script type='text/javascript' src='/gialenWages/dwr/interface/dwrSynchronizationStores.js'></script>
  <script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
  <script type="text/javascript" charset="gb2312" src="js/updtCorresponding.js"></script>
  <script type="text/javascript" charset="gb2312" src="js/delCorresponding.js"></script>
  <script type="text/javascript" charset="gb2312">
  
  var jq = jQuery.noConflict();
  	jq(function(){
  		jq(".addButton").each(function(){
  			
  			
  			 var proIdKeywordVal = jq("#proIdKeyword"+tableId).val(); //查询条件
  			 var proNameKeyWordVal = jq("#proNameKeyWord"+tableId).val();
  			
  			jq(this).click(function testFunction(){
			//readonly='readonly' id='storeTypeName"+lastTrvar+"' name='storeTypeName'	
			//var lastTr = jq(this).parent().parent().parent().children("tr:last");//获取最后一个tr的id
			//var lastTrvar =	lastTr.attr("id");//alert("lastTrvar:"+lastTrvar);
			var lastTrvar = jq("#correspondingTable>tbody>tr:last").attr("id");
			lastTrvar++;//alert("lastTrvar:"+lastTrvar);
			var addTr =	"<tr id='"+lastTrvar+"'>"+
			"<td><input id='correspondingId"+lastTrvar+"' type='text' name='correspondingId'></input>"+lastTrvar+"</td><td><input id='braId"+lastTrvar+"' name='braId'></input></td>"+
			"<td><input id='braName"+lastTrvar+"' name='braName' ></input></td>"+
			"<td><input id='unitid"+lastTrvar+"'  name='unitid'></input></td>"+
			"<td><input id='unitname"+lastTrvar+"' name='unitname'></input></td>"+
			"<td>"+
			"<select id='storeTypeName"+lastTrvar+"' name='storeTypeName'>"+
				"<option value='小区'>小区</option><option value='商业'>商业</option>"+
			"</select>"+
			"</td>"+
			"<td><input id='addButton"+lastTrvar+"'type='button' value='提交'></input>"+
			"<input id='updtButton"+lastTrvar+"'  type='button' value='修改'></input>"+
			"<input id='delButton"+lastTrvar+"' type='button' value='删除' ></input></td>"+
			"</tr>";
			jq("#correspondingTable").append(addTr);
			
			
			jq("#addButton"+lastTrvar).click(function(){
				
				var thisButtonVar = jq(this).val();
				if(thisButtonVar == "提交"){
					
					var lastbraIdvar = jq("#braId"+lastTrvar).val();
					var lastbraNamevar = jq("#braName"+lastTrvar).val();
					var lastunitidvar = jq("#unitid"+lastTrvar).val();
					var lastunitnamevar = jq("#unitname"+lastTrvar).val();
					var laststoreTypeNameVar = jq("#storeTypeName"+lastTrvar).val();
					var r1= /^[0-9]*[1-9][0-9]*$/; //正整数正则
					
					if(lastbraIdvar == ""){
						alert("门店代码不可以为空!");
						return false;
					}else if(!r1.test(lastbraIdvar)){
						alert("门店代码必须为整数!");
						return false;
					}else if(lastbraNamevar == ""){
						alert("门店名称不可以为空!");
						return false;
					}else if(lastunitidvar == ""){
						alert("HR门店编号不可以为空!");
						return false;
					}else if(!r1.test(lastunitidvar)){
						alert("HR门店编号必须为整数!");
						return false;
					}else if(lastunitnamevar ==""){
						alert("HR门店名称不可以为空!");
						return false;
					}
					
					dwrSynchronizationStores.ishadBrand(lastbraIdvar,function(data){
						if(data == "nofound"){
							alert("没有在佳讯系统中找到门店代码为‘"+lastbraIdvar+"’的门店,请确认您填写的门店代码!");
							return false;
						}else if(data != "ok"){
							alert("门店代码为"+lastbraIdvar+"的门店"+data+"已经对应好了,请确认您填写的门店代码!");
							return false;
						}else{
				dwrSynchronizationStores.ishadOrgstdStruct(lastunitidvar,function(date){
						if(date == "nofound"){
							alert("没有在HR系统中找到门店代码为‘"+lastunitidvar+"’的门店,请确认您填写的代码!");
							return false;
						}else if(date != "ok"){
							alert("HR门店编号为"+lastunitidvar+"的门店已经对应好了,请确认您填写的门店代码!");
							return false;
						}else {
							dwrSynchronizationStores.addCorresponding(lastbraIdvar,lastbraNamevar,lastunitidvar,
									lastunitnamevar,laststoreTypeNameVar,function(correspondingId){
										
								//var hiddenInput ="<input id='correspondingId+"+lastTrvar+"' type='text' name='correspondingId' value='"+correspondingId+"'></input>";
								
								//jq("#"+lastTrvar).children().append(hiddenInput);
								jq("#correspondingId"+lastTrvar).val(correspondingId);
								jq("#braId"+lastTrvar).addClass("braId");jq("#braId"+lastTrvar).attr("readonly",true);
								jq("#braName"+lastTrvar).addClass("braName");jq("#braName"+lastTrvar).attr("readonly",true);
								jq("#unitid"+lastTrvar).addClass("unitid");jq("#unitid"+lastTrvar).attr("readonly",true);
								jq("#unitname"+lastTrvar).addClass("unitname");jq("#unitname"+lastTrvar).attr("readonly",true);
								var selectTd = jq("#storeTypeName"+lastTrvar).parent();
								
			var selectInput ="<input id='storeTypeName"+lastTrvar+"' class='storeTypeName' name='storeTypeName' value='"+laststoreTypeNameVar+"' readonly='readonly'></input>";
								selectTd.html("");
								selectTd.append(selectInput);
								jq("#addButton"+lastTrvar).val("添加");
							});
						}
					}); 		
				   }
				});
				}else{
					testFunction();
				}
				
			});
			
			
			jq("#updtButton"+lastTrvar).click(function(){
				//alert("updtButton");
				var addButtonVar = jq("#addButton"+lastTrvar).val();
				if(addButtonVar == "添加"){
					
					var r1= /^[0-9]*[1-9][0-9]*$/; //正整数 
					var updtButton = jq(this).val();//当前按钮的值 
					
					var braId = jq("#braId"+lastTrvar);
					var braName = jq("#braName"+lastTrvar);
					var unitid = jq("#unitid"+lastTrvar);
					var unitName = jq("#unitname"+lastTrvar);
					var storeTypeName = jq("#storeTypeName"+lastTrvar);
					var storeTypeTD = storeTypeName.parent();
					
					var correspondingIdVar = jq("#correspondingId"+lastTrvar).val();
					var braIdVar = braId.val();//当前tr中的门店代码值 
					var braNameVar = braName.val();//当前tr中门店名称值 
					var unitidVar = unitid.val(); //当前tr中的HR门店编码值 
					var unitNameVar = unitName.val();//当前tr中的HR门店名称值 
					var storeTypeNameVar =	storeTypeName.val(); //当前tr中的商圈值 
					
					if(updtButton == "修改"){
						
						braId.css("border","1px solid gray");braId.attr("readonly",false);
						braName.css("border","1px solid gray");braName.attr("readonly",false);
						unitid.css("border","1px solid gray");unitid.attr("readonly",false);
						unitName.css("border","1px solid gray");unitName.attr("readonly",false);
						storeTypeName.css("border","1px solid gray");storeTypeName.attr("readonly",false);
						
						storeTypeTD.html("");//storeTypeTD.html("");
						if(storeTypeNameVar == "小区"){
							storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='商业'>商业</option></select>");
						}else{
							storeTypeTD.append("<select id='storeTypeName"+lastTrvar+"'><option selected='selected' value=" +storeTypeNameVar+">"+storeTypeNameVar+"</option><option value='小区'>小区</option></select>");
						}
						jq(this).val("提交");
						
					}else{
						
						if(braIdVar == ""){
							alert("门店代码不能为空!");
							return false;
						}else if(!r1.test(braIdVar)){
							alert("门店代码必须为整数!");
							return false;
						}else if(braNameVar ==""){
							alert("门店名称不能为空!");
							return false;
						}else if(unitidVar ==""){
							alert("HR门店编码不能为空!");
						}else if(!r1.test(unitidVar)){
							alert("HR门店编码必须为整数!");
							return false;
						}else if(unitNameVar ==""){
							alert("HR门店名称不能为空!");
							return false;
						}
						
						dwrSynchronizationStores.ishadBrand(correspondingIdVar,braIdVar,function(data){
							if(data == "nofound"){
								alert("没有在佳讯系统中找到门店代码为+‘"+braIdVar+"’的门店,请确认您填写的门店代码!");
								return false;
							}else if(data != "ok"){
								alert("门店代码为"+braIdVar+"的门店"+data+"已经对应好了,请确认您填写的门店代码!");
								return false;
							}else{
								dwrSynchronizationStores.ishadOrgstdStruct(correspondingIdVar,unitidVar,function(date){
									if(date == "nofound"){
										alert("没有在HR系统中找到门店编码为+‘"+unitidVar+"’的门店,请确认您填写的门店编码!");
									}else if(date != "ok"){
										alert("门店代码为"+unitidVar+"的门店"+data+"已经对应好了,请确认您填写的门店代码!");
										return false;
									}else{
										dwrSynchronizationStores.updtCorresponding
										(correspondingIdVar,braIdVar,braNameVar,
												unitidVar,unitNameVar,storeTypeNameVar);
										
										braId.css("border","none");braId.attr("readonly",true);
										braName.css("border","none");braName.attr("readonly",true);
										unitid.css("border","none");unitid.attr("readonly",true);
										unitName.css("border","none");unitName.attr("readonly",true);
										storeTypeTD.html(""); //storeTypeName.css("border","none");storeTypeName.attr("readonly",true);
										storeTypeTD.append("<input id='storeTypeName"+lastTrvar+"' class='storeTypeName' name='storeTypeName' value="+storeTypeNameVar+" readonly='readonly'></input>");
										//jq(this).css({background:"white", border:"1px white solid"});
										jq("#updtButton"+lastTrvar).val("修改");
										
									}
								});
							}
						});

					}
				}
			});
			
			jq("#delButton"+lastTrvar).click(function(){
					
				var answer = confirm("你确认要删除本行数据?");
				if(!answer){
					return false;
				}
					var hiddenCorrespondingIdVar = jq("#correspondingId"+lastTrvar).val();
					//alert("hiddenCorrespondingIdVar是"+hiddenCorrespondingIdVar);
					if(typeof hiddenCorrespondingIdVar != "undefined"){
						//alert("二次hiddenCorrespondingIdVar是"+hiddenCorrespondingIdVar);
						 if(hiddenCorrespondingIdVar != ""){
							 dwrSynchronizationStores.delCorresponding(hiddenCorrespondingIdVar); 
						 }
					}
					jq(this).parent().parent().remove();
			});
			
			
  			});
  		});	  
  });
  </script>
  </head>
  
  <body>
	<table border="1" id="correspondingTable">
			<tr>
				<td>序号<td>门店代码</td><td>门店名称</td><td>HR门店编码</td>
				<td>HR门店名称</td><td>商圈</td><td>操作</td>
			<tr>
			<c:forEach items="${requestScope.correspondingList}" var="list" varStatus="s">
				<tr id="${s.index+1}">
		<td>单品编码:<input id="proIdKeyword1" name="proIdKeyword" value="0" /></td>
				
					<td><input id="correspondingId${s.index+1}" type="hidden" name="correspondingId" value="${list.correspondingId}"></input>${s.index+1}</td>
					<td><input id="braId${s.index+1}" class="braId" name="braId" value="${list.branch.braId}" readonly="readonly"></input></td>
					<td><input id="braName${s.index+1}" class="braName" name="braName" value="${list.branch.braName}" readonly="readonly"></input></td>
					<td><input id="unitid${s.index+1}" class="unitid" name="unitid" value="${list.orgstdStruct.unitid}"readonly="readonly"></input></td>
					<td><input id="unitname${s.index+1}" class="unitname" name="unitname" value="${list.orgstdStruct.unitname}"readonly="readonly"></input></td>
					<td>
					<input id="storeTypeName${s.index+1}" class="storeTypeName" name="storeTypeName" value="${list.storeTypeName}"readonly="readonly"></input> 
					</td>
					<td>
						<input id="addButton${s.index+1}" class="addButton" type="button" value="添加"></input>
						<input id="updtButton${s.index+1}" class="updtButton" type="button" value="修改"></input>
						<input id="delButton${s.index+1}" class="delButton" type="button" value="删除" ></input>
					
					</td>
				</tr>
			</c:forEach>	
		
	</table>



  </body>
  
  
</html>
<%--<s:iterator value="#request.correspondingList" status="s">
				<tr>
					<td><s:property value="#s.index+1"></s:property></td>
					<td><s:property value="branch.braId"></s:property></td>
					<td><s:property value="branch.braName"></s:property></td>
					<td><s:property value="orgstdStruct.unitid"></s:property></td>
					<td><s:property value="orgstdStruct.unitname"></s:property></td>
					<td><s:property value="storeTypeName"></s:property></td>
				</tr>
			</s:iterator>--%>




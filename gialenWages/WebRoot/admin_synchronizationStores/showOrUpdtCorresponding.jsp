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
	<style type="text/css">
	body{
		margin:0px;
	}

	*{ font-size:12px;
	   font-family:"宋体";
	 }
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
			margin:0px;
		}
		
	form{
		margin:0px;
	}
	
	table{
		margin:0px;
		margin-left:10px;
	}
	
	span{
		margin:0px;
	}
	.myTable {
		width:98%;
		text-align:center;
		background-color: #CBD8AC;
		margin-bottom:8px;
		margin-top:8px;
	}
	.myTable tr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTable td{
		line-height: 14px;height: 14px;
	}
	.myTableTh{
		background: url(image/wbg.gif);
	}
	
	
</style>
	
  <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
  <script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
  <script type='text/javascript' src='/gialenWages/dwr/interface/dwrSynchronizationStores.js'></script>
  <script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
  <script type="text/javascript" charset="gb2312" src="js/updtCorresponding.js" ></script>
  <script type="text/javascript" charset="gb2312" src="js/delCorresponding.js"></script>
  <script type="text/javascript" charset="gb2312">
  
  var jq = jQuery.noConflict();
  	jq(function(){
  		jq(".addButton").each(function(){
  			
  			jq(this).click(function testFunction(){

			var lastTrvar = jq("#correspondingTable>tbody>tr:last").attr("id");
			lastTrvar++;//
			var addTr =	"<tr id='"+lastTrvar+"'>"+
			"<td><input id='correspondingId"+lastTrvar+"' type='hidden'  name='correspondingId'></input>"+lastTrvar+"</td>"+
			"<td><input id='braId"+lastTrvar+"' name='braId'></input></td>"+
			"<td><input class='braName' id='braName"+lastTrvar+"' name='braName' readonly='readonly'></input></td>"+
			"<td><input id='unitid"+lastTrvar+"'  name='unitid'></input></td>"+
			"<td><input class='unitname' id='unitname"+lastTrvar+"' name='unitname' readonly='readonly'></input></td>"+
			"<td>"+
			"<select id='storeTypeName"+lastTrvar+"' name='storeTypeName'>"+
				"<option value='小区'>小区</option><option value='商业'>商业</option>"+
			"</select>"+
			"</td>"+
			"<td><input id='addButton"+lastTrvar+"'type='button' value='提交'></input>"+
			"<input class='updtButton' id='updtButton"+lastTrvar+"'  type='button' value='修改'></input>"+
			"<input class='delButton' id='delButton"+lastTrvar+"' type='button' value='删除' ></input></td>"+
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
					}else if(lastunitidvar == ""){
						alert("HR门店编号不可以为空!");
						return false;
					}else if(!r1.test(lastunitidvar)){
						alert("HR门店编号必须为整数!");
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
							
							dwrSynchronizationStores.getBrandName(lastbraIdvar,function(data){
								jq("#braName"+lastTrvar).val(data);
							});
						
							dwrSynchronizationStores.getHRbrandName(lastunitidvar,function(data){
								jq("#unitname"+lastTrvar).val(data);
							}); 
							
							dwrSynchronizationStores.addCorresponding(lastbraIdvar,lastunitidvar,
									laststoreTypeNameVar,function(correspondingId){
										
								jq("#correspondingId"+lastTrvar).val(correspondingId);
								jq("#braId"+lastTrvar).addClass("braId");jq("#braId"+lastTrvar).attr("readonly",true);
								jq("#unitid"+lastTrvar).addClass("unitid");jq("#unitid"+lastTrvar).attr("readonly",true);
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
			
  			});
  		});	  
  });
  </script>

 <script  type="text/javascript" src="js/mybgcolor.js">
 </script>
  </head>
  
  <body>
	<table id="correspondingTable" class="myTable" cellpadding="3" cellspacing="1">
			<tr>
				<th class="myTableTh" colspan="7">
						<b style="font-size:16px;">HR与佳讯系统对应表单</b>
			</th>
			</tr>
			<tr>
				<th>序号</th><th>门店代码</th><th>门店名称</th><th>HR门店编码</th><th>HR门店名称</th><th>商圈</th><th>操作</th>
			</tr>
			<c:forEach items="${requestScope.correspondingList}" var="list" varStatus="s">
				<tr id="${s.index+1}" class="mybgcolor">
					<td><input id="correspondingId${s.index+1}" type="hidden" name="correspondingId" value="${list.correspondingId}"></input>${s.index+1}</td>
					<td><input  id="braId${s.index+1}" class="braId" name="braId" value="${list.branch.braId}" readonly="readonly"></input></td>
					<td><input id="braName${s.index+1}" class="braName" name="braName" value="${list.branch.braName}" readonly="readonly"></input></td>
					<td><input id="unitid${s.index+1}" class="unitid" name="unitid" value="${list.orgstdStruct.unitcode}"readonly="readonly"></input></td>
					<td><input id="unitname${s.index+1}" class="unitname" name="unitname" value="${list.orgstdStruct.unitname}"readonly="readonly"></input></td>
					<td>
						<input id="storeTypeName${s.index+1}" class="storeTypeName" name="storeTypeName" value="${list.storeTypeName}"readonly="readonly"></input> 
					</td>
					<td>
						<input id="addButton${s.index+1}" class="addButton" type="button" value="添加" ></input>
						<input id="updtButton${s.index+1}" class="updtButton" type="button" value="修改"></input>
						<input id="delButton${s.index+1}" class="delButton" type="button" value="删除" ></input>
					</td>
				</tr>
			</c:forEach>	
		
	</table>



  </body>
  
  
</html>

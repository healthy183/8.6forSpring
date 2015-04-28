<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updtProProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<style type="text/css">
	 table{
	 	width:100%;
	 }
	 li {
			float:left;
}
	#queryProduct{
		text-align: center;
	}
	</style>
<script language="javascript" type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script type="text/javascript" charset="gb2312" src="js/brandThreeLinkage.js"></script>
<script type="text/javascript" charset="gb2312" src="js/classThreeLinkage.js"></script>
<script type="text/javascript" charset="gb2312" src="js/queryProduct.js"></script>
<script type="text/javascript" charset="gb2312" src="js/choseCheckbox.js"></script>
<script type="text/javascript" charset="gb2312" >
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
		
	//	alert("截取先:"+proStr);
		proStr = proStr.substring(3,(proStr.length-1));//截取01，和最后的，
		//alert("截取后:"+proStr);
		
		
		
		var projectTypeVar = jq("#projectType").val();
		var operatingMonthIdVar = jq("#operatingMonthId").val();
		
		//alert(projectTypeVar+":"+operatingMonthIdVar);
		var productProjectIdVar = jq("#productProjectId").val();
		//alert(productProjectIdVar);
		
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
			//alert("执行修改");
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
			//alert("修改完成");
			return false;
		}
		
	});
});




</script>

 <script type="text/javascript" charset="gb2312" src="js/isReturn.js"></script>
<style type="text/css">
*{ font-size:12px;
	   font-family:"宋体";
	 }
	.tableA{
	text-align:center;
	margin-bottom:8px;
	margin-top:8px;
	width:99%;
	font:Georgia 11px;
	font-size:12px;
	color:#333333;
	border-collapse:collapse;/*细线表格*/
}

.tableAbb{
text-align:center;
	margin-bottom:8px;
	margin-top:8px;
	width:99%;
	font:Georgia 11px;
	font-size:12px;
	color:#333333;
	border-collapse:collapse;/*细线表格*/
}
.tableAbb th{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}


.tableAbb td{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
	text-align: left;
}


.tableA th{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}
.tableA td{
	border:1px solid #CBD8AC;/*细线条颜色*/
	height:22px;
}
.myTableTh{
		width:100%;
		background: url(image/wbg.gif);
	}
	div{
		display: inline; 
	}
	select{
		width: 130px;
	}
</style>
  </head>
  
  <body>
      <input type="hidden" name="operatingMonthType" value="${param.operatingMonthType}"></input>
  <input type="hidden" name="projectType" value="${param.projectType}"></input>

			<table  class="tableAbb">
			
			<tr class="myTableTh">
			<th align="center" colspan="4">
				<b style="font-size:16px;margin-top:30px;">修改单品提成计划填写表</b>
				<div style="text-align: right;float:right;margin-top:-18px;">
						<input type="button" value= "返回 " id="returnButton">
				</div>
			</th>
			</tr>
					<tr>
						<td>单品编码:<input id="proIdKeyword" name="proIdKeyword"  /></td>
						<td>单品条码:<input id="barcodeKeyword" name="barcodeKeyword" /></td>
						<td>单品关键字:<input id="proNameKeyWord" name="proNameKeyWord" /></td>
						<th rowspan="5" align="center"><input id="queryProduct" class="queryProduct" type="button" value="查询单品"/></th>
					</tr>
					<tr>
						<td>
						品牌大类:<select id="bigBrand" class="brandClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigBrandList}" var="brand">
									<option value="${brand.brandId}">${brand.brandName}</option>
								</c:forEach>
						</select>
						</td>
						<td>
						品牌中类:<select id="middleBrand" class="brandClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>
						</td>
						<td>
						品牌小类:<select id="smallBrand">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productSmallBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>	
						</td>
					</tr>
					<tr>
						<td>
						类别大类:<select id="bigClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						</td>
						<td>
						类别中类:<select id="middleClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						</td>
						<td>
						类别小类:<select id="smallClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productSmallClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="3">商品类型:
							<input type="checkbox" class="ProFlag" name="ProFlag" value="0" checked="checked"/>正常商品
							<input type="checkbox" class="ProFlag" name="ProFlag" value="1" checked="checked"/>赠品
							<input type="checkbox" class="ProFlag" name="ProFlag" value="2" checked="checked"/>物料耗材
							<input type="checkbox" class="ProFlag" name="ProFlag" value="3" checked="checked"/>流通商品试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="4" checked="checked"/>自有试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="5" checked="checked"/>待摊物料
							<input type="checkbox" class="ProFlag" name="ProFlag" value="6" checked="checked" />普通物料
						</td>
					</tr>
					<tr>
						<td colspan="3">单品状态:
							<input type="checkbox" name="Status" value="0" checked="checked" />未使用
							<input type="checkbox" name="Status" value="1" checked="checked"/>新品
							<input type="checkbox" name="Status" value="2" checked="checked"/>正常
							<input type="checkbox" name="Status" value="3" checked="checked"/>季节性禁止采购
							<input type="checkbox" name="Status" value="4" checked="checked"/>停止采购 
							<input type="checkbox" name="Status" value="5" checked="checked"/>停止要货
							<input type="checkbox" name="Status" value="8" checked="checked"/>停止销售
							<input type="checkbox" name="Status" value="9" checked="checked"/>完全停用
						</td>
					</tr>
				</table>
			
			
			
				<table id="2"  class="tableA">
					<tr class="titleClass">
					<td>
   				<input id="operatingMonthId"  type="hidden" value="${requestScope.productProject.operatingMonth.operatingMonthId}"></input>
   				营运月:${requestScope.productProject.operatingMonth.operatingMonthName}
					</td>
						<td>
							单品方案名称:<input id="productProjectName" value="${requestScope.productProject.productProjectName}"></input><font style="color: red;"> *</font>
							<input id="projectType" type="hidden" value="0"></input>
						</td>
						<td>
							<!--  显示一下:${requestScope.productProject.productProjectType}-->
							<select id="productProjectType">
								<c:if test="${requestScope.productProject.productProjectType == 0}">
									<option value="0" selected="selected">按百分比提成</option>
									<option value="1">按件提成</option>
								</c:if>
								<c:if test="${requestScope.productProject.productProjectType == 1}">
								<option value="1" selected="selected">按件提成</option>
								<option value="0">按百分比提成</option>
								</c:if>
							</select>
							<input id="productProjectVal" value="${requestScope.productProject.productProjectVal}"></input><font style="color: red;"> *</font>
							<select id="isAddBrandWages">
								<c:if test="${requestScope.productProject.isAddBrandWages == 0}">
									<option value="0" selected="selected">不算入品牌提中</option>
								<option value="1">算入品牌提中</option>
								</c:if>
								<c:if test="${requestScope.productProject.isAddBrandWages == 1}">
									<option value="1" selected="selected">算入品牌提中</option>
									<option value="0">不算入品牌提中</option>
								</c:if>
								
							</select>
						</td>
						<td align="right">
								<input type="hidden" id="productProjectId" value="${requestScope.productProject.productProjectId}"></input><!-- 当前单品项目id -->
								<input type="button" id="updtmiddleTable" class="updtmiddleTable"  value="提交"/>
						</td>
					</tr>
	</table>
			
			
			
	<table id="proTable" class="tableA">
								<tr id="abc">
									<td>序号</td><td>选择<input id="allCheckbox" type="checkbox" checked="checked"></input></td>
									<td>商品编号</td><td>商品条码</td><td>商品名称</td><td>规格</td>
									<td>品牌小类</td><td>品类小类</td><td>商品类型</td><td>商品状态</td>
								</tr>
								<tr style="display:none;" id="efg">
									<td>0</td><td><input type="checkbox" id="mustbeCheck" class="proIdClass" value="01" checked="checked"></td>
									<td></td><td></td><td></td><td></td>
									<td></td><td></td><td></td>
								</tr>
								<c:forEach items="${ProProjectRelationTableList}" var="table" varStatus="t">
   				<tr>
   					<td>${t.index+1}</td>
   					<td><input type="checkbox" id="mustbeCheck" class="proIdClass" value="${table.product.proId}" checked="checked"></td>
   					<td>${table.product.proId}</td>
   					<td>${table.product.barcode}</td>
   					<td>${table.product.proName}</td>
   					<td>${table.product.spec}</td>	
   					<td>${table.product.productClass.className}</td>
   					<td>${table.product.productBrand.brandName}</td>
   				<c:if test="${table.product.proFlag == 0}">
   						<td>正常商品</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 1}">
   						<td>赠品</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 2}">
   						<td>物料耗材</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 3}">
   						<td>流通商品试用装</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 4}">
   						<td>自有试用装</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 5}">
   						<td>待摊物料</td>
   				</c:if>
   				<c:if test="${table.product.proFlag == 6}">
   						<td>普通物料</td>
   				</c:if>
   					
		   			
		   		<c:if test="${table.product.status == 0}">
   						<td>未使用</td>
   				</c:if>
   				<c:if test="${table.product.status == 1}">
   						<td>新品</td>
   				</c:if>	
   				<c:if test="${table.product.status == 2}">
   						<td>正常</td>
   				</c:if>	
   				<c:if test="${table.product.status == 3}">
   						<td>季节性禁止采购</td>
   				</c:if>	
   				<c:if test="${table.product.status == 4}">
   						<td>停止采购</td>
   				</c:if>	
   				<c:if test="${table.product.status == 5}">
   						<td>停止要货</td>
   				</c:if>	
   				<c:if test="${table.product.status == 8}">
   						<td>停止销售</td>
   				</c:if>	
   				<c:if test="${table.product.status == 9}">
   						<td>完全停用</td>
   				</c:if>	
   				</tr>
   				</c:forEach>
	</table>
	
	
  </body>
</html>

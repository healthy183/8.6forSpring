<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addProductProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	 table{
	 	width:100%;
	 }
	 li {
			float:left;
}
	</style>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
	<script type='text/javascript' src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script type="text/javascript">
var jq = jQuery.noConflict();
jq(function(){
		jq("#bigBrand").change(function(){
			var bigBrandIdVar = jq(this).val();
			var level2 = "2";
			var level3 = "3";
			if(bigBrandIdVar != "0"){
				dwrAddBrand.findBrandByParentBrand(bigBrandIdVar,level2,function(data){
					dwr.util.removeAllOptions("middleBrand");
					dwr.util.addOptions("middleBrand",{"0":''});
					dwr.util.addOptions("middleBrand",data);
				});
			}else{ //为中品牌下拉框赋值
				dwrAddBrand.getbrandByLevel(level2,function(data){
					dwr.util.removeAllOptions("middleBrand");
					dwr.util.addOptions("middleBrand",{"0":''});
					dwr.util.addOptions("middleBrand",data);
				});
			} //为小品牌下拉框赋值
			dwrAddBrand.getbrandByLevel(level3,function(data){
				dwr.util.removeAllOptions("smallBrand");
				dwr.util.addOptions("smallBrand",{"0":''});
				dwr.util.addOptions("smallBrand",data);
			});
		});
		
		jq("#middleBrand").change(function(){
				var middelBrandIdVar = jq(this).val();	
				var level3 = "3";
				if(middelBrandIdVar != "0"){
					dwrAddBrand.findBrandByParentBrand(middelBrandIdVar,level3,function(data){
						dwr.util.removeAllOptions("smallBrand");
						dwr.util.addOptions("smallBrand",{"0":''});
						dwr.util.addOptions("smallBrand",data);
					});
				}else{
					dwrAddBrand.getbrandByLevel(level3,function(data){
						dwr.util.removeAllOptions("smallBrand");
						dwr.util.addOptions("smallBrand",{"0":''});
						dwr.util.addOptions("smallBrand",data);
					});
				}
		});
});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#bigClass").change(function(){
			var bigClassVar = jq(this).val();
			var level2 = "2";
			var level3 = "3";
			if(bigClassVar !="0"){
				dwrAddBrand.findClassByParentClass(bigClassVar,level2,function(data){
					dwr.util.removeAllOptions("middleClass");
					dwr.util.addOptions("middleClass",{"0":''});
					dwr.util.addOptions("middleClass",data);
				});
			}else{
				dwrAddBrand.getClassByLevel(level2,function(data){
					dwr.util.removeAllOptions("middleClass");
					dwr.util.addOptions("middleClass",{"0":''});
					dwr.util.addOptions("middleClass",data);
				});
			}
			dwrAddBrand.getClassByLevel(level3,function(data){
				dwr.util.removeAllOptions("smallClass");
				dwr.util.addOptions("smallClass",{"0":''});
				dwr.util.addOptions("smallClass",data);
			});
		});
		
		jq("#middleClass").change(function(){
				var middleClassIdVar = jq(this).val();
				var level3 = "3";
				if(middleClassIdVar != 0){
					dwrAddBrand.findClassByParentClass(middleClassIdVar,level3,function(data){
						dwr.util.removeAllOptions("smallClass");
						dwr.util.addOptions("smallClass",{"0":''});
						dwr.util.addOptions("smallClass",data);
					});
				}else{
					dwrAddBrand.getClassByLevel(level3,function(data){
						dwr.util.removeAllOptions("smallClass");
						dwr.util.addOptions("smallClass",{"0":''});
						dwr.util.addOptions("smallClass",data);
					});
				}
		});
	});
</script>
<script type="text/javascript">
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
					ProFlagStr,ProStatusStr,function(data){
						//alert(data);
						
						if(data !="没有对应单品,请确认您的查询条件!"){
							var json = eval("("+data+")");//前台格式化对象
							//alert(json);
							//var lastIndex =	jq("#proTable>tbody>tr:first").children().html();
							var lastIndex =	jq("#proTable>tbody>tr:last").children().html();
							
							//jq("#proTable:not(#abc,#)").html("");
							jq("#allCheckbox").attr("checked",true);
							var checkboxString = "";
							var checkboxString_first = "";
							jq.each(json,function(n,v){
								
								var tdproFlag = "";
								var proFlag = v[7];
								
								if(proFlag =="0"){
									tdproFlag  = "正常商品";
								}else if(proFlag =="1"){
									tdproFlag  = "赠品";
								}else if(proFlag =="2"){
									tdproFlag  = "物料耗材";
								}else if(proFlag =="3"){
									tdproFlag  = "流通商品试用装";
								}else if(proFlag =="4"){
									tdproFlag  = "自有试用装";
								}else if(proFlag =="5"){
									tdproFlag  = "待摊物料";
								}else if(proFlag =="6"){
									tdproFlag  = "普通物料";
								}
								
								var tdStatus = "";
								var proStatus = v[6];
								if(proStatus =="0"){
									tdStatus ="未使用";
								}else if(proStatus =="1"){
									tdStatus ="新品";
								}else if(proStatus =="2"){
									tdStatus ="正常";
								}else if(proStatus =="3"){
									tdStatus ="季节性禁止采购";
								}else if(proStatus =="4"){
									tdStatus ="停止采购";
								}else if(proStatus =="5"){
									tdStatus ="停止要货";
								}else if(proStatus =="8"){
									tdStatus ="停止销售";
								}else if(proStatus =="9"){
									tdStatus ="完全停用";
								}
								
								
								 
								checkboxString += 
								"<tr  >"+
									"<td >"+((lastIndex*1)+n+1)+"</td>"+
									"<td><input type='checkbox' class='proIdClass' value='"+v[0]+"' checked='checked'></input></td>"+
									"<td>"+v[0]+"</td>"+
									"<td>"+v[3]+"</td>"+
									"<td>"+v[4]+"</td>"+
									"<td>"+v[5]+"</td>"+
									"<td>"+v[2]+"</td>"+
									"<td>"+v[1]+"</td>"+
									"<td>"+tdproFlag+"</td>"+
									"<td>"+tdStatus+"</td>"+
									
								"</tr>";
								
								
								//checkboxString_first=checkboxString;
								
								
								
							});														
							jq("#proTable").append(checkboxString);							
							return false;							
						}else{
							alert("没有对应单品,请确认您的查询条件!");
							return false;
						}			
			});
		});
		
		
		    jq("#addProduct").click(function(){
		    alert("abc");
			var bigBrandVar = jq("#bigBrand").val();
			var middleBrandVar = jq("#middleBrand").val();
			var smallBrandVar = jq("#smallBrand").val();
			//	var lastIndex =	jq("#proTable>tbody>tr:last").children().html();
				var id = jq("#proTable").children("table:last").attr("id");
			id++;
	 	dwrAddBrand.findSmallProduct(bigBrandVar,middleBrandVar,smallBrandVar ,function(data){
						//alert(data);					
		 				if(data !="没有对应品牌,请确认您的查询条件!"){
		 					var json = eval("("+data+")");//前台格式化对象
							//alert(json);
							var lastIndex =	jq("#proTable>tbody>tr:last").children().html();							
							//jq("#proTable:not(#abc,#)").html("");
							jq("#allCheckbox").attr("checked",true);
							var checkboxString = "";
							jq.each(json,function(n,v){
							checkboxString += 
								"<tr  >"+
									"<td >"+((lastIndex*1)+n+1)+"</td>"+
									"<td><input type='checkbox' class='proIdClass' value='"+v[0]+"' checked='checked'></input></td>"+
									"<td> </td>"+
									"<td> </td>"+
									"<td> </td>"+
								 	"<td> </td>"+
								 	"<td>"+v[2]+"</td>"+
								 	"<td> </td>"+
								 	"<td> </td>"+
								 	"<td></td>"+
								//	"<td><input type='button' class='delPro' id='del"+id+"' value='删除'></input></td>"+
								"</tr>";
								//checkboxString_first=checkboxString;																								
							});														
							jq("#proTable").append(checkboxString);
							return false;							
						}else{
							alert("没有对应品牌,请确认您的查询条件!");
							return false;
						}			
			 
		});
});
	});
</script>
<script type="text/javascript">
	jq(function(){
		jq("#allCheckbox").click(function(){
			if(jq(this).attr("checked") == true){
				jq(".proIdClass").attr("checked",true);
			}else{
				jq(".proIdClass").attr("checked",false);
			}
		});
	});
</script>
  </head>
  
  <body>
  <input type="hidden" name="operatingMonthType" value="${param.operatingMonthType}"></input>
  <input type="hidden" name="projectType" value="${param.projectType}"></input>
<table>
		<tr>
			<td align="center">品牌提成计划填写表</td>
		</tr>
		<tr>
			<td>
				<table border="1" >
					<tr>
						<td>单品编码:<input id="proIdKeyword" name="proIdKeyword"   /></td>
						<td>单品条码:<input id="barcodeKeyword" name="barcodeKeyword"   /></td>
						<td>单品关键字:<input id="proNameKeyWord" name="proNameKeyWord"  /></td>
						<td rowspan="5" align="center"><input id="addProduct" class="addProduct" type="button" value="绑定品牌"/></td>
						<td rowspan="5" align="center"><input id="queryProduct" class="queryProduct" type="button" value="查询单品"/></td>
					</tr>
					<tr>
						<td colspan="3">
						品牌大类:
						<select id="bigBrand" class="brandClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigBrandList}" var="brand">
									<option value="${brand.brandId}">${brand.brandName}</option>
								</c:forEach>
						</select>
						品牌中类:<select id="middleBrand" class="brandClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>
						品牌小类:<select id="smallBrand">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productSmallBrandList}" var="brand">
							 		<option value="${brand.brandId}">${brand.brandName}</option>
							 	</c:forEach>
						</select>	
						</td>
					</tr>
					<tr>
						<td colspan="3">
						类别大类:<select id="bigClass">
								<option value="0"></option>
								<c:forEach items="${requestScope.productBigClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
						类别中类:<select id="middleClass">
							 	<option value="0"></option>
							 	<c:forEach items="${requestScope.productMiddleClassList}" var="class">
									<option value="${class.classId}">${class.className}</option>
								</c:forEach>
						</select>
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
							<input type="checkbox" class="ProFlag" name="ProFlag" value="2" />物料耗材
							<input type="checkbox" class="ProFlag" name="ProFlag" value="3"/>流通商品试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="4"/>自有试用装
							<input type="checkbox" class="ProFlag" name="ProFlag" value="5"/>待摊物料
							<input type="checkbox" class="ProFlag" name="ProFlag" value="6"/>普通物料
						</td>
					</tr>
					
					
					<tr>
						<td colspan="3">单品状态:
							<input type="checkbox" name="Status" value="0" />未使用
							<input type="checkbox" name="Status" value="1" checked="checked"/>新品
							<input type="checkbox" name="Status" value="2" checked="checked"/>正常
							<input type="checkbox" name="Status" value="3"/>季节性禁止采购
							<input type="checkbox" name="Status" value="4"/>停止采购 
							<input type="checkbox" name="Status" value="5"/>停止要货
							<input type="checkbox" name="Status" value="8"/>停止销售
							<input type="checkbox" name="Status" value="9"/>完全停用
						</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td>
				<table id="2" border="1" >
					<tr class="titleClass">
					<td>
		<select name="operatingMonthId" id="operatingMonthId">
   				<option value="${requestScope.thisOperatingMonth.operatingMonthId}" selected="selected">${requestScope.thisOperatingMonth.operatingMonthName}</option>
  		<c:forEach items="${requestScope.thisYearOperatingMonthList}" var="Month">
  				<option value="${Month.operatingMonthId}">${Month.operatingMonthName}</option>
  		</c:forEach>
   </select>
					</td>
						<td>
							品牌方案名称:<input id="productProjectName"></input>
						</td>
						<td>
							<select id="productProjectType">
								<option value="0">按百分比提成</option>
								<option value="1">按件提成</option>
							</select>
							<input id="productProjectVal"></input>
							<select id="isAddBrandWages">
								<option value="0">不算入品牌提中</option>
								<option value="1">算入品牌提中</option>
							</select>
						</td>
						<td align="right">
								<input type="button" id="updtmiddleTable" class="updtmiddleTable"  value="提交"/>
								<input type="button" id="delmiddleTable" class="delmiddleTable" value="清空设置"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table id="proTable" border="1">
								<tr id="abc">
									<td>序号</td><td>选择<input id="allCheckbox" type="checkbox" checked="checked"></input></td>
									<td>商品编号</td><td>商品条码</td><td>商品名称</td><td>规格</td>
									<td>品牌小类</td><td>品类小类</td><td>商品类型</td><td>商品状态</td><td>操作</td>
								</tr>
								<tr style="display:none;" id="efg">
									<td>0</td><td><input type="checkbox" class="proIdClass" value="01" checked="checked"></td>
									<td></td><td></td><td></td><td></td>
									<td></td><td></td><td></td><td></td>
								</tr>
								
								
								
								
							</table>
						</td>
					</tr>
			</table>
			</td>
		</tr>
		
	</table>

<!--  
<form action="">
	<select name="aa">
		<option>00111</option>
	</select>
	<input type="file"></input>
	<input type="submit">
</form>
-->
  </body>
</html>

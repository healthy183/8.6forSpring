<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'addBrand.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		table {
			width:100%;
		}
	</style>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery.blockUI.js"></script>	
	<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
  	<script type='text/javascript' src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
	<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
	<script type="text/javascript" src="js/linkageBrandClass.js"></script>
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
			
			<%-- 
			var n = 1;
			var updttable = null;
			jq(".updtmiddleTable").each(function(){
				var thisUpdtmiddleTableVar = jq(this).val();
				if(thisUpdtmiddleTableVar =="提交"||thisUpdtmiddleTableVar =="保存"){
					updttable = jq(this);
					n++;
				}
			});
			
			if(n>2){
				alert("你还有其他方案没有保存,请先保存或者提交你其他方案!");
				return false;
			}
			--%>
			var proIdKeywordVar = jq("#proIdKeyword").val();
			var proNameKeyWordVar = jq("#proNameKeyWord").val();
			var bigBrandVar = jq("#bigBrand").val();
			var middleBrandVar = jq("#middleBrand").val();
			var smallBrandVar = jq("#smallBrand").val();
			var bigClassVar = jq("#bigClass").val();
			var middleClassVar = jq("#middleClass").val();
			var smallClassVar = jq("#smallClass").val();
			
			<%-- 
			if(n == 2){
				//添加单品到指定项目
				
				
				
				
				return false;
			}--%>
			
			var id = jq("#addTable").children("table:last").attr("id");
			id++;
			
		    var addTable = "<table id='"+id+"' border='1'>";
		    var firstTr = "<tr class='titleClass'>"+
		    			     "<td>单品方案名称:<input id='productProjectName"+id+"'></input></td>"+
		    			     "<td><select id='productProjectType"+id+"'><option value='0'>按百分比提成</option>"+
		    			     	  "<option value='1'>按件提成</option></select>"+
		    			     	  "<input id='productProjectVal"+id+"'></input>"+
		    			     	  "<select id='isAddBrandWages"+id+"'><option value='0'>不算入品牌提中</option>"+
		    			     	  "<option value='1'>算入品牌提中</option></select></td>"+
		    			     "<td><input type='button' class='updtmiddleTable' id='updt"+id+"' value='保存'></input>"+
		    			     	  	  "<input type='button' class='delPro' id='del"+id+"' value='删除'></input>"+
		    			     	  	  "<input type='hidden' id='productProjectId"+id+"' value='0'></input></td></tr>";
			
			dwrAddBrand.findProductForjson
				(proIdKeywordVar,proNameKeyWordVar,bigBrandVar,middleBrandVar,smallBrandVar,
						bigClassVar,middleClassVar,smallClassVar,function(data){
				
					if(data!="没有对应单品,请确认您的查询条件!"){
						//alert(data);
						var json = eval("("+data+")");//前台格式化对象
						var checkboxString = "<tr>";
						jq.each(json,function(n,v){
							checkboxString += "<td><input checked='checked' type='checkbox' value='"+v.proId+"'>"+v.proId+","+v.proName+"</input></td>";
							if((n+1) % 3 == 0){
								checkboxString = checkboxString+"</tr><tr>";	
								}else if((n+1) == json.length){
									checkboxString = checkboxString+"</tr></table>";
								}
						});
						
					 var sddd =addTable+ firstTr + checkboxString;//jq("#1 tr:not(.titleClass)").remove();
						jq("#addTable").append(sddd);
					}else{
						alert(data);
						return false;
					}
			});
		});
	});

</script>

<script type="text/javascript">
var jq = jQuery.noConflict();
jq(function(){
		jq(".updtmiddleTable").live("click",function(){
			var thisButton = jq(this);
			var thisTableId = jq(this).parentsUntil("table").parent().attr("id");
			if(thisButton.val()=="保存"){
				
				var stringVar = "";
				jq("input:checked").each(function(){
					stringVar += jq(this).val()+",";
				});	
				
				var checkboxVarstr = stringVar.substr(0,(stringVar.length-1));
				var parentTd = jq(this).parent();
				var productProjectNameVal =	jq("#productProjectName"+thisTableId).val();
				var productProjectTypeVal =	jq("#productProjectType"+thisTableId).val();
				var productProjectVal  = jq("#productProjectVal"+thisTableId).val();
				var isAddBrandWagesVal = jq("#isAddBrandWages"+thisTableId).val();
				
				dwrAddBrand.addProductProject(checkboxVarstr,thisTableId,productProjectNameVal
						,productProjectTypeVal,productProjectVal,isAddBrandWagesVal,function(data){
					//var ProjectId = "<input type='hidden' id='productProjectId"+thisTableId+"' value='"+data+"'></input>";
					//parentTd.append(ProjectId);
					jq("#productProjectId"+thisTableId).val(data);
					jq("#"+thisTableId+" tr:not(.titleClass)").remove();
					thisButton.val("修改");
				});
				
			}else if(thisButton.val()=="修改"){
				
				var productProjectId = jq("#productProjectId"+thisTableId).val();
				alert(productProjectId);
				
				dwrAddBrand.findProductByProject(productProjectId,function(data){
					//alert("WFH");alert("1");alert("2");alert(json);
					if(data !="该单品方案没有单品安排,请添加!"){
						
						var json = eval("("+data+")");//前台格式化对象
						var checkboxString = "<tr>";
						jq.each(json,function(n,v){
							checkboxString += "<td><input checked='checked' type='checkbox' value='"+v[0]+"'>"+v[0]+","+v[1]+"</input></td>";
							//checkboxString += "<td><input checked='checked' type='checkbox' value='"+v.proId+"'>"+v.proId+","+v.proName+"</input></td>";
							if((n+1) % 3 == 0){
								checkboxString = checkboxString+"</tr><tr>";	
								}else if((n+1) == json.length){
									checkboxString = checkboxString+"</tr>";
								}
						});
						jq("#"+thisTableId).append(checkboxString);
						thisButton.val("提交");
					}else{
						alert(data);
						thisButton.val("提交");
						return false;
					}
				});
			}else if(thisButton.val()=="提交"){
				
				var stringVar = "";
				jq("input:checked").each(function(){
					stringVar += jq(this).val()+",";
				});	
				
				var checkboxVarstr = stringVar.substr(0,(stringVar.length-1));
				//var parentTd = jq(this).parent();
				var productProjectIdVar = jq("#productProjectId"+thisTableId).val();//主键id
				var productProjectNameVal =	jq("#productProjectName"+thisTableId).val();//名称
				var productProjectTypeVal =	jq("#productProjectType"+thisTableId).val();//按什么提成
				var productProjectVal  = jq("#productProjectVal"+thisTableId).val();//提点
				var isAddBrandWagesVal = jq("#isAddBrandWages"+thisTableId).val();//是否添加到总提
				
				dwrAddBrand.updtProductProject(checkboxVarstr,productProjectIdVar,
						productProjectNameVal,productProjectTypeVal,
							productProjectVal,isAddBrandWagesVal,function(){
					
					jq("#"+thisTableId+" tr:not(.titleClass)").remove();
					thisButton.val("修改");
				});
				
				
			}
			
		});

});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq(".delPro").live("click",function(){
			
			var answer = confirm("你确认要删除本方案数据?");
			if(answer){
				var thisTableId = jq(this).parentsUntil("table").parent().attr("id");
				//alert(jq(this).attr("id"));
				//alert(thisTableId);
				var productProjectId = jq("#productProjectId"+thisTableId).val();
				//alert(productProjectId);
				
				if(productProjectId !="0"){
					dwrAddBrand.delPro(productProjectId);
				}
				jq("#"+thisTableId).remove();
			}else{
				return false;
			}
			
		});
	});
	</script>
</head>

<body>
	<!-- <input type="button" value="添加表格" /> -->
	<table>
		<tr>
			<td align="center">单品提成计划填写表</td>
		</tr>
		<tr>
			<td>
				<table border="1" >
					<!--  
					<tr>
						<td>综合品牌名称:<input/></td>
						<td align="right" colspan="2"><input type="button" value="保存/修改">
							<input id="delAll1"  type="button" value="删除"></td>
					</tr>-->
					<tr>
						<td>单品编码:<input id="proIdKeyword" name="proIdKeyword" value="0" /></td>
						<td>单品关键字:<input id="proNameKeyWord" name="proNameKeyWord" value="婷美"/></td>
						<td rowspan="3" align="center"><input id="queryProduct" class="queryProduct" type="button" value="查询单品"/></td>
					</tr>
					<tr>
						<td colspan="2">
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
						<td colspan="2">
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
				</table></td>
		</tr>
		<tr><td align="center">单品列表</td></tr>
		<tr>
		<!-- id="1middleTable1keywordTD" class="middleTablekeywordTD" --><!--  -->
		<td id="addTable">
			<table id="1" style="display:none;"></table>
		<!--  
			<table id="2" border="1" >
					<tr class="titleClass">
						<td>
							单品方案名称:<input id="productProjectName1"></input>
						</td>
						<td>
							<select id="productProjectType1">
								<option value="0">按百分比提成</option>
								<option value="1">按件提成</option>
							</select>
							<input id="productProjectVal1"></input>
							<select id="isAddBrandWages1">
								<option value="0">不算入品牌提中</option>
								<option value="1">算入品牌提中</option>
							</select>
						</td>
						<td align="right">
								<input type="button" id="updt1middleTable" class="updtmiddleTable"  value="提交/修改"/>
								<input type="button" id="del1middleTable1" class="delmiddleTable" value="删除"/>
						</td>
					</tr>
			</table>
			-->
		</td>
		</tr>
	</table>
		<!-- 
					<tr>
						<td colspan="3">
					
							<table id="1middleTable1" border="1" width="100%">
								 <tr>
									
									<td colspan="3" id="1middleTable1keywordTD" class="middleTablekeywordTD">关键字:</td>
									<td align="right">
										<input type="button" id="updt1middleTable1" class="updtmiddleTable"  value="提交/修改"/>
										<input type="button" id="del1middleTable1" class="delmiddleTable" value="删除"/>
									</td>
								</tr
								<tr>
									<td colspan="3">
											<table id="1middleTable1productTable"></table>
									</td>
								</tr>>
							</table> 
						</td>
					</tr>-->
	<!-- 
	alert("??");
			var lastTableId = null;
			var lastTable =	jq("#addTable table:last")attr("id");
			//alert(lastTable == "undefined");
			alert("??");
			//alert("id:"+lastTable.attr("id"));
			if(typeof lastTable != "undefined"){
				//alert("不等于");
				//lastTableId = lastTable.attr("id");
				lastTableId = lastTable;
			}else{
				lastTableId  = "1";
			}
			alert(lastTableId);
	 -->
	
</body>
</html>

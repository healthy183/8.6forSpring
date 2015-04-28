<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	var nid=0;
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
			
			
			var proIdClassStr = "";
			var proIdClassVar = jq(".proIdClass");
			jq.each(proIdClassVar,function(){
				proIdClassStr += jq(this).val()+",";
			});
			
			proIdClassStr = proIdClassStr.substring(0,(proIdClassStr.length-1));
			//alert(proIdClassStr);
			
		dwrAddBrand.findProductForjson(proIdKeywordVar,barcodeKeywordVar,proNameKeyWordVar,
			bigBrandVar,middleBrandVar,smallBrandVar,
					bigClassVar,middleClassVar,smallClassVar,
					ProFlagStr,ProStatusStr,proIdClassStr,function(data){
						// alert("proIdClassStr "+proIdClassStr);
						
						if(data !="没有对应单品,请确认您的查询条件!"){
							var json = eval("("+data+")");//前台格式化对象
							//alert(json);
							//var lastIndex =	jq("#proTable>tbody>tr:first").children().html();
							//nid++;
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
									"<td >"+('Y'+((lastIndex*1)+n+1)*1)+"</td>"+
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
								jq("#efg").append(checkboxString);											
							//jq("#proTable").append(checkboxString);							
							return false;							
						}else{
							alert("没有对应单品,请确认您的查询条件!");
							return false;
						}			
			});
		});
		
		
		    jq("#addProduct").click(function(){
		   // alert("abc");
			var bigBrandVar = jq("#bigBrand").val();
			var middleBrandVar = jq("#middleBrand").val();
			var smallBrandVar = jq("#smallBrand").val();
				var proIdClassStr = "";
			var proIdClassVar = jq(".proIdClass");
			jq.each(proIdClassVar,function(){
				proIdClassStr += jq(this).val()+",";
			});
			
			proIdClassStr = proIdClassStr.substring(0,(proIdClassStr.length-1));
			//alert(proIdClassStr);
				//var id = jq("#proTable").children("table:last").attr("id");
			//	id=lastIndex;
			//id++;
				nid++;
			 var lastIndex =	jq("#proTable>tbody>tr:last").children().html();
	 	dwrAddBrand.findSmallProduct(bigBrandVar,middleBrandVar,smallBrandVar , proIdClassStr, function(data){
						//alert(data);					
		 				if(data !="没有对应品牌,请确认您的查询条件!"){
		 					var json = eval("("+data+")");//前台格式化对象
							// alert(" "+lastIndex);
							//var lastIndex =	jq("#proTable>tbody>tr:last").children().html();							
							//nid=0;
							jq("#allCheckbox").attr("checked",true);
							var checkboxString = "";
							jq.each(json,function(n,v){
						//	nid++;							
							//alert(nid);
							checkboxString += 
								"<tr  >"+
									"<td >"+('X'+((nid*1)+n)*1)+"</td>"+
									"<td><input type='checkbox' class='proIdClass' value='"+v[0]+"' checked='checked'></input></td>"+
									"<td>mainPlan<input id='QplanBrandmainPlan'/></td>"+
									"<td>secondPlan<input id='QplanBrandsecondPlan'/></td>"+
									"<td>任务<select id='QplanBrandtaskType'><option value='0'>有任务</option><option value='1'>无任务</option></select></td>"+								 	
								 	"<td>Point<input id='QplanBrandfinishedPoint'/> </td>"+
								 	"<td>Name<input id='QparentPlanBrandId' value='"+v[2]+"'/></td>"+
								 	"<td>Point2<input id='QplanBrandunFinishedPoint'/> </td>"+
								 	"<td>finishedType<input id='QplanBrandfinishedType'/> </td>"+
								 	"<td><input id='QplanBrandfinishedQuota'/> </td>"+
								 	"</tr>";
								//	"<td><input type='button' class='delPro' id='del"+id+"' value='删除'></input></td>"+
								
								//checkboxString_first=checkboxString;																								
							});														
							jq("#hjk").append(checkboxString);
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
var jq = jQuery.noConflict();
	jq(function(){
		jq("#updtmiddleTable").click(function(){
//			var productProjectNameVar = jq("#productProjectName").val();
////			var productProjectTypeVar =	jq("#productProjectType").val();
////			var productProjectVal =	jq("#productProjectVal").val();
//			var	isAddBrandWagesVar = jq("#isAddBrandWages").val();			
//			if(productProjectNameVar ==""){
//				alert("单品方案名称不允许为空!");
//				return false;
//			}					
//			if(productProjectVal ==""){
//				alert("提点内容不允许为空!");
//				return false;
//			}if(isNaN(productProjectVal)){
//				alert("提点内容必须是正数!");
//				return false;
//			}			
//			if(productProjectVal.substring(0,1)=="0"){
//				if(productProjectVal.substring(0,2)!="0."){
//					alert("提点内容必须是正数!");
//					return false;
//				}
//			}else if(productProjectVal <= 0){
//				alert("提点内容必须是正数!");
//				return false;
//			}			
 			var proIdClassVar1 =	jq(".proIdClass:checked");
 			
 			
 			
 		//	if(proIdClassVar1.length <= 1){
 			//	alert("你没有选择单品!");
 			//	return false;
 		//	}			
			var proStr1 = "";
			jq.each(proIdClassVar1,function(){
				proStr1 += jq(this).val()+",";
			});
			
			
			
	 		//var BrandId = "";
//	 		var Q1 = jq(".QplanBrandId");
	// 		var Q2="";
	 		
	 //		alert("Q: " +Q1.val());
	 	//	jq.each(Q1,function(){
	 		// Q2 += jq(this).val()+",";
	 		//});
	 		
	 		//alert("Q ! " +Q2);
		 	// var Q2=jq(".QplanBrandId");
			//alert("planBrandId!" +Q2);
			
			// jq.each(planBrandId,function(){
				//BrandId += jq(this).val()+",";
			//});
			
			  //alert("planBrandId!" +BrandId);
			
			
 // 		var proIdClassVar2 =	jq(".proIdClass2:checked");
 		 //	if(proIdClassVar2.length <= 1){
 				//alert("你没有选择单品!");
 		//		return false;
 		//	}
//			
 // 			var proStr2 = "";
 // 			jq.each(proIdClassVar2,function(){
 //				proStr2 += jq(this).val()+",";
 //			});	
 
 
 //var x=document.getElementsByName("planBrandId"); 

 // var x=document.getElementsByName("planBrand[1].planBrandId"); 
 // alert("X: "+x.length); 
  
  
 var pro = "";
 perID = document.getElementsByName("QId");
 jq.each(perID,function(){
 				pro += jq(this).val()+",";
 			});	
 alert("pro :"+pro);
 
 
 			
//	 perID = document.getElementsByName("QplanBrandId");						
//		for (i = 0; i < perID.length; i++) {
//		alert(" perID['"+i+"'] :"+perID[i].value);	
//		   } 
	
			
		 	proStr1 = proStr1.substring(3,(proStr1.length-1));//截取01，和最后的，
		 	
		 //	first=
		 	
		 //	alert("截取:"+proStr1);
		 	//alert("截取后:"+proStr1);
			
 		//	 alert("截取先:"+proStr2);
//			proStr2 = proStr2.substring(3,(proStr2.length-1));//截取01，和最后的，
//			alert("截取后:"+proStr2);
			
			var projectTypeVar = jq("#projectType").val();
			var operatingMonthIdVar = jq("#operatingMonthId").val();
			
			//alert(projectTypeVar+":"+operatingMonthIdVar);
			var productProjectIdVar = jq("#productProjectId").val();
			alert(productProjectIdVar);
			
			
	dwrAddBrand.addProductProject(operatingMonthIdVar,proStr,productProjectNameVar,productProjectTypeVar,
 						productProjectVal,isAddBrandWagesVar,projectTypeVar,function(data){
 				jq("#productProjectId").val(data);
 				var answer = confirm("添加品牌项目成功!是否返回项目清单页面?");
 				if(answer){
 					return false;
 				}else{
 					return false;	
 					}
 				});		
			
			
			
			
			
			
			
//			if(productProjectIdVar =="01"){
//				dwrAddBrand.addProductProject(operatingMonthIdVar,proStr,productProjectNameVar,productProjectTypeVar,
//						productProjectVal,isAddBrandWagesVar,projectTypeVar,function(data){

//					jq("#productProjectId").val(data);
//					var answer = confirm("添加单品项目成功!是否返回项目清单页面?");
//					if(answer){
//						return false;
//					}else{
//						return false;	
//					}
//				});
//			}else{
//				alert("执行修改");
//				dwrAddBrand.updtProductProject(proStr,productProjectIdVar,
//					productProjectNameVar,productProjectTypeVar,
//						productProjectVal,isAddBrandWagesVar,function(){
					
//					var answer = confirm("修改项目成功!是否返回项目清单页面?");
//					if(answer){
//						return false;
//					}else{
//						return false;	
//					}
					
///				});
//				alert("修改完成");
//				return false;
//			}
			
			
			
			
			
			
		});
	});

</script>
<script type="text/javascript">
	jq(function(){
		jq("#allCheckbox").click(function(){
			if(jq(this).attr("checked") == true){
			jq(".proIdClass").attr("checked",true);
			//	jq(".proIdClass1").attr("checked",true);
			//	jq(".proIdClass2").attr("checked",true);
			}else{
			jq(".proIdClass").attr("checked",false);
			//	jq(".proIdClass1").attr("checked",false);
			//	jq(".proIdClass2").attr("checked",false);
				 
			}
			jq("#hjk_").attr("checked",true);
			jq("#efg_").attr("checked",true);
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
							品牌方案名称:<input id="productProjectName"></input>
						</td>
						
						<!--  
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
						</td>-->
						
						
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
									<td>品牌小类</td><td>品类小类</td><td>商品类型</td><td>商品状态</td> 
								</tr>
								
								
								<tr style="display:none;" id="hjk">
									<td>0</td><td><input type="checkbox" id="hjk_" class="proIdClass" value="01" checked="checked"></td>
									<td></td><td></td><td></td><td></td>
									<td></td><td></td><td></td>
								</tr>
								
								<tr style="display:none;" id="efg">
									<td>0</td><td><input type="checkbox" id="efg_" class="proIdClass" value="01" checked="checked"></td>
									<td></td><td></td><td></td><td></td>
									<td></td><td></td><td></td>
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

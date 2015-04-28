<%@ page language="java" import="java.util.*"
	import="com.gialen.model.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
table {
	width: 100%;
}

li {
	float: left;
}
</style>
<style type="text/css">
a:link {
	font-size: 9pt;
	color: blue;
	text-decoration: none;
	font-family: "" 宋体 "";
}

a:visited {
	font-size: 9pt;
	color: blue;
	font-family: "" 宋体 "";
}

a:hover {
	color: red;
	font-family: "" 宋体 "";
	text-decoration: underline;
}

.tableA {
	text-align: center;
	margin-bottom: 8px;
	margin-top: 8px;
	width: 98%;
	font: Georgia 11px;
	font-size: 12px;
	color: #333333;
	border-collapse: collapse; /*细线表格*/
}

.tableA th {
	border: 1px solid #CBD8AC; /*细线条颜色*/
	height: 22px;
}

.tableA td {
	border: 1px solid #CBD8AC; /*细线条颜色*/
	height: 22px;
	text-align: left;
}
</style>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.2.js"></script>
<script type='text/javascript' src='/gialenWages/dwr/engine.js'></script>
<script type='text/javascript'
	src='/gialenWages/dwr/interface/dwrAddBrand.js'></script>
<script type='text/javascript' src='/gialenWages/dwr/util.js'></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#bigBrand").change(
				function() {
					var bigBrandIdVar = jq(this).val();
					var level2 = "2";
					var level3 = "3";
					if (bigBrandIdVar != "0") {
						dwrAddBrand.findBrandByParentBrand(bigBrandIdVar,
								level2, function(data) {
									dwr.util.removeAllOptions("middleBrand");
									dwr.util.addOptions("middleBrand", {
										"0" : ''
									});
									dwr.util.addOptions("middleBrand", data);
								});
					} else { //为中品牌下拉框赋值
						dwrAddBrand.getbrandByLevel(level2, function(data) {
							dwr.util.removeAllOptions("middleBrand");
							dwr.util.addOptions("middleBrand", {
								"0" : ''
							});
							dwr.util.addOptions("middleBrand", data);
						});
					} //为小品牌下拉框赋值
					dwrAddBrand.getbrandByLevel(level3, function(data) {
						dwr.util.removeAllOptions("smallBrand");
						dwr.util.addOptions("smallBrand", {
							"0" : ''
						});
						dwr.util.addOptions("smallBrand", data);
					});
				});

		jq("#middleBrand").change(
				function() {
					var middelBrandIdVar = jq(this).val();
					var level3 = "3";
					if (middelBrandIdVar != "0") {
						dwrAddBrand.findBrandByParentBrand(middelBrandIdVar,
								level3, function(data) {
									dwr.util.removeAllOptions("smallBrand");
									dwr.util.addOptions("smallBrand", {
										"0" : ''
									});
									dwr.util.addOptions("smallBrand", data);
								});
					} else {
						dwrAddBrand.getbrandByLevel(level3, function(data) {
							dwr.util.removeAllOptions("smallBrand");
							dwr.util.addOptions("smallBrand", {
								"0" : ''
							});
							dwr.util.addOptions("smallBrand", data);
						});
					}
				});
	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#bigClass").change(
				function() {
					var bigClassVar = jq(this).val();
					var level2 = "2";
					var level3 = "3";
					if (bigClassVar != "0") {
						dwrAddBrand.findClassByParentClass(bigClassVar, level2,
								function(data) {
									dwr.util.removeAllOptions("middleClass");
									dwr.util.addOptions("middleClass", {
										"0" : ''
									});
									dwr.util.addOptions("middleClass", data);
								});
					} else {
						dwrAddBrand.getClassByLevel(level2, function(data) {
							dwr.util.removeAllOptions("middleClass");
							dwr.util.addOptions("middleClass", {
								"0" : ''
							});
							dwr.util.addOptions("middleClass", data);
						});
					}
					dwrAddBrand.getClassByLevel(level3, function(data) {
						dwr.util.removeAllOptions("smallClass");
						dwr.util.addOptions("smallClass", {
							"0" : ''
						});
						dwr.util.addOptions("smallClass", data);
					});
				});

		jq("#middleClass").change(
				function() {
					var middleClassIdVar = jq(this).val();
					var level3 = "3";
					if (middleClassIdVar != 0) {
						dwrAddBrand.findClassByParentClass(middleClassIdVar,
								level3, function(data) {
									dwr.util.removeAllOptions("smallClass");
									dwr.util.addOptions("smallClass", {
										"0" : ''
									});
									dwr.util.addOptions("smallClass", data);
								});
					} else {
						dwrAddBrand.getClassByLevel(level3, function(data) {
							dwr.util.removeAllOptions("smallClass");
							dwr.util.addOptions("smallClass", {
								"0" : ''
							});
							dwr.util.addOptions("smallClass", data);
						});
					}
				});
	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	var nid = 0;
	//var nidy = 0;
	jq(function() {
		jq("#queryProduct")
				.click(
						function() {
							var planBrandqverQuotaVal = jq(
									"#planBrandqverQuota").val();
							var planBrandqverQuotaPointVal = jq(
									"#planBrandqverQuotaPoint").val();

							if (planBrandqverQuotaVal == "") {

								alert("超定额不允许为空!");
								return false;
							}
							if (isNaN(planBrandqverQuotaVal)) {
								alert("超定额必须是正数!");
								return false;
							}
							if (planBrandqverQuotaPointVal == "") {

								alert("超定额提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandqverQuotaPointVal)) {
								alert("超定额提点必须是正数!");
								return false;
							}

							var planBrandfinishedPointVal = jq(
									"#planBrandfinishedPoint").val();

							var planBrandunFinishedPointVal = jq(
									"#planBrandunFinishedPoint").val();

							var planBrandfinishedQuotaVal = jq(
									"#planBrandfinishedQuota").val();
							var planBrandfinishedPointQuotaVal = jq(
									"#planBrandfinishedPointQuota").val();
							if (planBrandfinishedPointVal == "") {

								alert("完成提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedPointVal)) {
								alert("完成提点必须是正数!");
								return false;
							}

							if (planBrandunFinishedPointVal == "") {

								alert("未完成提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandunFinishedPointVal)) {
								alert("未完成提点必须是正数!");
								return false;
							}

							if (planBrandfinishedQuotaVal == "") {

								alert("定额不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedQuotaVal)) {
								alert("定额必须是正数!");
								return false;
							}

							if (planBrandfinishedPointQuotaVal == "") {

								alert("完成定额提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedPointQuotaVal)) {
								alert("完成定额提点必须是正数!");
								return false;
							}

							//jq("#select").change(function(){
							//var planBrandtaskTypVar_text=jq("#planBrandtaskType").find("option:selected").text();
							var planBrandtaskTypVar_text = jq(
									"#planBrandtaskType").find(
									"option:selected").text();

							var planBrandfinishedTypeVar_text = jq(
									"#planBrandfinishedType").find(
									"option:selected").text();

							var planBrandtaskTypVar = jq("#planBrandtaskType")
									.val();
							var planBrandfinishedPointVar = jq(
									"#planBrandfinishedPoint").val();
							var planBrandunFinishedPointVar = jq(
									"#planBrandunFinishedPoint").val();
							var planBrandfinishedTypeVar = jq(
									"#planBrandfinishedType").val();
							var planBrandfinishedQuotaVar = jq(
									"#planBrandfinishedQuota").val();

							var planBrandfinishedPointVar = jq(
									"#planBrandfinishedPoint").val();

							var planBrandunFinishedPointQuotaVar = jq(
									"#planBrandunFinishedPointQuota").val();
							var planBrandfinishedPointQuotaVar = jq(
									"#planBrandfinishedPointQuota").val();

							var planBrandsecondPlanVar = jq(
									"#planBrandsecondPlan").val();

							//var smallBrandVar = jq("#smallBrand").val();

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
							var ProFlagCheckedStr = jq(".ProFlag:checked");

							if (ProFlagCheckedStr.length < 1) {
								ProFlagStr = "0,1,2,3,4,5,6,7,8,9,";
							} else {
								ProFlagCheckedStr.each(function() {
									ProFlagStr += jq(this).val() + ",";
								});
							}

							ProFlagStr = ProFlagStr.substring(0,
									(ProFlagStr.length - 1));

							var ProStatusStr = "";
							var ProStatusCheckedStr = jq("input[name='Status']:checked");
							if (ProStatusCheckedStr.length < 1) {
								ProStatusStr = "0,1,2,3,4,5,6,7,8,9,";
							} else {
								ProStatusCheckedStr.each(function() {
									ProStatusStr += jq(this).val() + ",";
								});
							}
							ProStatusStr = ProStatusStr.substring(0,
									(ProStatusStr.length - 1));

							var proIdClassStr = "";
							var proIdClassVar = jq(".proIdClass4");
							jq.each(proIdClassVar, function() {
								proIdClassStr += jq(this).val() + ",";
							});

							proIdClassStr = proIdClassStr.substring(0,
									(proIdClassStr.length - 1));
							//alert(proIdClassStr);
							//nid++;				
							var lastIndex2 = jq(".proIdClass2:checkbox").length;
							lastIndex2++;
							//var lastIndex =	jq("#proTable>tbody>tr:last").children().html();

							//	nidy++;
							dwrAddBrand
									.findProductForjson(
											proIdKeywordVar,
											barcodeKeywordVar,
											proNameKeyWordVar,
											bigBrandVar,
											middleBrandVar,
											smallBrandVar,
											bigClassVar,
											middleClassVar,
											smallClassVar,
											ProFlagStr,
											ProStatusStr,
											proIdClassStr,
											function(data) {
												// alert("proIdClassStr "+proIdClassStr);

												if (data != "没有查询出对应单品或者单品已经陈列在页面上,请确认您的查询条件!") {
													var json = eval("(" + data
															+ ")");//前台格式化对象
													//alert(json);
													//	var lastIndex =	jq("#proTable>tbody>tr:first").children().html();

													//nid++;
													//var lastIndex = jq("#proTable>tbody>tr:last").children().html();

													//jq("#proTable:not(#abc,#)").html("");
													jq("#allCheckbox2").attr(
															"checked", true);
													var checkboxString = "";
													var checkboxString_first = "";
													jq
															.each(
																	json,
																	function(n,
																			v) {

																		var tdproFlag = "";
																		var proFlag = v[7];

																		if (proFlag == "0") {
																			tdproFlag = "正常商品";
																		} else if (proFlag == "1") {
																			tdproFlag = "赠品";
																		} else if (proFlag == "2") {
																			tdproFlag = "物料耗材";
																		} else if (proFlag == "3") {
																			tdproFlag = "流通商品试用装";
																		} else if (proFlag == "4") {
																			tdproFlag = "自有试用装";
																		} else if (proFlag == "5") {
																			tdproFlag = "待摊物料";
																		} else if (proFlag == "6") {
																			tdproFlag = "普通物料";
																		}

																		var tdStatus = "";
																		var proStatus = v[6];
																		if (proStatus == "0") {
																			tdStatus = "未使用";
																		} else if (proStatus == "1") {
																			tdStatus = "新品";
																		} else if (proStatus == "2") {
																			tdStatus = "正常";
																		} else if (proStatus == "3") {
																			tdStatus = "季节性禁止采购";
																		} else if (proStatus == "4") {
																			tdStatus = "停止采购";
																		} else if (proStatus == "5") {
																			tdStatus = "停止要货";
																		} else if (proStatus == "8") {
																			tdStatus = "停止销售";
																		} else if (proStatus == "9") {
																			tdStatus = "完全停用";
																		}

																		checkboxString += "<tr  >"
																				+ "<td >"
																				//+ ('Y' + ((lastIndex * 1)+ n + 1) * 1)
																				+ ('Y' + ((lastIndex2 * 1) + n) * 1)
																				+ "</td>"
																				+ "<td><input type='checkbox' class='proIdClass2'  name='items2' value='"
																				+ ((lastIndex2 * 1) + n) * 1
																				+ "'  checked='checked'></input></td>"
																				+ "<td><input id='QplanBrandmainPlan2' type='hidden' size='2' value='X' readonly='true'/></td>"
																				/* + "<td><input id='QplanBrandsecondPlan2' size='2' value='"
																				+ ('X' + 1) planBrandsecondPlanVar
																				+ "' /></td>" */
																				+ "<td><input id='QplanBrandsecondPlan2' size='2' value='"
																				+ planBrandsecondPlanVar
																				+ "' readonly='true'/></td>"
																				+ "<td><input id='QparentPlanBrandId2' value='"+v[4]+"'/></td>"
																				+ "<td><input class='proIdClass4' id='QProId2' value='"+v[0]+"' size='10'  readonly='true'/> </td>"
																				+ "<td><select id='QplanBrandtaskType2'><option value='"+planBrandtaskTypVar+"' selected='selected'>"
																				+ planBrandtaskTypVar_text
																				+ "</option> </select></td>"
																				+ "<td><input id='QplanBrandfinishedPoint2' size='2' value='"+planBrandfinishedPointVar+"'  readonly='true' /> </td>"
																				+ "<td><input id='QplanBrandunFinishedPoint2' size='2' value='"+planBrandunFinishedPointVar+"' readonly='true'/> </td>"
																				+ "<td><select id='QplanBrandfinishedType2' ><option value='"+planBrandfinishedTypeVar+"' selected='selected'>"
																				+ planBrandfinishedTypeVar_text
																				+ "</option> </select></td>"
																				+ "<td><input id='QplanBrandfinishedQuota2' size='2' value='"+planBrandfinishedQuotaVar+"' readonly='true'/> </td>"
																				+ "<td><input id='QplanBrandfinishedPointQuota2' value='"+planBrandfinishedPointQuotaVar+"' size='2'  /> </td>"
																				+ "<td><input id='QplanBrandqverQuota2' value='"+planBrandqverQuotaVal+"' size='2' readonly='true' /> </td>"
																				+ "<td><input id='QplanBrandqverQuotaPoint2' value='"+planBrandqverQuotaPointVal+"' size='2'   /> </td>"
																				+ "<td><input id='QplanBrandunFinishedPointQuota2' value='"+planBrandunFinishedPointVar+"' size='2' type='hidden'    /> </td>"
																				+ "<td>"
																				+ tdproFlag
																				+ "</td>"
																				+ "<td><input id='QplanBrandType2' value='0' type='hidden'  readonly='true'/> </td>"
																				+ "</tr>";

																		//checkboxString_first=checkboxString;

																	});
													jq("#efg").append(
															checkboxString);
													//jq("#proTable").append(checkboxString);							
													return false;
												} else {
													alert("没有对应单品,请确认您的查询条件!");
													return false;
												}
											});

							//alert("----");

						});

		jq("#addProduct")
				.click(
						function() {

							var planBrandqverQuotaVal = jq(
									"#planBrandqverQuota").val();

							var planBrandqverQuotaPointVal = jq(
									"#planBrandqverQuotaPoint").val();

							if (planBrandqverQuotaVal == "") {

								alert("超定额不允许为空!");
								return false;
							}
							if (isNaN(planBrandqverQuotaVal)) {
								alert("超定额必须是正数!");
								return false;
							}

							if (planBrandqverQuotaPointVal == "") {

								alert("超定额提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandqverQuotaPointVal)) {
								alert("超定额提点必须是正数!");
								return false;
							}

							var planBrandfinishedPointVal = jq(
									"#planBrandfinishedPoint").val();

							var planBrandunFinishedPointVal = jq(
									"#planBrandunFinishedPoint").val();

							var planBrandfinishedQuotaVal = jq(
									"#planBrandfinishedQuota").val();
							var planBrandfinishedPointQuotaVal = jq(
									"#planBrandfinishedPointQuota").val();
							if (planBrandfinishedPointVal == "") {

								alert("完成提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedPointVal)) {
								alert("完成提点必须是正数!");
								return false;
							}

							if (planBrandunFinishedPointVal == "") {

								alert("未完成提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandunFinishedPointVal)) {
								alert("未完成提点必须是正数!");
								return false;
							}

							if (planBrandfinishedQuotaVal == "") {

								alert("定额不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedQuotaVal)) {
								alert("定额必须是正数!");
								return false;
							}

							if (planBrandfinishedPointQuotaVal == "") {

								alert("完成定额提点不允许为空!");
								return false;
							}
							if (isNaN(planBrandfinishedPointQuotaVal)) {
								alert("完成定额提点必须是正数!");
								return false;
							}

							var planBrandtaskTypVar_text = jq(
									"#planBrandtaskType").find(
									"option:selected").text();

							var planBrandtaskTypVar = jq("#planBrandtaskType")
									.val();
							var planBrandfinishedPointVar = jq(
									"#planBrandfinishedPoint").val();
							var planBrandunFinishedPointVar = jq(
									"#planBrandunFinishedPoint").val();
							var planBrandfinishedTypeVar = jq(
									"#planBrandfinishedType").val();
							var planBrandfinishedQuotaVar = jq(
									"#planBrandfinishedQuota").val();

							var planBrandfinishedTypeVar_text = jq(
									"#planBrandfinishedType").find(
									"option:selected").text();
							var planBrandsecondPlanVar = jq(
									"#planBrandsecondPlan").val();
							var planBrandunFinishedPointQuotaVar = jq(
									"#planBrandunFinishedPointQuota").val();
							var planBrandfinishedPointQuotaVar = jq(
									"#planBrandfinishedPointQuota").val();

							// alert("abc");
							var bigBrandVar = jq("#bigBrand").val();
							var middleBrandVar = jq("#middleBrand").val();
							var smallBrandVar = jq("#smallBrand").val();
							var proIdClassStr = "";
							var proIdClassVar = jq(".proIdClass3");
							jq.each(proIdClassVar, function() {
								proIdClassStr += jq(this).val() + ",";
							});

							proIdClassStr = proIdClassStr.substring(0,
									(proIdClassStr.length - 1));
							//alert(proIdClassStr);
							//var id = jq("#proTable").children("table:last").attr("id");
							//	id=lastIndex;
							//id++;
							//nid++;
							//var lastIndex = jq("#proTable>tbody>tr:last").children().html();
							var lastIndex1 = jq(".proIdClass1:checkbox").length;
							lastIndex1++;
							dwrAddBrand
									.findSmallProduct(
											bigBrandVar,
											middleBrandVar,
											smallBrandVar,
											proIdClassStr,
											function(data) {
												//alert(data);					
												if (data != "没有对应品牌,请确认您的查询条件!") {
													var json = eval("(" + data
															+ ")");//前台格式化对象
													// alert(" "+lastIndex);
													//var lastIndex =	jq("#proTable>tbody>tr:last").children().html();							
													//nid=0;
													jq("#allCheckbox1").attr(
															"checked", true);
													var checkboxString = "";
													jq
															.each(
																	json,
																	function(n,
																			v) {
																		//	nid++;							
																		//alert(nid);
																		checkboxString += "<tr  class='mybgcolor'>"
																				+ "<td >"
																				+ ('X' + ((lastIndex1 * 1) + n) * 1)
																				+ "</td>"
																				+ "<td><input type='checkbox' class='proIdClass1'  name='items1' value='"
																				+ ((lastIndex1 * 1) + n) * 1
																				+ "' checked='checked' ></input></td>"
																				+ "<td><input id='QplanBrandmainPlan1' type='hidden'   value='X' readonly='true'  /></td>"
																				+ "<td><input id='QplanBrandsecondPlan1'   value='"
																				+ planBrandsecondPlanVar
																				+ "' readonly='true' size='2'/></td>"
																				+ "<td><input id='QparentPlanBrandId1' value='"+v[2]+"'/></td>"
																				+ "<td><input id='QproductBrandId1' class='proIdClass3' value='"+v[0]+"' size='10'  readonly='true' /> </td>"
																				//+ "<td><select id='QplanBrandtaskType1'><option value='1'>有任务</option><option value='0'>无任务</option></select></td>"
																				+ "<td><select id='QplanBrandtaskType1'><option value='"+planBrandtaskTypVar+"' selected='selected'>"
																				+ planBrandtaskTypVar_text
																				+ "</option> </select></td>"
																				+ "<td><input id='QplanBrandfinishedPoint1' size='2' value='"+planBrandfinishedPointVar+"'/> </td>"
																				+ "<td><input id='QplanBrandunFinishedPoint1' size='2' value='"+planBrandunFinishedPointVar+"'/> </td>"
																				+ "<td><select id='QplanBrandfinishedType1' ><option value='"+planBrandfinishedTypeVar+"' selected='selected'>"
																				+ planBrandfinishedTypeVar_text
																				+ "</option> </select></td>"
																				+ "<td><input id='QplanBrandfinishedQuota1' size='2' value='"+planBrandfinishedQuotaVar+"'/> </td>"
																				+ "<td><input id='QplanBrandfinishedPointQuota1' value='"+planBrandfinishedPointQuotaVar+"' size='2'  /> </td>"
																				+ "<td><input id='QplanBrandqverQuota1' value='"+planBrandqverQuotaVal+"' size='2' readonly='true' /> </td>"
																				+ "<td><input id='QplanBrandqverQuotaPoint1' value='"+planBrandqverQuotaPointVal+"' size='2'    /> </td>"
																				+ "<td><input id='QplanBrandunFinishedPointQuota1' value='"+planBrandunFinishedPointVal+"' size='2' type='hidden'    /> </td>"
																				+ "<td><input id='QplanBrandType1' value='1'  type='hidden' /> </td>"
																				+ "</tr>";

																		//	"<td><input type='button' class='delPro' id='del"+id+"' value='删除'></input></td>"+

																		//checkboxString_first=checkboxString;																								
																	});
													jq("#hjk").append(
															checkboxString);
													return false;
												} else {
													alert("没有对应品牌,请确认您的查询条件!");
													return false;
												}

											});
						});

	});
</script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function() {
		jq("#updtmiddleTable")
				.click(
						function() {
							var productProjectNameVar = jq(
									"#productProjectName").val();
							////			var productProjectTypeVar =	jq("#productProjectType").val();
							////			var productProjectVal =	jq("#productProjectVal").val();
							//			var	isAddBrandWagesVar = jq("#isAddBrandWages").val();			
							if (productProjectNameVar == "") {
								alert("方案名称不允许为空!");
								return false;
							}

							var planBrandtaskTypVar = jq("#planBrandtaskType")
									.val();

							 
							var PlanBrandSql = "";
							var PlanBrandSql_brand = "";
							planBrandfinishedType1 = document
									.getElementsByName("QplanBrandfinishedType1");
							planBrandfinishedType2 = document
									.getElementsByName("QplanBrandfinishedType2");
							planBrandType1 = document
									.getElementsByName("QplanBrandType1");
							planBrandType2 = document
									.getElementsByName("QplanBrandType2");
							productBrandId1 = document
									.getElementsByName("QproductBrandId1");
							productBrandId2 = document
									.getElementsByName("QproductBrandId2");
							planBrandfinishedQuota1 = document
									.getElementsByName("QplanBrandfinishedQuota1");
							planBrandfinishedQuota2 = document
									.getElementsByName("QplanBrandfinishedQuota2");
							planBrandfinishedPoint1 = document
									.getElementsByName("QplanBrandfinishedPoint1");
							planBrandfinishedPoint2 = document
									.getElementsByName("QplanBrandfinishedPoint2");
							planBrandunFinishedPoint1 = document
									.getElementsByName("QplanBrandunFinishedPoint1");
							planBrandunFinishedPoint2 = document
									.getElementsByName("QplanBrandunFinishedPoint2");
							planBrandtaskType1 = document
									.getElementsByName("QplanBrandtaskType1");
							planBrandtaskType2 = document
									.getElementsByName("QplanBrandtaskType2");
							planBrandsecondPlan1 = document
									.getElementsByName("QplanBrandsecondPlan1");
							planBrandsecondPlan2 = document
									.getElementsByName("QplanBrandsecondPlan2");
							planBrandmainPlan1 = document
									.getElementsByName("QplanBrandmainPlan1");
							planBrandmainPlan2 = document
									.getElementsByName("QplanBrandmainPlan2");
							planBrandName1 = document
									.getElementsByName("QplanBrandName1");
							planBrandName2 = document
									.getElementsByName("QplanBrandName2");
							//planBrandKeyword1 = document.getElementsByName("QplanBrandKeyword1");
							parentPlanBrandId1 = document
									.getElementsByName("QparentPlanBrandId1");
							parentPlanBrandId2 = document
									.getElementsByName("QparentPlanBrandId2");
							ProId2 = document.getElementsByName("QProId2");

							planBrandunFinishedPointQuota1 = document
									.getElementsByName("QplanBrandunFinishedPointQuota1");
							planBrandunFinishedPointQuota2 = document
									.getElementsByName("QplanBrandunFinishedPointQuota2");

							planBrandfinishedPointQuota1 = document
									.getElementsByName("QplanBrandfinishedPointQuota1");
							planBrandfinishedPointQuota2 = document
									.getElementsByName("QplanBrandfinishedPointQuota2");

							planBrandqverQuota1 = document
									.getElementsByName("QplanBrandqverQuota1");
							planBrandqverQuota2 = document
									.getElementsByName("QplanBrandqverQuota2");

							planBrandqverQuotaPoint1 = document
									.getElementsByName("QplanBrandqverQuotaPoint1");
							planBrandqverQuotaPoint2 = document
									.getElementsByName("QplanBrandqverQuotaPoint2");

							// var PlanBrandSql="insert into planBrand(planBrandName,planBrandKeyword,planBrandType,ProId,operatingMonthId,parentPlanBrandId) values(?,?)"
							jq("input[name=items1]")
									.each(
											function() {
												if (jq(this).attr("checked")) {

													 

													var Sql = "-"
															+ planBrandmainPlan1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandsecondPlan1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandtaskType1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedPoint1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandunFinishedPoint1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedType1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedQuota1[jq(
																	this).val() - 1].value
															+ ","
															+ productBrandId1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandType1[jq(
																	this).val() - 1].value
															+ ",XXXXX,"
															  /* parentPlanBrandId1[jq(
																	this).val() - 1].value  2012-9-25 需要名称  */ 
															 	
															 
															+ planBrandfinishedPointQuota1[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandunFinishedPointQuota1[jq(
																	this).val() - 1].value
															+","
															+ planBrandqverQuota1[jq(
																	this).val() - 1].value
															+","
															+ planBrandqverQuotaPoint1[jq(
																	this).val() - 1].value;
													PlanBrandSql += Sql;

												}

											});

							jq("input[name=items2]")
									.each(
											function() {
												if (jq(this).attr("checked")) {

													var Sql_brand = "-"
															+ planBrandmainPlan2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandsecondPlan2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandtaskType2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedPoint2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandunFinishedPoint2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedType2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandfinishedQuota2[jq(
																	this).val() - 1].value
															+ ","
															+ ProId2[jq(this)
																	.val() - 1].value
															+ ","
															+ planBrandType2[jq(
																	this).val() - 1].value
															+ ",XXXXX,"
															/* + parentPlanBrandId2[jq(
																	this).val() - 1].value */
															 
															+ planBrandfinishedPointQuota2[jq(
																	this).val() - 1].value
															+ ","
															+ planBrandunFinishedPointQuota2[jq(
																	this).val() - 1].value
															+","
															+ planBrandqverQuota2[jq(
																	this).val() - 1].value
															+","
															+ planBrandqverQuotaPoint2[jq(
																	this).val() - 1].value;
													PlanBrandSql_brand += Sql_brand;
												}

											});

							//	if(proIdClassVar1.length <= 1){
							//	alert("你没有选择单品!");
							//	return false;
							//	}	
							var proIdClassStr = "";
							var proIdClassVar1 = jq(".proIdClass1");
							var proStr1 = "";
							jq.each(proIdClassVar1, function() {
								proStr1 += jq(this).val() + ",";
							});
							//	alert("proStr1 :" +proStr1);

							var proIdClassVar2 = jq(".proIdClass2:checked");
							var proStr2 = "";
							jq.each(proIdClassVar2, function() {
								proStr2 += jq(this).val() + ",";
							});

							proStr1 = proStr1
									.substring(3, (proStr1.length - 1));//截取01，和最后的，

							//	first=

							//	alert("截取:"+proStr1);
							//alert("截取后:"+proStr1);

							//	 alert("截取先:"+proStr2);
							//			proStr2 = proStr2.substring(3,(proStr2.length-1));//截取01，和最后的，
							//			alert("截取后:"+proStr2);

							var projectTypeVar = jq("#projectType").val();
							var operatingMonthIdVar = jq("#operatingMonthId")
									.val();

							//alert(projectTypeVar+":"+operatingMonthIdVar);
							var productProjectIdVar = jq("#productProjectId")
									.val();
							//alert(productProjectIdVar);

						//	 alert("PlanBrandSql :"+PlanBrandSql);	
						//	  alert("PlanBrandSql_brand :"+PlanBrandSql_brand);	
							var operatingMonthIdVar = jq("#operatingMonthId")
									.val();
									
									
									
									
									
							dwrAddBrand
									.addProduct_Project(
											operatingMonthIdVar,
											PlanBrandSql,
											PlanBrandSql_brand,
											productProjectNameVar,
											planBrandtaskTypVar,
											/* planBrandtaskTypVar,
											operatingMonthIdVar,
											PlanBrandSql,
											productProjectNameVar,
											productProjectTypeVar,
											productProjectVal,
											isAddBrandWagesVar,
											projectTypeVar, */
											function(data) {
												jq("#productProjectId").val(
														data);
												var answer = confirm("添加品牌项目成功!是否返回项目清单页面?");
												if (answer) {
												location.href ="admin_arrangeBrand_addProductProject.action?"+
					"operatingMonthType="+"${param.operatingMonthType}&projectType="+"${param.projectType}&"+
						"operatingMonthId="+jq("#operatingMonthId").val();
													return false;
												} else {
													return false;
												}
											});
						});
	});
</script>
<script type="text/javascript">
	jq(function() {
		jq("#allCheckbox1").click(function() {
			if (jq(this).attr("checked") == true) {
				jq(".proIdClass1").attr("checked", true);

			} else {
				jq(".proIdClass1").attr("checked", false);

			}
			jq("#hjk_").attr("checked", true);
			//jq("#efg_").attr("checked",true);
		});

		jq("#allCheckbox2").click(function() {
			if (jq(this).attr("checked") == true) {
				jq(".proIdClass2").attr("checked", true);

			} else {
				jq(".proIdClass2").attr("checked", false);

			}
			//jq("#hjk_").attr("checked",true);
			jq("#efg_").attr("checked", true);
		});
	});
</script>
<script type="text/javascript" charset="gb2312" src="js/mybgcolor.js"></script>
</head>

<body>
	<input type="hidden" name="operatingMonthType"
		value="${param.operatingMonthType}"></input>
	<input type="hidden" name="projectType" value="${param.projectType}"></input>
	<table class="tableA">
		<tr>
			<td align="center">品牌提成计划填写表</td>
		</tr>
	</table>
	<table class="tableA">
		<tr>
			<td>单品编码:<input id="proIdKeyword" name="proIdKeyword" />
			</td>
			<td>单品条码:<input id="barcodeKeyword" name="barcodeKeyword" />
			</td>
			<td>单品关键字:<input id="proNameKeyWord" name="proNameKeyWord" />
			</td>
			<td rowspan="5" align="center"><input id="addProduct"
				class="addProduct" type="button" value="绑定品牌" />
			</td>
			<td rowspan="5" align="center"><input id="queryProduct"
				class="queryProduct" type="button" value="查询单品" />
			</td>
		</tr>
		<tr>
			<td colspan="3">品牌大类: <select id="bigBrand" class="brandClass">
					<option value="0"></option>
					<c:forEach items="${requestScope.productBigBrandList}" var="brand">
						<option value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
			</select> 品牌中类:<select id="middleBrand" class="brandClass">
					<option value="0"></option>
					<c:forEach items="${requestScope.productMiddleBrandList}"
						var="brand">
						<option value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
			</select> 品牌小类:<select id="smallBrand">
					<option value="0"></option>
					<c:forEach items="${requestScope.productSmallBrandList}"
						var="brand">
						<option value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td colspan="3">类别大类:<select id="bigClass">
					<option value="0"></option>
					<c:forEach items="${requestScope.productBigClassList}" var="class">
						<option value="${class.classId}">${class.className}</option>
					</c:forEach>
			</select> 类别中类:<select id="middleClass">
					<option value="0"></option>
					<c:forEach items="${requestScope.productMiddleClassList}"
						var="class">
						<option value="${class.classId}">${class.className}</option>
					</c:forEach>
			</select> 类别小类:<select id="smallClass">
					<option value="0"></option>
					<c:forEach items="${requestScope.productSmallClassList}"
						var="class">
						<option value="${class.classId}">${class.className}</option>
					</c:forEach>

			</select></td>
		</tr>
		<tr>
			<td colspan="3">商品类型: <input type="checkbox" class="ProFlag"
				name="ProFlag" value="0" checked="checked" />正常商品 <input
				type="checkbox" class="ProFlag" name="ProFlag" value="1"
				checked="checked" />赠品 <input type="checkbox" class="ProFlag"
				name="ProFlag" value="2" />物料耗材 <input type="checkbox"
				class="ProFlag" name="ProFlag" value="3" />流通商品试用装 <input
				type="checkbox" class="ProFlag" name="ProFlag" value="4" />自有试用装 <input
				type="checkbox" class="ProFlag" name="ProFlag" value="5" />待摊物料 <input
				type="checkbox" class="ProFlag" name="ProFlag" value="6" />普通物料</td>
		</tr>
		<tr>
			<td colspan="3">单品状态: <input type="checkbox" name="Status"
				value="0" />未使用 <input type="checkbox" name="Status" value="1"
				checked="checked" />新品 <input type="checkbox" name="Status"
				value="2" checked="checked" />正常 <input type="checkbox"
				name="Status" value="3" />季节性禁止采购 <input type="checkbox"
				name="Status" value="4" />停止采购 <input type="checkbox" name="Status"
				value="5" />停止要货 <input type="checkbox" name="Status" value="8" />停止销售
				<input type="checkbox" name="Status" value="9" />完全停用</td>
		</tr>
	</table>
	<table id="2" class="tableA">
		<tr class="titleClass" colspan="6">
			<td><input id="operatingMonthId" type="hidden"
				value="${requestScope.thisoperatingMonth.operatingMonthId}"></input>
				${requestScope.thisoperatingMonth.operatingMonthName} 方案名称<input
				id="productProjectName" size='10'></input> 副任务<select
				id="planBrandsecondPlan">
					<option value="X1">X1</option>
					<option value="X2">X2</option>
					<option value="X3">X3</option>
					<option value="X4">X4</option>
					<option value="X5">X5</option>
					<option value="X6">X6</option>
					<option value="X7">X7</option>
					<option value="X8">X8</option>
					<option value="X9">X9</option>

			</select> <select id="planBrandtaskType">

					<option value="0">无任务</option>
					<option value="1">有任务</option>
			</select> 完成提点<input id="planBrandfinishedPoint" size='10' value="0"></input>
				未完成提点<input id="planBrandunFinishedPoint" size='10' value="0"></input>
			</td>


			<td rowspan="2"><input type="button" id="updtmiddleTable"
				class="updtmiddleTable" value="提交" />
			</td>
		</tr>

		<tr class="titleClass">
			<td colspan="6"><select id="planBrandfinishedType">
					<option value="0">无完成定额</option>
					<option value="1">有完成定额</option>
			</select>定额<input id="planBrandfinishedQuota" size='10' value="0"></input>
				完成定额提点<input id="planBrandfinishedPointQuota" size='10' value="0"></input>
				<input id="planBrandunFinishedPointQuota" size='10' value="0"
				type="hidden"></input> 超定额<input id="planBrandqverQuota" size='10'
				value="100" readonly="true"></input> 超定额提点<input id="planBrandqverQuotaPoint"
				size='10' value="0"></input>
			</td>
		</tr>
	</table>
	<table id="proTable" class="tableA">
		<tr id="abc">
			<td>序号</td>
			<td>选择<input id="allCheckbox1" type="checkbox" checked="checked"></input>
			</td>
			<td></td>
			<td>副任务</td>
			<td>品牌小类</td>
			<td>品牌代码</td>
			<td>是否有任务</td>
			<td>完成提点</td>
			<td>未完成提点</td>
			<td>是否有完成定额</td>
			<td>定额</td>
			<td>完成定额提点</td>
			<td>超定额</td>
			<td>超定额提点</td>
			<td></td>
		</tr>


		<tr style="display:none;" id="hjk">
			<td>0</td>
			<td><input type="checkbox" id="hjk_" class="proIdClass"
				value="01" checked="checked">
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr id="abc">
			<td>序号</td>
			<td>选择<input id="allCheckbox2" type="checkbox" checked="checked"></input>
			</td>
			<td></td>
			<td>副任务</td>
			<td>商品名称</td>
			<td>商品编号</td>
			<td>是否有任务</td>
			<td>完成提点</td>
			<td>未完成提点</td>
			<td>是否有完成定额</td>
			<td>定额</td>
			<td>完成定额提点</td>
			<td>超定额</td>
			<td>超定额提点</td>
			<td></td>
			<td>商品类型</td>


		</tr>
		<tr style="display:none;" id="efg">
			<td>0</td>
			<td><input type="checkbox" id="efg_" class="proIdClass"
				value="01" checked="checked">
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
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
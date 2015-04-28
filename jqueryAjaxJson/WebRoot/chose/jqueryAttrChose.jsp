<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryAttrChose.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body {
			text-align: right;	
	}
	</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#attrChose").click(function(){
			//选择有title属性的a标签
			$("a[title]").css("text-decoration","none");
		});
		
		$("#attrValChose").click(function(){//选择有class属性等于,不等于的hasTitle的a标签
			$("a[class != hasTitle]").css("color","#FFFF00");
			$("a[class = hasTitle]").css("color","#99FF00");
		});
		
		$("#attrStarChose").click(function(){
			$("a[class ^= one]").css("background","#9999CC"); //有class属性，并且值以one开头的a标签
			$("a[class $= Title]").css("text-decoration","line-through");//有class属性，并且值以Title结尾的a标签
			$("a[class *= no]").css("font-size","24px");//有class属性，并且值有no的a标签,类似sql的like查询
		});	
	
		$("#doubleAttrChose").click(function(){
			$("a[title=abc][class=oneTitle]").css("font-size","24px");				
		});
		
		$("#firstLastChose").click(function(){
			
			$("div:first-child").each(function(){//唔明
				alert($(this).html());
			});
			$("div:last-child").each(function(){//唔明
				alert($(this).html());
			});
		});
		
		$("#onlyChildChose").click(function(){
				$("span:only-child").each(function(){//唔明
					alert($(this).html());
				});
		});
		
		$("#nth-child").click(function(){
			$("tr:nth-child(1)").css("font-size","24px");//第一个元素，不是下标
			//$("tr:nth-child(3n)").css("background","#CC00FF");//倍数为3的元素，不是下标
			$("tr:nth-child(3n+2)").css("background","#CC00FF");//第二个元素开始(含本元素)倍数为3的元素，不是下标
			//$("tr:nth-child(odd)").css("background","#CCCC00"); //奇数
			//$("tr:nth-child(even)").css("background","#99CC00");//偶数
		});
		
		$("#enabledDisabled").click(function(){
			$(".enabledDisabled:enabled").css("background","#CC00FF");
			$(".enabledDisabled:disabled").css("background","#CCCC00");
		});
		
		$("#checked").click(function(){
			 $(":checked").each(function(){
					alert($(this).val()); //对于select下拉框如果部默认添加checked属性则无法查找到
			 });
		});
		
		$("#select").click(function(){
			$(":selected").each(function(){
				alert($(this).val());
			});
		});
		
		$("#inputChose").click(function(){
			alert($(".formChose:text").val());
		});
		
		$("#passwordChose").click(function(){
			alert($(".formChose:password").val());
		});
		
		$("#radioChose").click(function(){
			$(".formChose:radio").each(function(){
				alert($(this).val());
			});
		});
		
		$("#checkboxChose").click(function(){
			$(".formChose:checkbox:checked").each(function(){
				alert($(this).val());
			})
		});
	});
</script>
  </head>
  
  <body>
  	<input type="button" id="attrChose" value="[attr]属性选择器"/><br/>
  	<input type="button" id="attrValChose" value="attr = Val属性选择器"/><br/>
  	<input type="button" id="attrStarChose" value="attrVal属性选择器"/><br/>
  	<input type="button"id="doubleAttrChose" value="复合属性选择器" /><br/>
	<a href="#" title="abc" class="hasTitle">有title的a标签</a><br/>
	<a href="#" class="nohasTitle">没有title的a标签</a><br/>			
	<a href="#" title="abc" class="oneTitle">class为oneTitle的a标签</a><br/>
	
	<input type="button" id="firstLastChose" value="firstLastChild子元素过滤选择器(唔明)"/><br/>
	<div>123
		<div>345
			<div></div>
		</div>
	</div>
	
	<input type="button" id="onlyChildChose" value="onlyChild选择器(唔明)" /><br/>
	<span>123
		<span>456
			<span></span>
		</span>
	</span><br/>
	
	<input type="button" id="nth-child" value=":nth-child选择器" /><br/>
	<table>
		<tr><td>abc</td></tr>
		<tr><td>def</td></tr>
		<tr><td>ghi</td></tr>
		<tr><td>jkl</td></tr>
		<tr><td>mno</td></tr>
		<tr><td>pqr</td></tr>
	</table>
	<input type="button" id="enabledDisabled" value="enabledDisabled选择器"/><br/>
	<input class="enabledDisabled" disabled="disabled" value="enabled"/><br/>
	<input class="enabledDisabled" value="Disabled"/><br/>
	
	<input type="button" id="checked" value="checked选择器"/><br/>
	<input type="button" id="select" value="select选择器"/><br/>
	<input type="checkbox" value="abc"/>abc<br/>
	<select name="select">
		<option value="abc">abc</option><!-- checked=checked -->
		<option value="def">def</option>
	</select><br/>
	
	<input id="inputChose" class="formChose" type="button" value=":input选择器"/><br/>
	<input id="passwordChose" class="formChose" type="button" value="password选择器"/><br/>
	<input id="radioChose" class="formChose" type="button" value="radioChose选择器"/><br/>
	<input id="checkboxChose" class="formChose" type="button" value="checkboxChose选择器"/><br/>
	<input class="formChose" value="input text选择器" /><br/>
	<input class="formChose" type="password" value="password选择器"/><br/>
	<input class="formChose" name="formChoseRadio" type="radio" value="a"/>a
	<input class="formChose" name="formChoseRadio" type="radio" value="b"/>b
	<input class="formChose" name="formChoseRadio" type="radio" value="c"/>c<br/>
	<input class="formChose" name="formChoseCheckbox" type="checkbox" value="a"/>a
	<input class="formChose" name="formChoseCheckbox" type="checkbox" value="b"/>b
	<input class="formChose" name="formChoseCheckbox" type="checkbox" value="c"/>c
	
  </body>
</html>

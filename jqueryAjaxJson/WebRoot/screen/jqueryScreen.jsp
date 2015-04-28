<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryScreen.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testEQ").click(function(){
			alert("eq(1)结果:"+$("p").eq(1).html());//正数的时候取下标
			alert("eq(-1)结果:"+$("p").eq(-1).html());//负数的时候取序列
		});
		$("#testFirst").click(function(){
			alert($("p").first().html());//相当于:alert($("p:first").html());
		});
		$("#testLast").click(function(){
			alert($("p").last().html());
			//alert($("p:last").html());
		});
		$("#testHasClass").click(function(){
			$("p").each(function(){
				alert($(this).hasClass("testHasClass"));					
			});
		});
		$("#testfilter").click(function(){
			alert($("span").filter(".pFilter").html());//获得有class为pFilter的span
		});
		$("#testfirstFilter").click(function(){
			alert($("span").filter(".firstFilter, :first").html());//第一个span并且class为firstFilter
			//alert($("span.firstFilter:first").html()); //相当于
		}); 
		$("#testnotFilter").click(function(){//选择自身中没有<b>的span 
			var test =  $("span").filter(function(data){
				return $("b",this).length == 0; //查询条件只适用html元素，不支持id class
			});
			test.each(function(){
				alert($(this).html());
			});
		});
		
		$("#testis").click(function(){
				var isTrue = $(":checkbox").parent().is("form");			
				alert(isTrue);
		});
		
		$("#ishadStrong").click(function(){
			$("ul li").each(function(){//判断所有li中是否含有strong的元素
			  var ishadStrong = $(this).is(function(){
					return $("strong",this).length != 0;
				});
			  if(ishadStrong){
				  $(this).css("color","red");
			  }else{
				  $(this).css("color","#99FF33");
			  }
			});
		});
		
		$("#hasStrong").click(function(){
			$("ul li").has("strong").css("font-size","24");
		});
		
		$("#mapId").click(function(){//同each有咩唔同?
			$("#mapP").append($(".mapClass").map(function(){
				return $(this).val();
			}).get().join(","));		
		});
		
		$("#testNot").click(function(){
			//alert(($("#nNot").not($(".testNot"))).html());
			//alert(($("#nNot").not($("#firstSpan")[0])).html());
			//貌似只支持纯html元素
			alert(($("p").not( $("#selected")[0]).html()));//取没有id为select的P 
		});
		
		$("#testSlice").click(function(){
			//alert($(".testSlice").slice(0,1).html());//第一个 testSlice,下标为0开始，1结束(不包含1)
			$(".testSlice").slice(0,2).each(function(){ //第一二个 testSlice 下标为0开始，2结束(不包含2)
				//alert($(this).html());
			});
			$(".testSlice").slice(1,2).each(function(){//第二个 testSlice 下标为1开始，2结束(不包含2)
				//alert($(this).html());
			});
			$(".testSlice").slice(1).each(function(){ //区下标1(不包含1)的元素，直到最后
				//alert($(this).html());
			});
			$(".testSlice").slice(-1).each(function(){//直接取最后一个
				alert($(this).html());
			});
		});
		
		$("#testChildren").click(function(){
			$("span").children(".testChildren").each(function(){
				alert($(this).html());
			});
		});
		
		$("#testFind").click(function(){
			$("span").find("span").each(function(){
				alert($(this).html());
			});
		});
		
		$("#testNext").click(function(){//id=testNextSpan下一个元素
			$("#testNextSpan").next().each(function(){//只是一个元素 API有点误导
				alert($(this).html());
			});
			$("p").next().each(function(){//都是p的下一个同级元素 
				//alert($(this).html());其实$("p").next()只是下一个下一个元素这么查找，并不是返回一个集合,nextAll()才是
			})
		});
		
		$("#testNextall").click(function(){
			$("#testNextSpan").nextAll().each(function(){
				alert($(this).html());
			});
		});
		
		$("#testNextUntil").click(function(){//从#testNextSpan的元素(不含该元素)开始直到找到P的同辈元素
			$("#testNextSpan").nextUntil("p").each(function(){
				alert($(this).html());				
			});
		});
		$("#testParents").click(function(){//所有父元素是span的元素
			$("span").parents("p").each(function(){
				alert($(this).html());
			});
		});
		$("#testParentsUntil").click(function(){
			$("#childSpan").parentsUntil("#parentsUntil").css("font-size","24px");
		});
		$("#testPrev").click(function(){
			$("#span3").prev("#span2").each(function(){
				alert($(this).html());
			});
		});
		$("#testPrevAll").click(function(){
			$("#span3").prevAll().each(function(){
				//alert($(this).html());
			});
		});
		$("#testPrevUntil").click(function(){//取从span3(不包含)到span1(不包含)的元素
			$("#span3").prevUntil("#span1").each(function(){
				alert($(this).html());
			});
		});
		
		$("#testSiblings").click(function(){ //查询#span2所有同辈的span
			$("#span2").siblings("span").each(function(){
				alert($(this).html());
			});
		});
		$("#testAndSelf").click(function(){ //.andSelf()包含自己
				//$("#andSelfSpan").find("p").css("font-size","24px");	
				$("#andSelfSpan").find("p").css("font-size","24px");	
		});
		
		$("#testContents").click(function(){
			$("#andSelfSpan").contents().not("[nodeType=1]").wrap("<b/>");
		});
	});
	
</script>  
  </head>
  
  <body>
  <input id="testEQ" type="button" value="测试eq"/>
  <input id="testFirst" type="button" value="测试first()"/>
  <input id="testLast" type="button" value="测试last()"/>
  <input id="testHasClass" type="button" value="测试hasClass()"/>
  <p class="testHasClass">abc</p><p>def</p><br/>
  
  <input id="testfilter" type="button" value="测试filter()"/>
   <input id="testfirstFilter" type="button" value="测试first&&Filter()"/>
   <input id="testnotFilter" type="button" value="测试notFilter()"/><br/>
  <span class="firstFilter">a</span><span>b</span><span class="pFilter"><b></b>c</span><br/>

  <br/>
  <input id="testis" type="button" value="判断checkbox的父元素是否为form"/><br/>  
  <form><input type="checkbox"/>判断checkbox</form>
  
  <input id="ishadStrong" type="button" value="判断是否(is)有strong"/>
   <input id="hasStrong" type="button" value="判断是否有(has)strong"/>
  <ul>
  	<li><strong>abc</strong></li>
  	<li>def</li>
  	<li>ghi</li>
  </ul>
  
  <input id="mapId" type="button" value="测试map()"/>
  <input class="mapClass" value="A"/>
  <input class="mapClass" value="B"/>
  <input class="mapClass" value="C"/>
  <P id="mapP"></P><br/>
  
  <input id="testNot" type="button" value="测试not"/>
  <p id="nNot"><span id="firstSpan" class="testNot">abc</span><span class="testNot">def</span></p>
  <p>Hello</p><p id="selected">Hello Again</p><div>helllo thirth</div><br/>
  
  <input id="testSlice" type="button" value="测试slice"/>
  <form>
		<p class="testSlice">sliceABC</p><p class="testSlice">sliceDEF</p><p class="testSlice">sliceGHI</p>  
  </form>
  <input id="testChildren" type="button" value="测试children"/>
  <input id="testFind"type="button" value="测试find()"/>
  <input id="testNext" type="button" value="测试next()"/>
  <input id="testNextall" type="button" value="测试nextAll()"/>
  <input id="testNextUntil" type="button" value="测试nextUntil()"/>
  <br/>
  <span>
  	<span id="testNextSpan" class="testChildren">ChildrenABC</span>
  	<span class="testChildren">ChildrenDEF</span>
  	<span class="classNext">ChildrenGHI</span>
  	<p>ChildrenJKL</p>
  </span><br/>
  
  <input type="button" id="testParents" value="测试Parents()"/><br/>
 	<p><span>ParentsABC</span></p><p><span>ParentsDEF</span></p>
 	
  <input type="button" id="testParentsUntil" value="测试ParentsUntil"/><br/>
  <span id="parentsUntil"><span>htl<span>efg<span id="childSpan">abc</span></span></span></span><br/>
  
  <input type="button" id="testPrev" value="测试prev"/>
  <input type="button" id="testPrevAll" value="测试prevAll()"/>
  <input type="button" id="testPrevUntil" value="测试PrevUntil"/>
  <input type="button" id="testSiblings" value="测试siblings()">
  <form><P>this is p!</P>
  	<span id="span1">ghi</span><span id="span2">def</span><span id="span3">abc</span>
  </form>
  
  <input type="button" id="testAndSelf" value="测试AndSelf()">
   <input type="button" id="testContents" value="测试Contents()">
  <br/>
  <span id="andSelfSpan">abc
  		<p>def</p><p>ghi</p>
  </span>
  
 
  	
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testjquery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
	
	var jq = jQuery.noConflict();
	
	jq(function(){
		 jq(".addButton").each(function(){
			 jq(this).click(function testFunction(){
			 var id = jq("#correspondingTable>tbody>tr:last").attr("id");
			 id++;
				var addTr = "<tr id='"+id+"'>"+
					"<td><input id='name1' value='梁健康"+id+"'></input><td>"+
			    	"<td>"+
			    		"<input id='addButton"+id+"'  class='addButton'  type='button' value='提交'></input>"+
						"<input id='updtButton"+id+"' class='updtButton'  type='button' value='修改'></input>"+
						"<input id='delButton"+id+"'  class='delButton'  type='button' value='删除'></input>"+
			    	"<td>"+
				"</tr>";
				
				jq("#correspondingTable").append(addTr);
				
				jq("#addButton"+id).click(function(){
					var addButtonVar = jq(this).val();
					if(addButtonVar == "提交"){
						jq(this).val("添加");
					}else{ //这里递归!
						testFunction();
					}
				});
				
				jq("#updtButton"+id).click(function(){
					alert("修改"+id);
				});
				
				jq("#delButton"+id).click(function(){
					alert("删除"+id);
				});
				
			});
		});
	});
	</script>
	<script type="text/javascript">
	var jq = jQuery.noConflict();
	jq(function(){
		jq("#ids").click(function(){
			var add = jq("ul li").css("display");
			if(add == "block"){
				jq("ul li").hide();
			}else{
				jq("ul li").show();
			}
		});
	});
	</script>
  </head>
  
  <body>
   <a id="ids" href="javascript:void(0);">点击</a>
  <ul>
 	 <li>A</li>
 	 <li>B</li>
 	 <li>C</li>
  </ul><br>
  
  
  
  
  <table border="1" id="correspondingTable">
   	<tr id="0"><td>名字<td><td>操作<td><tr>
    <tr id="1">
    	<td><input id="name1" value="梁健康"></input><td>
    	<td>
    		<input id="addButton1"  class="addButton"  type="button" value="添加"></input>
			<input id="updtButton1" class="updtButton" type="button" value="修改"></input>
			<input id="delButton1"  class="delButton"  type="button" value="删除" ></input>
    	<td>
    </tr>
   </table>	
   	
  </body>
</html>

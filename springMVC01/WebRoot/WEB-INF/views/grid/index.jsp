<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<!-- 
jqGrid采用了jQueryUI的CSS主题，需要下载相应的主题http://jqueryui.com/themeroller/ 
找到相应的主题下载，如果使用了datepicker控件，还需要ui。如下：
<link rel="stylesheet" type="js/jquery-ui-1.9.0.custom.min.js" media="all"/>  
<script type="text/javascript" src="js/jquery-ui-1.9.0.custom.min.js"></script>   -->    
<!-- 
<script type="text/javascript" src="$request.contextPath/script/ui/jquery.ui.datepicker-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="css/layout-default-latest.css" />
 -->
<!-- 
  <link rel="stylesheet" type="text/css" href="css/ui.login.css" />  
  <link rel="stylesheet" type="text/css" media="screen" href="css/tiptip.css">
  <link rel="stylesheet" type="text/css" media="screen" href="css/jquery.loadmask.css">
  <link rel="stylesheet" type="text/css" media="print" href="css/print.css">
 <link rel="stylesheet" type="text/css" href="css/dtree.css" /> 
    <link rel="stylesheet" type="text/css" href="css/parts/sidemenu.css" /> 
     <link rel="stylesheet" type="text/css" href="css/parts/inputform.css.jsp" /> 
   <link rel="stylesheet" type="text/css" href="css/parts/structure_light.css" /> 
   <link rel="stylesheet" type="text/css" href="css/typographyEN.css" />     
      -->
   <!--  
   <link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" />  
   <script type="text/javascript" src="jqGrid/jquery-1.7.2.js"></script>
   <script type="text/javascript" src="jqGrid/grid.locale-en.js"></script>
   <script type="text/javascript" src="jqGrid/jquery.jqGrid.min.js"></script>--> 
   	<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" /> 
	<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
	<script type="text/javascript" src="js/grid.locale-cn.js"></script>  
	<script type="text/javascript" src="js/jquery.jqGrid.src.js"></script>    
	<script type="text/javascript" src="js/jquery-ui-1.9.0.custom.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/custom/jquery-ui-custom.css.jsp" />   
  <style type="text/css">
  
  	.ui-jqgrid-sortable{
  		
  	}
  
  </style>
  <script type="text/javascript">
  	
  </script>
   <script type="text/javascript">
   $(function(){
	   
	  // alert("agd");
	   /**/
	   $("#testButton").click(function(){
			// alert("abc");
			var $table = $("#first");
			$table.jqGrid('setGridParam',{datatype:'json'});
			$table.jqGrid('setGridParam',{rowNum:5}).trigger('reloadGrid');
			$(".ui-pg-selbox").val(5);
		 });
	   
	   /*
	   $("#testButton2").click(function(){
			// alert("abc");
			var $table = $("#first2");
			$table.jqGrid('setGridParam',{datatype:'json'});
			$table.jqGrid('setGridParam',{rowNum:5}).trigger('reloadGrid');
			$(".ui-pg-selbox").val(5);
		 });*/
	  
	   
	   
	 initFirstList("first");
	 
	// initFirstList2("first2");
	
   });
   
   function setGridPageNum(id){
	   
			var count= jQuery("#"+id).getGridParam('records');
			if(count==0){
				$('#gbox_'+id+' input.ui-pg-input').val("1");
				$('#gbox_'+id+' input.ui-pg-input + span').text("1");
			}
			
		}
   
   
   function initFirstList(id){
	   
	   var tableVar = $("#"+id);
	   var page = "firstPage";
	   
	   tableVar.jqGrid({
		//
		   url:"${pageContext.request.contextPath}/grid/getFirstList.html",
			  mtype:"post",		   
			 // loadonce:false,
			  datatype:"local", //为local时初始化不加载，支持json，xml等 
			  height:"auto",
			  //autoheigth:false,
			  //shrinkToFit:false,
			  //autowidth:false,
			  //loadui: "disable",
			  autowidth: true,
			  width:"100%",
			 // multiselect: true, //多选框
			 //multiboxonly: true,
			  colNames:["用户名","账号","真实名称"],
			  //这里会根据index去解析jsonReader中root对象的属性，填充cell    
			  colModel:[{name:"userName",index:"userName",width :200,sortable:true},
			            {name:"passWord",index:"passWord",width :200,sortable:true},
			            {name:"realName",index:"realName",width :200,sortable:true}],
			  rowNum : 5,
			  rowList : [ 5, 10, 20, 30, 50, 100],
			  pager : "#firstPage",	
			  caption :"testLocal", //table名称
			  jsonReader:{
				  root:"voList", //返回的数据list
				  page:"page",   //当前页码
				  total:"totalPage", //总共页数
				  records:"totalCount",//总记录数
				  repeatitems:false,//表示 voList中对象的属性要按顺序,默认不用   指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
			  },
			  prmNames:{page:"page",rows:"size", sort: "sidx", order: "direction", search:"_search"},
			  
			//显示记录数信息，如果这里设置为false,下面的不会显示 recordtext: "第{0}到{1}条, 共{2}条记录", 
			//默认显示为{0}-{1} 共{2}条 scroll: false, //滚动翻页，设置为true时翻页栏将不显示
			  viewrecords: false, 
			  /**这里是排序的默认设置，这些值会根据列表header点击排序时覆盖*/
			  sortable: false,    
			  sortname: "userName",    
			  sortorder: "desc",
			  rownumbers: true, //设置列表显示序号,需要注意在colModel中不能使用rn作为index 
			  rownumWidth: 25, //设置显示序号的宽度，默认为25
			  multiselect: false, //多选框 
			  multiboxonly: false, //在点击表格row时只支持单选，只有当点击checkbox时才多选，需要multiselect=true是有效 
			  gridComplete:function(){
				  setGridPageNum("first");
			  },
			  
			  loadComplete : function(xtr){
				 //加载完成触发
				// alert("a");
			  },
			  onSelectRow:function(rowid){
				 /*
				  var rowdata= tableVar.jqGrid("getRowData", rowid);
				  $("#userNameId").val(rowdata.userName);
				  $("#passWordId").val(rowdata.passWord);
				  $("#realNameId").val(rowdata.realName);
				  
				  $.extend(rowdata,{"userName":"change"});
				  
				  tableVar.jqGrid("setRowData", rowid, rowdata); 
				  */ 
				  //tableVar.jqGrid("delRowData", rowid); //前台删除
			  }
		  
	   });
//.navGrid('#firstPage', { add: true, edit: true, del: true,search:false,refresh:false });	   
   };
   
   </script>

 
  </head>
  
  
  <style type="text/css">
  	.ui-icon { display: block; text-indent: -99999px; overflow: hidden; background-repeat: no-repeat; }
  	.ui-icon { width: 16px; height: 16px; background-image: url(images/ui-icons_222222_256x240.png); }
 	.ui-icon {background-image: url(images/ui-icons_222222_256x240.png); }
 	.ui-icon { position: absolute; left: .5em; top: 50%; margin-top: -8px; }
  	.ui-icon { position: absolute; top: 50%; margin-top: -8px; }
   	.ui-icon { left: 50%; margin-left: -8px; }
  </style>
  <body>
       
    <!--ui-icon-asc  ui-grid-ico-sort  ui-sort-ltr -->
    <!-- ui-icon-triangle-1-n(浅黑) ui-state-disabled(深黑) -->
    <span  class="ui-icon"></span>
  
  
  <hr>
  
    This is my grid  index.jsp <input type="button" value="点击" id='testButton'/><br>
    
    <table id="first"></table>
    <div id="firstPage"></div>
    
    <br>
    <br>

    
    
    
    
    
    <!--   
    <table>
    	<tr>
    		<td>用户名:<input id="userNameId"></input></td>
    		<td>密码:<input id="passWordId"></input></td>
    		<td>真实名称:<input id="realNameId"></input></td>
    	<tr>
    </table>
    -->
    
    <br>
    
 
    

    
    <script type="text/javascript">
 /*
 function  initFirstList2(id){
	 
	  var tableVar = $("#"+id);
	  var page = "firstPage2";
	   
	  tableVar.jqGrid({
	        url: "${pageContext.request.contextPath}/grid/getFirstList.html",
	        datatype: "local",
	        height: "auto",
	        pager: "#firstPage2",
	        loadui: "disable",
	        colNames:["用户名","账号","真实名称"],
			  //这里会根据index去解析jsonReader中root对象的属性，填充cell    
			colModel:[{name:"userName",index:"userName",width :200,sortable:true},
			            {name:"passWord",index:"passWord",width :200,sortable:true},
			            {name:"realName",index:"realName",width :200,sortable:true}],
			rowNum : 5,
			rowList : [ 5, 10, 20, 30, 50, 100],
			pager : "#firstPage2",	
	        treeGrid: true,
			caption: "jqGrid Demos",
	        ExpandColumn: "menu",
	        autowidth: true,
	        //width: 180,
	        rowNum: 200,
	        ExpandColClick: true,
	        treeIcons: {leaf:'ui-icon-document-b'},
	        prmNames:{page:"page",rows:"size", sort: "sidx", order: "direction", search:"_search"},
	        viewrecords: false, 
			  //这里是排序的默认设置，这些值会根据列表header点击排序时覆盖
			  sortable: false,    
			  sortname: "userName",    
			  sortorder: "desc",
			  rownumbers: true, //设置列表显示序号,需要注意在colModel中不能使用rn作为index 
			  rownumWidth: 25, //设置显示序号的宽度，默认为25
			  multiselect: false, //多选框 
			  multiboxonly: false, //在点击表格row时只支持单选，只有当点击checkbox时才多选，需要multiselect=true是有效 
	        jsonReader:{
				  root:"voList", //返回的数据list
				  page:"page",   //当前页码
				  total:"totalPage", //总共页数
				  records:"totalCount",//总记录数
				  repeatitems:false,//表示 voList中对象的属性要按顺序,默认不用   指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
			  },
	        onSelectRow: function(rowid) {
	        }
	    });
	 
 }
      This is my grid  index.jsp <input type="button" value="点击2" id='testButton2'/><br>
    <table id="first2"></table>
    <div id="firstPage2"></div>
 */
 
 </script>
   
    
  </body>
</html>

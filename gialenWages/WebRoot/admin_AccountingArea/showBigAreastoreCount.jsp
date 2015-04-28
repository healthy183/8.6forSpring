<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showBigAreastoreCount.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
	*{ font-size:12px;
	   font-family:"宋体";
	   }
	
	#redId{
			background:transparent;
			border:0;
			color:red;
			width:100%;
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
	
	
	.myTableTr{
		background-color:#FFFFFF;/**/
		font-size:14px;
		color: black;
	}
	.myTableTd{
		line-height: 14px;height: 14px;
	}
	
	.myTableSpan{
		font-size: 12px;color: #666600;
	}	
	
	.myTableTh{
		background: url(image/wbg.gif);
		/*background-color: #EEF4EA;*/
	}
		
</style>
   <script language="javascript" type="text/javascript" 
	src="js/jquery-1.4.2.js"></script>
 <script type="text/javascript" charset="gb2312" src="js/mybgcolor.js">
 </script>

  </head>
  
  <body>

    <table  class="myTable" cellpadding="3" cellspacing="1">
    <tr>
    		<td colspan="8">
    			<b style="font-size: 16px">大区长提成金额表</b>
    			   <input type="button" value="返回" onclick="javascript:history.go(-1)"></input>
    		</td>
    	</tr>
    
    	<tr><th>序号</th>
    		<th>片区名称</th><th>445营运月</th><th>计划销售金额</th>
    		<th>实际销售金额</th><th>完成比例</th>
    		<th>大区长奖金</th><th>区域助理奖金</th>
    	</tr>
    	<c:forEach items="${requestScope.bigAreastoreCountList}"  var="storeCount" varStatus="s">
    	<tr  class="mybgcolor">
    		<td>${s.count}</td>
    		<td>${storeCount.orgstdStruct.unitname}</td>
    		<td>${storeCount.operatingMonth.operatingMonthName}</td>
    		<td>${storeCount.planMoneyCount}元</td>
    		<td>${storeCount.saleCount}元</td>
    		<td>${storeCount.percentStr}</td>
    		<td>${storeCount.oneStarManagergrundbonusMoney}元</td>
    		<td>${storeCount.positiveManagergrundbonusMoney}元</td>
    		
    	</tr>
    	</c:forEach>
    </table>
    <br>   <br>   <br>   <br>   <br>   <br>   <br>
    <table  class="myTable" cellpadding="3" cellspacing="1">
    	<tr>
    		<th colspan="17">夏季02期员工销售提成汇总表</th>
    	</tr>
    	<tr>
			<td>序号</td><td>门店编号</td><td>门店名称</td><td>营运月</td><td>hr员工编号</td><td>佳讯员工编号</td>
			<td>员工名称</td><td>职位</td><td>职位提成</td><td>单品销售金额</td><td>单品销售奖金</td>
			<td>品牌销售金额</td><td>品牌销售奖金</td><td>总销售金额</td><td>总销售奖金</td><td>片区</td><td>大区</td>
    	</tr>
    	<tr>
    		<td>1</td><td>02004</td><td>时代花园店</td><td>夏季02期</td><td>G000030</td><td>00030</td>
			<td>刘琴</td><td>一星级店长</td><td>164.3元</td><td>1775.8元</td><td>40.75元</td>
			<td>1775.8元</td><td>40.75元</td><td>3000.8元</td><td>100元</td><td>白云片区3</td><td>白云区</td>
    	</tr>
    	<tr>
    		<td>2</td><td>02005</td><td>时代花园店</td><td>夏季02期</td><td>G000040</td><td>00040</td>
			<td>潘少慧</td><td>店长</td><td>164.3元</td><td>918.11元</td><td>30.00元</td>
			<td>1255.5元</td><td>43.75元</td><td>3010.8元</td><td>150元</td><td>白云片区3</td><td>白云区</td>
    	</tr>
    	<tr>
    		<td>3</td><td>02006</td><td>时代花园店</td><td>夏季02期</td><td>G000040</td><td>00040</td>
			<td>陈明娟</td><td>副店长</td><td>124.0元</td><td>1533.3元</td><td>47.75元</td>
			<td>1775.8元</td><td>40.75元</td><td>3220.8元</td><td>105元</td><td>白云片区3</td><td>白云区</td>
    	</tr>
    	<tr>
    		<td>4</td><td>02007</td><td>时代花园店</td><td>夏季02期</td><td>G000040</td><td>00040</td>
			<td>任关莲</td><td>柜长</td><td>0元</td><td>1155.8元</td><td>40.4元</td>
			<td>1255.8元</td><td>40.45元</td><td>3050.4元</td><td>125元</td><td>白云片区3</td><td>白云区</td>
    	</tr>
    	<tr>
    		<td>5</td><td>02008</td><td>时代花园店</td><td>夏季02期</td><td>G000040</td><td>00040</td>
			<td>伍星</td><td>实习生</td><td>0元</td><td>1256.8元</td><td>35.75元</td>
			<td>1505.8元</td><td>45.75元</td><td>3550.8元</td><td>170元</td><td>白云片区3</td><td>白云区</td>
    	</tr>
    </table>
    <br>   <br>
    <table class="myTable" cellpadding="3" cellspacing="1">
    	<tr>
    		<th colspan="11">
    			夏季02期员工销售总提表
    		</th>
    	</tr>
	  	<tr>
	  		<td>序列</td><td>门店编号</td><td>门店名称</td><td>营运月</td>
	  		<td>实际销售总金额</td><td>计划销售总金额</td>
	  		<td>完成比例</td><td>员工总提(平均分)</td><td>所有员工提提成总额</td>
	  		<td>公共账号总提</td><td>门店总提</td>
	  	</tr>
	  	<tr>
	  		<td>1</td><td>02001</td><td>时代花园店</td><td>夏季02期</td>
	  		<td>118661.35元</td><td>142600.0元</td>
	  		<td>83.2%</td><td>1898.58元</td>
	  		<td>1255.08元</td><td>1044.54元</td><td>2299.62元</td>
	  	</tr>
	  	<tr>
	  		<td>2</td><td>02002</td><td>棠景店</td><td>夏季02期</td>
	  		<td>331846.17元</td><td>390900.0元</td>
	  		<td>87.2%</td><td>5309.54元</td>
	  		<td>1255.08元</td><td>1044.54元</td><td>2299.62元</td>
	  	</tr>
	  	<tr>
	  		<td>3</td><td>02003</td><td>万华花园店</td><td>夏季02期</td>
	  		<td>276352.8元</td><td>341900.0元</td>
	  		<td>97.5%</td><td>4873.45元</td>
	  		<td>1255.08元</td><td>1044.54元</td><td>2299.62元</td>
	  	</tr>
	  	<tr>
	  		<td>4</td><td>02004</td><td>江晓店</td><td>夏季02期</td>
	  		<td>216268.15元</td><td>262700.0元</td>
	  		<td>82.3%</td><td>3460.29元</td>
	  		<td>1255.08元</td><td>1044.54元</td><td>2299.62元</td>
	  	</tr>
    </table>
    
    
     <br>   <br>
    <table class="myTable" cellpadding="3" cellspacing="1">
    	<tr><th colspan="11">夏季02期区长销售提成汇总表</th></tr>
    	<tr>
    		<td>序号</td><td>负责区域</td><td>hr员工编号</td>
			<td>佳讯员工编号</td><td>员工名称</td><td>职位</td>
			<td>营运月</td><td>计划销售金额</td><td>实际销售金额</td>
			<td>完成比例</td><td>奖金</td>
    	</tr>
    	<tr>
    		<td>1</td><td>白云区</td>
    		<td>00001</td><td>G0001</td><td>A</td>
    		<td>大区长</td><td>夏季02期</td>
    		<td>6531800.0元  </td><td>5037594.27元 </td>
			<td>83.2%</td><td>1880.0元</td>
    	</tr>
    	<tr>
    		<td>2</td><td>白云区</td><td>00001</td><td>G0001</td><td>A1</td>
    		<td>区域助理</td><td>夏季02期</td>
    		<td>6531800.0元  </td><td>5037594.27元 </td>
			<td>83.2%</td><td>1128.0元</td>
    	</tr>
    	<tr>
    		<td>3</td><td>白云片区1</td><td>00001</td><td>G0001</td><td>A11</td>
    		<td>片区长</td><td>夏季02期</td>
    		<td>1204600.0元  </td><td>1078399.87元</td>
			<td>89.5%</td><td>1050.0元</td>
    	</tr>
    	<tr>
    		<td>4</td><td>白云片区2</td><td>00001</td><td>G0001</td><td>A12</td>
    		<td>片区长</td><td>夏季02期</td>
    		<td>793100.0元 </td><td>880933.67元</td>
			<td>111.1%</td><td>1870.0元</td>
    	</tr>
    </table>
    <br>
    
  </body>
</html>

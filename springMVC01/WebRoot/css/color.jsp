<%@ page language="java" contentType="text/css; charset=UTF-8"	pageEncoding="UTF-8"%>

<%
if("Y".equals(request.getParameter("print"))){
	pageContext.setAttribute("leftBgColor", "FFFFFF");
	pageContext.setAttribute("rightBgColor", "FFFFFF");
	pageContext.setAttribute("fontColor","000000");
	pageContext.setAttribute("editFontColor","000000");
	pageContext.setAttribute("menuBgColor","f9f1dc");
	pageContext.setAttribute("rightMenuBgColor","f9f1dc");
	pageContext.setAttribute("bottomPanelBgColor", "c9e2ff");
	pageContext.setAttribute("leftButtonPanelBgColor", "b4eeee");
	pageContext.setAttribute("rightButtonPanelBgColor", "e7d5d0");
	pageContext.setAttribute("screenTitleColor", "000000");
	pageContext.setAttribute("screenModeColor", "000000");
	pageContext.setAttribute("legendColor", "000000");
	pageContext.setAttribute("borderColor", "666666");
	pageContext.setAttribute("inputBorderColor", "bac0c8");
	pageContext.setAttribute("stateBorderColor","666666");
	pageContext.setAttribute("buttonColor","666666");
	pageContext.setAttribute("tableHeaderBgColor","FFFFFF");
}else{
	pageContext.setAttribute("leftBgColor", "dffcf8");
	
	pageContext.setAttribute("rightBgColor", "e4e4e4");
	pageContext.setAttribute("fontColor","8a4d48");
	pageContext.setAttribute("editFontColor","000000");
	
	pageContext.setAttribute("menuBgColor","f9f1dc");
	pageContext.setAttribute("rightMenuBgColor","f9f1dc");
	pageContext.setAttribute("bottomPanelBgColor", "c9e2ff");
	pageContext.setAttribute("leftButtonPanelBgColor", "b4eeee");
	pageContext.setAttribute("rightButtonPanelBgColor", "e7d5d0");
	
	pageContext.setAttribute("screenTitleColor", "dd7c35");
	pageContext.setAttribute("screenModeColor", "ee7c35");
	pageContext.setAttribute("legendColor", "16448b");
	pageContext.setAttribute("borderColor", "bac0c8");
	//pageContext.setAttribute("inputBorderColor", "b4d0e3");
	pageContext.setAttribute("inputBorderColor", "bac0c8");
	//pageContext.setAttribute("borderColor", "000000");
	//pageContext.setAttribute("stateBorderColor","f2832e");
	pageContext.setAttribute("stateBorderColor","bac0c8");
	pageContext.setAttribute("buttonColor","494949");
	pageContext.setAttribute("tableHeaderBgColor","FCC696");
	
}
%>

/**
*for-print = ${param.print}
*leftBgColor = ${leftBgColor}
*rightBgColor = ${rightBgColor}
*fontColor = ${fontColor}
*menuBgColor = ${menuBgColor}
*rightMenuBgColor = ${rightMenuBgColor}
*bottomPanelBgColor = ${bottomPanelBgColor}
*leftButtonPanelBgColor = ${leftButtonPanelBgColor}
*rightButtonPanelBgColor = ${rightButtonPanelBgColor}
*screenTitleColor = ${screenTitleColor}
*screenModeColor = ${screenModeColor}
**/
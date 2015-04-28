<%@ page language="java" contentType="text/css; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/css/color.jsp"%>

.ui-layout-west,.ui-layout-north, /* Banner ... */ .ui-layout-south,
	/*Footer*/ .east-west {
	padding: 0;
	background: #FFFFFF;
	overflow: hidden;
}

.east-north {
	padding: 0;
	background: #FFFFFF;
	overflow: hidden;
}

.ui-layout-east {
	padding: 0;
	background: #FFFFFF;
	overflow: auto;
}

/*Light-Green
.ui-layout-center,.center-north,.center-center,.center-up {
	padding: 0;
	background: #e9f9d9;
	overflow: auto;
}
.east-center {
	padding: 0;
	background: #ebe0fc;
	overflow: auto;
}
*/
.ui-layout-center,.center-north,.center-center,.center-up {
	padding: 0;
	background: #FFFFFF;
	overflow: auto;
}

.east-center {
	padding: 0;
	background: #f7f7f7;
	overflow: auto;
}
.east-west{
	padding: 0;
	background: #f7f7f7;
	overflow: auto;
}

#containerPanel_N fieldset {
	padding-left: 10px;
	background: #${leftBgColor};
	overflow: hidden;
	border: 1px solid #${borderColor};
}
#containerPanel_Y fieldset {
	padding-left: 10px;
	/*background: #fff9c5;*/
	background: #${rightBgColor};
	overflow: hidden;
	border: 1px solid #${borderColor};
}
#layout-west .ui-accordion-content{
	background: #${menuBgColor};
}
#layout-east-west .ui-accordion-content{
	background: #${rightMenuBgColor};;
}

#containerPanel_N {
	overflow-x: auto;
	overflow-y: auto;
	height: 100%;
	margin-left: 2px;
}
#containerPanel_Y {
	overflow-x: auto;
	overflow-y: auto;
	height: 100%;
	margin-left: 2px;
}






#buttonPanel_N {
	width: 100%;
	overflow: hidden;
	padding-bottom: 0px;
	background: #${leftButtonPanelBgColor};
}

#buttonPanel_Y {
	width: 100%;
	overflow: hidden;
	padding-bottom: 0px;
	background: #${rightButtonPanelBgColor};
}


/*Blue*/
.center-down {
	padding: 0;
	background: #c9e2ff;
	overflow: auto;
}

.buttonContainerGreen {
	background: #${leftButtonPanelBgColor};
	display: block;
	width: 100%;
	padding-bottom: 1px;
	padding-top: 1px;
	/* white-space: nowrap;  */
}

.buttonContainerGreen input {
	margin-right: 5px;
}

.buttonContainerBlue {
	background: #abd0fc;
	display: block;
	width: 100%;
	padding-bottom: 1px;
	padding-top: 1px;
}

.buttonContainerPurple {
	/*deep yellow*/
	background: #${rightButtonPanelBgColor};
	display: block;
	width: 100%;
	padding-bottom: 1px;
	padding-top: 1px;
}

.select_input_vertical_align,.select_input_vertical_align ul {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-align: center;
	float: left;
	line-height: 28px;
	height: 28px;
}

.select_input_vertical_align li {
	float: left;
}

.select_input_vertical_align li select,.select_input_vertical_align li input
	{
	vertical-align: middle;
}

.select_input_vertical_align li select {
	height: 22px;
	line-height: 22px;
	/*margin-left:5px; */
	margin-right: 5px;
}

.select_input_vertical_align li input {
	height: 18px;
	line-height: 18px;
}

/*jqgrid-row*/
.ui-jqgrid-row-highlight {
	border: 1px solid #FFFF00;
	background: #FFFF99;
	color: #644f4f;
}

.ui-jqgrid-state-disabled {
	display: none;
}

/*For pending Interview BOX*/
.pendingInterviewBox {
	width: 300px;
	padding: 5px 10px;
	border: 1px solid #ffecb0;
	font-size: 12px;
	background-color: #fffee0;
	-moz-box-shadow: 1px 1px 2px rgba(0, 0, 0, .2);
	-webkit-box-shadow: 1px 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 1px 1px 2px rgba(0, 0, 0, .2);
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	position: fixed;
	top: 62px;
	right: 380px
}

.pendingInterviewBox .close-ico {
	position: absolute;
	top: 5px;
	right: 5px;
	display: block;
	width: 16px;
	height: 16px;
	background-image: url(../images/close-ico.png);
	text-indent: -900px;
	overflow: hidden;
}

.pendingInterviewBox .close-ico:hover {
	background-position: 0 -16px;
}

.pendingInterviewBox p {
	line-height: 22px
}

.changecolor0 {
	color: red;
	text-decoration: underline;
}

.changecolor1 {
	color: green;
	text-decoration: underline;
}

.changecolor2 {
	color: #00f;
	text-decoration: underline;
}

.changecolor3 {
	color: #f0f;
	text-decoration: underline;
}

.changecolor4 {
	color: #0ff;
	text-decoration: underline;
}

.changecolor5 {
	color: #000;
	text-decoration: underline;
}

/**for receipt verification */
.filedName200 {
	width: 150px;
	float: left;
}

.clear {
	clear: both;
}



.giStyle .oneShortCol{
	
}

.giStyle .oneShortCol textarea{
	width: 500px;
}

.giStyle .oneShortCol select{
	width: 500px;
}

.giStyle .oneShortCol h3{
	width: 400px;
}
.giStyle .oneShortCol span{
	width: 400px;
}

.giStyle .oneShortCol input[type=text]{
	width: 500px;
}
#containerPanel_N h3{
	display: block;
	float: left;
	padding-top: 5px;
	padding-bottom: 5px;
}
#containerPanel_Y h3{
	display: block;
	float: left;
	padding-top: 5px;
	padding-bottom: 5px;
}

.giStyle h3{
	display: block;
	width: 240px;
	float: left;
	padding-top: 5px;
	padding-bottom: 5px;
}

.giStyle h4{
	display: block;
	width:125px;
	float: left;
	padding-top: 5px;
}
.giStyle span{
}
.giStyle textarea{
}


.ui-layout-south {
	padding: 0;
	background: #${bottomPanelBgColor};
	overflow: auto;
}

#ui-layout-center {
	padding: 5px;
}

/* ------applicatTable-----------*/
.applicatTable {
	margin-top: 10px;
}

.applicatTable TD {
	width: 140px;
	padding: 0;
	margin: 0;
}

.applicatTable TD {
	height: 41px;
}

.applicatTable input {
	margin: 0;
}

.labelInput input {
	width: 83px;
}

.labelInput input.hkidLable {
	width: 188px;
}

.applicatTable span {
	width: 8px;
	margin-left: 8px;
}

.applicatTable TD.labelTitle {
	width: 300px;
	padding: 0;
}

.cPopText {
	background-color: #F8F8F5;
	border: 1px #000000 solid;
	padding-right: 4px;
	padding-left: 4px;
	height: 20px;
	padding-top: 2px;
	padding-bottom: 2px;
	display: none;
	font-weight: 200;
}
.headerInfo{	
	width:1594px;
}

.screenTitle {
	font-weight:bold;
	font-size: 24pt;
	font-family: Helvetica, Arial;
	color: #${screenTitleColor};
}
.screenMode{
	font-weight:bold;
	font-family: Helvetica, Arial;
	font-size: 24pt;
	color: #${screenModeColor};
}
#loginmsg {
	font-weight:bold;
	font-family: Helvetica, Arial;
	font-size: 24pt;
	color: #${screenTitleColor};
	
}



/* -- transform uppercase Styles ------------------------------- */
.transform{
  text-transform: uppercase;
}

input .readOnly{
	color: #${fontColor};
}
input .edit1{
	color: #${editFontColor};
}


.datePickerStyle{
	
}

.editable { 
	background-color: #fff;
	/* color: #000; */
}
.edit1 { 
	background-color: #FFFFFF;
	border: 2px solid #${inputBorderColor};
	color: #${editFontColor};
}
.readOnly { 
	background-color: transparent;
	border: 2px solid transparent;
}
.readOnlyUpCase { 	
	border: 2px solid transparent;
	background-color: transparent;
	text-transform: uppercase;
}

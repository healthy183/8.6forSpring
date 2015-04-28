<%@ page language="java" contentType="text/css; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/css/color.jsp"%>

@charset "utf-8";
/* CSS Document */

select {
    -moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
    border: 2px solid #${inputBorderColor};
    vertical-align: middle;
    background-color: White;
	padding: 0px;
	font-weight: normal;
	margin-top: 3px;
	margin-bottom: 3px;
	font-family:inherit;
	width: 200px;
	font-family: inherit;	
}


select.expand {
    width: auto;
}

select.fixed-width:focus{
    width: auto !important;
    position: relative;
}

.styled-button-5 {
	background-color: #ed8223;
	color: white;
	font-size: 18px;
	line-height: 30px;
	border-radius: 20px;
	border: 0px;
	text-shadow: #C17C3A 0px -1px 0px;
	width: 120px;
	height: 32px;
}

input{
	background-color:White;	
	border: 2px solid transparent;
	margin-top: 3px;
	margin-bottom: 3px;
}


input[type="text"]
{
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	font-family:inherit;
	display:inline;
	margin-top: 3px;
	margin-bottom: 3px;
	padding: 0px;
	border: 2px solid #${inputBorderColor};
	/*border: 2px solid transparent;*/
	font-weight: normal;
}

/* -- readonly Styles for input/select -------------------------- */
input[readonly="readonly"]{
	background-color: transparent; 
	border: 2px solid transparent;
	border-left-width: 0px;
	color: #${fontColor};
	padding: 0px;
	margin-top: 3px;
	margin-bottom: 3px;
	font-weight: normal;
}


select[readonly="readonly"]{
	background-color: transparent;
	border: 2px solid transparent; 
	border-left-width: 0px;
	color: #${fontColor};
	padding: 0px;
	margin-top: 3px;
	margin-bottom: 3px;
	font-weight: normal;
}
select[disabled="disabled"]{
	background-color: transparent;
	border: 2px solid transparent; 
	border-left-width: 0px;
	color: #${fontColor};
	-webkit-appearance: none;
	-moz-appearance:none;
	padding: 0px;
	margin-top: 3px;
	margin-bottom: 3px;
	font-weight: normal;
}
input[disabled="disabled"][value="----Please Select----"]{
	display:none;
}
input[readonly="readonly"][value="----Please Select----"]{
	display:none;
}
select[disabled="disabled"][value="----Please Select----"]{
	display:none;
}
select[readonly="readonly"][value="----Please Select----"]{
	display:none;
}

textarea[readonly="readonly"]{
	background-color: transparent; 
	border: 2px solid #${inputBorderColor};
	color: #${fontColor};
}
textarea[readonly="readonly"][rows="1"]{
	background-color: transparent;
	border: 2px solid transparent;
	color: #${fontColor};
}

input[type="text1"]
{
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	font-family:inherit;
	display:inline;
	width: 100px;
	margin-bottom:10px;
	border: 2px solid #${inputBorderColor};
	background-color:White;
	margin-left: 8px;
	font-weight: normal;
}

input[type="text2"]
{
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	font-family:inherit;
	display:inline;
	width: 330px;
	margin-bottom:10px;
	border: 2px solid #${inputBorderColor};
	background-color:White;
	margin-left: 8px;
	font-weight: normal;
}

input[type="text3"]
{
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	font-family:inherit;
	display:inline;
	width: 250px;
	margin-bottom:10px;
	border: 2px solid #${inputBorderColor};
	background-color:White;
	margin-left: 8px;
	font-weight: normal;
	
}

input[type="password"]
{
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	font-family:inherit;
	display:inline;
	width: 250px;
	margin-bottom:10px;
	border: 2px solid #${inputBorderColor};
	background-color:White;
	margin-left: 8px;
}

input[type="radio"]
{
	margin-right: 5px;
	height: 20px;width: 20px;
	background-color: transparent; 
	margin-top: 0px;
	margin-bottom: 0px;
}

input[type="checkbox"]
{
	margin-right: 5px;
	background-color: transparent; 
	height: 16px;
	width: 16px;
}

/**********************/
.radiobuttons input[type="radio"][disabled="disabled"] {
    display:none;
    color: #${fontColor};
}
.radiobuttons input[type="radio"][disabled="disabled"]+ label {
    display:none;
    color: #${fontColor};
}

.radiobuttons input[type="radio"][disabled="disabled"][checked] + label {
    display:inline;
    color: #${fontColor};
}
.radiobuttons label {
	color: #3E3E3E;
 
}

.radiobuttons{
	display:inline-block;
	color: #3E3E3E;
	float: left;
	margin-top: 5px;
	margin-bottom: 5px;
}

.checkboxes{
	display:inline-block;
	margin-top: 5px;
	margin-bottom: 5px;
	vertical-align: middle;
}

/*
input[type="radio"]:checked + label:before {
	content: ' ';
	display:inline-block;
	width: 25px;
    height: 25px;
	border: 2px solid black;
    background: url('../../images/check.gif') no-repeat;    
}





*/


label {
	font-family: inherit;
	font-weight: normal;
}





/*
input[type="text1"]
{
	display:inline;
	width: 100px;
	margin-bottom:10px;
	border-style:solid;
	border-width:thin;
	border-color: #${inputBorderColor};
	background-color:White;
	margin-left: 8px;
}

*/

textarea {
	height:100px;
	resize: none;
	-moz-border-radius: 4px;
    -moz-box-shadow: 1px 1px 5px #cfcfcf inset;
	border: 2px solid #${inputBorderColor};
	font-weight: normal;
	margin-top: 3px;
	margin-bottom: 3px;
	
}


/* form style
---------------------------------------------*/
fieldset
{
	border: 1px solid #${borderColor};
	width:1582px;
	overflow: auto;
	margin-bottom: 8px;
	height:auto !important;
	background: #${leftBgColor};
}

#containerPanel_N .tableContainer
{
	/* #bac0c8;padding: 5px;*/
	width: 1594px;
	background-color: #${leftBgColor};
	overflow: hiddeb;
	margin-bottom: 8px;
	height:auto !important;
	border: 0px solid transparent;
}
#containerPanel_Y .tableContainer{
	/* #bac0c8;padding: 5px;*/
	width: 1594px;
	background-color: #${rightBgColor};
	overflow: hiddeb;
	margin-bottom: 8px;
	height:auto !important;
	border: 0px solid transparent;
}

fieldset TR{
	white-space: nowrap;
}

legend{
	color: #${legendColor};
	font-weight: bold;
	font-size: 110%;
} 

.ui-jqgrid-title{
	color: #${legendColor};
	font-weight: bold;
	font-size: 110%;
}
.sectiontitle {
	font-weight: bold;
	color: #16448b;
	
	border-bottom:0px;
	margin-top: 15px;
	padding-left: 10px;
	width: 300px;
	/*background: #e9f9d9;
	border: 1px solid #bac0c8;
	display:block;*/
	
}

.inputForm01 table {
	width: 100%;
	\_width: 730px; /* for WinIE5 */
	border-bottom: 1px solid #CCC;
}
.inputForm01 th,
.inputForm01 td {
	padding: 5px 0;
	border-top: 1px solid #CCC;
}
.inputForm01 th {
	width: 180px;
	vertical-align: middle;
}
.inputForm01 .input,
.inputForm01 .select,
.inputForm01 textarea {
	width: 98%;
	padding: 2px 1px;
	border: 1px solid #${inputBorderColor};;
	
	font-family: inherit;
	/**/
}

.inputForm01 select name {
	border: 1px solid #${inputBorderColor};;
	
	font-family: inherit;
	/**/
}

.inputForm01 textarea {
	height: 200px;
}




.inputForm01 .submit {
	margin: 10px 0 0 0;
	text-align: center;
}
.inputForm01 .submit input {
	padding: 2px 10px;
}



.btn1_old {
  color: #fff;
  background: #f2832e;
  font-weight: bold;
  border: 1px solid #cc660b;
   cursor: pointer;
  cursor: hand;
  font-size: 13px;
	line-height: 20px;
	border-radius: 14px;
	padding-left: 4px;
	padding-right: 4px;
}
.btn1 {
	color: #${buttonColor};
  	background: #fcc696;
  	font-weight: normal;
  	border: 1px solid #fcc696;
   	cursor: pointer;
 	cursor: hand;
  	font-size: 16px;
  	margin-top: 3px;
  	margin-bottom: 3px;
	line-height: 1.6em;
	border-radius: 1em;
	padding-left: 0.5em;
	padding-right: 0.5em;
	padding-top: 0.3em;
	padding-bottom: 0.3em;
	
	font-family:inherit;
}


.btn1:hover {
  color: #fff;
  background: #f2832e;
}

/* rounded buttons */
.round {
	cursor:pointer; 
	cursor:hand; 
	line-height:45px;
	height:45px;
	width:250px;
	background:url(../../images/button.gif) no-repeat right top; 
	padding-right:30px; 
	display:inline-block;
	Font-weight: bold;
	margin-top: 8px;
}

.round span { 
	background:url(../../images/button.gif) no-repeat left top; 
	height:45px;
	line-height:45px;
	display:inline-block;
	padding-left:20px;
}

	a.round:hover {background-position:right -138px;}
	a.round:hover span {background-position:left -138px;}

/* end rounded buttons */



a.ovalbutton{
background:url(../../images/button-left.png) no-repeat top left;

float: left;
font: bold 12px Arial, Helvetica, sans-serif, æ°ç´°æé«; /* Change 13px as desired */
color: #ffffff;
line-height: 16px; /* This value + 4px + 4px (top and bottom padding of SPAN) must equal height of button background (default is 24px) */
height: 24px; /* Height of button background height 

*/

padding-left: 11px; /* Width of left menu image */
text-decoration: none;
}

a.ovalbutton span{
background: transparent url(../../images/button-right.png) no-repeat top right;
display: block;
padding: 4px 11px 4px 0; /*Set 11px below to match value of 'padding-left' value above*/
}

a.ovalbutton:hover{ /* Hover state CSS */
background-position: bottom left;
}

a.ovalbutton:hover span{ /* Hover state CSS */
background-position: bottom right;
color: fff;
}

a.ovalbutton:active{ /* Hover state CSS */
background-position: bottom left;

}

a.ovalbutton:active span{ /* Hover state CSS */
background-position: bottom right;
color: #881313;
}

.CKI{
background-color: #F9F1DC;
}


/*-----------oldClass------------*/

.caseinfo input[type="text"]
{
	width: 220px;
}
.caseinfo TD
{
	width: 100px;
}

.oldClass select {
	width: 410px;
}

.oldClass input[type="text"]
{
	width: 400px;
}

.oldClass textarea {
	width: 410px;
}

.oldClass fieldset TD{
	width: 300px;
}



/*clean*/
.clean table
{
}

.clean TD{
	width: auto;
}
.clean TR{
}

.clean input[type="text"]{
	width: auto;
}

.clean select {
	width: auto;
}

.clean select {
	width: auto;
}

.clean textarea {
	width: auto;
}


	var jq = jQuery.noConflict();
 	jq(function(){
 		//alert("abcd");
 		
 		jq(".mybgcolor").live("mouseover",function(){
 			
 			jq(this).css("background-color","#FCFDEE");	
		 	jq(this).children().children("input[type='text']").css("background-color","#FCFDEE");
 		});
 		jq(".mybgcolor").live("mouseout",function(){
 			jq(this).css("background-color","#FFFFFF");	
		 	jq(this).children().children("input[type='text']").css("background-color","#FFFFFF");
 		});
 	});
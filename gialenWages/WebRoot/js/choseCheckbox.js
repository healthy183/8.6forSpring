var jq = jQuery.noConflict();
	jq(function(){
		jq("#allCheckbox").click(function(){
			
			if(jq(this).attr("checked") == true){
				jq(".proIdClass").attr("checked",true);
			}else{
				jq(".proIdClass").attr("checked",false);
			}
			
			jq("#mustbeCheck").attr("checked",true);
			
		});
	});
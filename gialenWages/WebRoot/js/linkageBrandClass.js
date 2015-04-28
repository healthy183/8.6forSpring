var jq = jQuery.noConflict();
	jq(function(){
		jq(".brandLevel").each(function(){
			jq(this).change(function(){
				var nextId = jq(this).next().attr("id");
				dwrAddBrand.getbrandByLevel(jq(this).val(),function(data){
					dwr.util.removeAllOptions(nextId);
					dwr.util.addOptions(nextId,{"0":''});
					dwr.util.addOptions(nextId,data);
				});
			});
		});
		
		jq(".classLevel").each(function(){
			jq(this).change(function(){
				var nextId = jq(this).next().attr("id");
				dwrAddBrand.getClassByLevel(jq(this).val(),function(date){
					dwr.util.removeAllOptions(nextId);
					dwr.util.addOptions(nextId,{"0":''});
					dwr.util.addOptions(nextId,date);
				});
			});
		});
});
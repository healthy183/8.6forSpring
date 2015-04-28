	var jq = jQuery.noConflict();
	jq(function(){
		jq("#bigClass").change(function(){
			var bigClassVar = jq(this).val();
			var level2 = "2";
			var level3 = "3";
			if(bigClassVar !="0"){
				dwrAddBrand.findClassByParentClass(bigClassVar,level2,function(data){
					dwr.util.removeAllOptions("middleClass");
					dwr.util.addOptions("middleClass",{"0":''});
					dwr.util.addOptions("middleClass",data);
				});
			}else{
				dwrAddBrand.getClassByLevel(level2,function(data){
					dwr.util.removeAllOptions("middleClass");
					dwr.util.addOptions("middleClass",{"0":''});
					dwr.util.addOptions("middleClass",data);
				});
			}
			dwrAddBrand.getClassByLevel(level3,function(data){
				dwr.util.removeAllOptions("smallClass");
				dwr.util.addOptions("smallClass",{"0":''});
				dwr.util.addOptions("smallClass",data);
			});
		});
		
		jq("#middleClass").change(function(){
				var middleClassIdVar = jq(this).val();
				var level3 = "3";
				if(middleClassIdVar != 0){
					dwrAddBrand.findClassByParentClass(middleClassIdVar,level3,function(data){
						dwr.util.removeAllOptions("smallClass");
						dwr.util.addOptions("smallClass",{"0":''});
						dwr.util.addOptions("smallClass",data);
					});
				}else{
					dwrAddBrand.getClassByLevel(level3,function(data){
						dwr.util.removeAllOptions("smallClass");
						dwr.util.addOptions("smallClass",{"0":''});
						dwr.util.addOptions("smallClass",data);
					});
				}
		});
	});
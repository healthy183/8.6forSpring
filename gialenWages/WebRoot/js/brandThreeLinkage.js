var jq = jQuery.noConflict();
jq(function(){
		jq("#bigBrand").change(function(){
			var bigBrandIdVar = jq(this).val();
			var level2 = "2";
			var level3 = "3";
			if(bigBrandIdVar != "0"){
				dwrAddBrand.findBrandByParentBrand(bigBrandIdVar,level2,function(data){
					dwr.util.removeAllOptions("middleBrand");
					dwr.util.addOptions("middleBrand",{"0":''});
					dwr.util.addOptions("middleBrand",data);
				});
			}else{ //为中品牌下拉框赋值
				dwrAddBrand.getbrandByLevel(level2,function(data){
					dwr.util.removeAllOptions("middleBrand");
					dwr.util.addOptions("middleBrand",{"0":''});
					dwr.util.addOptions("middleBrand",data);
				});
			} //为小品牌下拉框赋值
			dwrAddBrand.getbrandByLevel(level3,function(data){
				dwr.util.removeAllOptions("smallBrand");
				dwr.util.addOptions("smallBrand",{"0":''});
				dwr.util.addOptions("smallBrand",data);
			});
		});
		
		jq("#middleBrand").change(function(){
				var middelBrandIdVar = jq(this).val();	
				var level3 = "3";
				if(middelBrandIdVar != "0"){
					dwrAddBrand.findBrandByParentBrand(middelBrandIdVar,level3,function(data){
						dwr.util.removeAllOptions("smallBrand");
						dwr.util.addOptions("smallBrand",{"0":''});
						dwr.util.addOptions("smallBrand",data);
					});
				}else{
					dwrAddBrand.getbrandByLevel(level3,function(data){
						dwr.util.removeAllOptions("smallBrand");
						dwr.util.addOptions("smallBrand",{"0":''});
						dwr.util.addOptions("smallBrand",data);
					});
				}
		});
});
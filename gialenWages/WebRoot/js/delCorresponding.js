 var jq = jQuery.noConflict();
  	jq(function(){
  		
  		jq(".delButton").live("click",function(){
  				
  				var answer = confirm("你确认要删除本行数据?");
  				if(answer){
  					var firstTd = jq(this).parent().parent().children("td:first");
  					
  					var correspondingIdVar = firstTd.children().val();//当前tr中的主键id值 
  					
  					if(typeof correspondingIdVar != "undefined"){
						//alert("二次hiddenCorrespondingIdVar是"+hiddenCorrespondingIdVar);
						 if(correspondingIdVar != ""){
							 dwrSynchronizationStores.delCorresponding(correspondingIdVar); 
						 }
					}
  					//dwrSynchronizationStores.delCorresponding(correspondingIdVar);
  					jq(this).parent().parent().remove();
  				}else{
  					return false;
  					}
  			});			
  		});

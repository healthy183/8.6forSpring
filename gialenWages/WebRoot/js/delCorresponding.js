 var jq = jQuery.noConflict();
  	jq(function(){
  		
  		jq(".delButton").live("click",function(){
  				
  				var answer = confirm("��ȷ��Ҫɾ����������?");
  				if(answer){
  					var firstTd = jq(this).parent().parent().children("td:first");
  					
  					var correspondingIdVar = firstTd.children().val();//��ǰtr�е�����idֵ 
  					
  					if(typeof correspondingIdVar != "undefined"){
						//alert("����hiddenCorrespondingIdVar��"+hiddenCorrespondingIdVar);
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

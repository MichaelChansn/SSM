
function reportComment(id)
{
	if(id==null)
		return;
	$.ajax({   
	     url:'../reportComment',   
	     type:'post',   
	     data:'reportComment='+id,   
	     async:true, //默认为true 异步   
	     dataType:'html',
	     error:function(){ 
	    	 alert("举报失败，稍后再试");
	     },   
	     success:function(data){  
	    	 if(data.trim().length>0)
	    		 {
			        $("#comment-"+id).html(data);   
	    		 }
	        
	     }
	 });
}



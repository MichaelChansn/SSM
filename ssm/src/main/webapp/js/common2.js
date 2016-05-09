
function reportComment(id)
{
	if(id==null)
		return;
	$.ajax({   
	     url:'reportComment',   
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

function addComment(id)
{
	if(id==null) return;
	$.ajax({   
	     url:'addComment',   
	     type:'post',   
	     data:$('#'+id+"-form").serialize(),   
	     async:true, //默认为true 异步   
	     dataType:'html',
	     error:function(){ 
	    	 alert("评论失败！！");
	     },   
	     success:function(data){
	    	 //alert(data);
	        $("."+id+"-input-block").before(data); 
	        $("#"+id+"-textarea").val(""); 
	        
	        $("#"+id+"-checkbox").attr('checked', false);
	        
	     }
	 });
	
}
function articleComment(id) {
	if(id==null) return;
	//alert($("div."+id+"comment").html());
	if(!$("div."+id+"comment .input-block").html())
		{
		
	$.ajax({   
		     url:'articleComment',   
		     type:'post',   
		     data:'articleComment='+id,   
		     async:true, //默认为true 异步   
		     dataType:'html',
		     error:function(){ 
		    	 alert("获取评论失败！");
		     },   
		     success:function(data){   
		        $("div."+id+"comment").html(data);   
		        $("div."+id+"comment").slideDown(300);
		     }
		 });
		}
	else
		{
		$("div."+id+"comment").slideToggle(300);
		}

}  

function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', '../code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
} 

function upArticle(id) {
	if(id!=null)
	$.ajax({   
		     url:'user/upArticle',   
		     type:'post',   
		     data:'upArticle='+id,   
		     async:true, //默认为true 异步   
		     dataType:'html',
		     error:function(){ 
		    	 alert("点赞失败");
		     },   
		     success:function(data){  
		    	 if(data.trim().length>0)
		    		 {
				        $("span."+id+"upArticleNum").html(data);   
		    		 }
		    	 $("a."+id+"btn").addClass("disabled");
		        
		     }
		 });
}  
function reportComment(id)
{
	if(id==null)
		return;
	$.ajax({   
	     url:'user/reportComment',   
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
	     url:'user/addComment',   
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
		     url:'user/articleComment',   
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

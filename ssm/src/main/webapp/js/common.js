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
		     dataType:'json',
		     error:function(){ 
		    	 alert("点赞失败");
		     },   
		     success:function(data){   
		        $("span."+id+"upArticleNum").html(data);   
		        $("a."+id+"btn").addClass("disabled");   
		        
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
		        $("div."+id+"comment").slideDown(500);
		     }
		 });
		}
	else
		{
		$("div."+id+"comment").slideToggle(500);
		}

}  
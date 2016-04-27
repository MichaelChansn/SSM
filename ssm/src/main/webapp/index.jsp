<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>VerifyCode</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        var path="<%=path%>";
        $( document ).ready(function() {
            $("#codeImg").on("click",function(){
                $('#codeImg').prop('src',path+'/jcaptcha.jpg?nocache='+new Date().getTime());
            });
        });
    </script>
  </head>
<body style="text-align: center;">
<h2>Hello World!</h2>
<script type="text/javascript"> 
$(function(){  //生成验证码         
    $('#kaptchaImage').click(function () {  
    $(this).hide().attr('src', '/ssm/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
});   
 
window.onbeforeunload = function(){  
    //关闭窗口时自动退出  
    if(event.clientX>360&&event.clientY<0||event.altKey){     
        alert(parent.document.location);  
    }  
};  
             
function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', '/ssm/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}  
</script> 
 
<div class="form-group">  
   <label>验证码 </label> 
   <input name="j_code" type="text" id="kaptcha" maxlength="4" class="form-control" />
   <br/> 
   <img src="/ssm/code/captcha-image" id="kaptchaImage"  style="margin-bottom: -3px"/>       
   <a href="#" onclick="changeCode()">看不清?换一张</a>  
</div>

</body>
</html>
function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', '../code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}  
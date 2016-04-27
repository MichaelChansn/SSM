/**
 * Created by cheng on 2016/4/27.
 */
function changeCode() {  //刷新
    $('#kaptchaImage').hide().attr('src', 'http://localhost:8080/ssm/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
    event.cancelBubble=true;
}
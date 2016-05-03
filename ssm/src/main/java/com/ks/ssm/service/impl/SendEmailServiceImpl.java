package com.ks.ssm.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ks.ssm.service.SendEmailService;

@Service("sendEmailService")
public class SendEmailServiceImpl implements SendEmailService {

	@Resource
	private JavaMailSender mailSender;//spring配置中定义  

	@Resource
    private VelocityEngine velocityEngine;//spring配置中定义  
	
	@Resource
    private SimpleMailMessage simpleMailMessage;//spring配置中定义  
	
	@Resource
	private TaskExecutor taskExecutor;//spring配置中定义  
	
	
	
	
    private String to;  
    private String subject;  
    private String templateName;  


	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Override
	public void sendWithTemplate(Map model) {
		// TODO Auto-generated method stub
		System.err.println(simpleMailMessage==null);
        simpleMailMessage.setTo(this.getTo()); //接收人    
        simpleMailMessage.setFrom(simpleMailMessage.getFrom()); //发送人,从配置文件中取得  
        simpleMailMessage.setSubject(this.getSubject());  
        String result = null;  
        try {  
            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, this.getTemplateName(), "UTF-8",model);  
        } catch (Exception e) {}  
        simpleMailMessage.setText(result);  
        //mailSender.send(simpleMailMessage);
        /*暂时不用异步，不利于成功统计*/
        this.taskExecutor.execute(new SendMailThread(mailSender,simpleMailMessage));

	}
	
//  内部线程类，利用线程池异步发邮件。
  private class SendMailThread implements Runnable {
      private SimpleMailMessage simpleMailMessageInThread;
      private JavaMailSender mailSenderInThread;
      private SendMailThread(JavaMailSender mailSender,SimpleMailMessage simpleMailMessage) {
          this.simpleMailMessageInThread=simpleMailMessage;
          this.mailSenderInThread=mailSender;
      }
      @Override
      public void run() {
    	  mailSenderInThread.send(simpleMailMessageInThread);
      }
  }
}

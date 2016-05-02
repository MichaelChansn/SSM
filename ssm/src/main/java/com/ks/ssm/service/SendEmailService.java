package com.ks.ssm.service;

import java.util.Map;

public interface SendEmailService {
	/** 
     * 发送模板邮件 
     * 
     */  
    public void sendWithTemplate(Map model);
    public void setTo(String to);
    public void setSubject(String subject);
    public void setTemplateName(String templateName);

}

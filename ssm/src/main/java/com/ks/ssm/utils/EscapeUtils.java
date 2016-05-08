package com.ks.ssm.utils;

import java.util.List;

import com.ks.ssm.domain.Admin;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;

/**数据库的SQL注入可以通过mybatis自动防止，但是html和script必须自己进行过滤*/
public class EscapeUtils {
	
	
	public static User EscapeUser(User user)
	{
		if(user!=null)
		{
			user.setCity(escapeString(user.getCity()));
			user.setEmail(escapeString(user.getEmail()));
			user.setJob(escapeString(user.getJob()));
			user.setMood(escapeString(user.getMood()));
			user.setPhone(escapeString(user.getPhone()));
			user.setUsername(escapeString(user.getUsername()));
			user.setQq(escapeString(user.getQq()));
		}
		return user;
	}
	
	public static Admin EscapeAdmin(Admin user)
	{
		if(user!=null)
		{
			user.setCity(escapeString(user.getCity()));
			user.setEmail(escapeString(user.getEmail()));
			user.setJob(escapeString(user.getJob()));
			user.setMood(escapeString(user.getMood()));
			user.setPhone(escapeString(user.getPhone()));
			user.setAdminname(escapeString(user.getAdminname()));
			user.setQq(escapeString(user.getQq()));
			user.setRealname(escapeString(user.getRealname()));
		}
		return user;
	}
	
	public static Article EscapeArticle(Article article)
	{
		if(article!=null)
		{
			article.setContent(escapeString(article.getContent()));
		}
		return article;
	}
	
	public static List<Article> EscapeArticle(List<Article> artilceList)
	{
		if(artilceList!=null && artilceList.size()>0)
		{
			for(Article artilce : artilceList)
			{
				EscapeArticle(artilce);
			}
		}
		return artilceList;
	}
	
	public static Comment EscapeComment(Comment comment)
	{
		if(comment!=null)
		{
			comment.setContent(escapeString(comment.getContent()));
		}
		return comment;
	}
	
	public static String escapeString(String str)
	{
		if(CommonUtils.isBlank(str))
			return null;
		String retString=str;
		retString=retString.replaceAll(">", "&gt;").replaceAll( "<", "&lt;").replaceAll("\r\n", "<br>");
		return retString;
		
	}

}

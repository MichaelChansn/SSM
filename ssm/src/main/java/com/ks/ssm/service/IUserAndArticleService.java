package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.UserAndArticle;

public interface IUserAndArticleService {
	
	public List<UserAndArticle> getUserAndArticleByArticle(List<Article> articleList);

}

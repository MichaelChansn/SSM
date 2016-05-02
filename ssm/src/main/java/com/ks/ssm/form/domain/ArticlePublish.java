package com.ks.ssm.form.domain;

import org.hibernate.validator.constraints.Length;

public class ArticlePublish {
	
	@Length(min=15,message="至少十五个字。。。")
	private String articleContent;
	
	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public boolean isArticleAnonymous() {
		return articleAnonymous;
	}

	public void setArticleAnonymous(boolean articleAnonymous) {
		this.articleAnonymous = articleAnonymous;
	}

	private boolean articleAnonymous;

}

package com.ks.ssm.domain;

public class UserAndArticle {

	private User user;
	
	private Article article;
	
	private int commentNum;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNums(int commentNum) {
		this.commentNum = commentNum;
	}
	
}

package com.ks.ssm.form.domain;

import com.ks.ssm.utils.EscapeUtils;

public class ArticleComment {
	
	//@NotBlank(message="文章ID不正确，非法请求！")
	private long articleId;
	
	private boolean commentAnonymous=false;
	
	//@NotBlank(message="评论内容不能为空啊！")
	private String commentContent;
	
	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public boolean isCommentAnonymous() {
		return commentAnonymous;
	}

	public void setCommentAnonymous(boolean commentAnonymous) {
		this.commentAnonymous = commentAnonymous;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = EscapeUtils.escapeString(commentContent);
	}

	

}

package com.ks.ssm.domain;

public class ArticleIdAndStatus {
	
	private long articleid;
	
	private boolean status;

	public ArticleIdAndStatus(long srticleid,boolean status)
	{
		this.setArticleid(srticleid);
		this.setStatus(status);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getArticleid() {
		return articleid;
	}

	public void setArticleid(long articleid) {
		this.articleid = articleid;
	}
	
	

}

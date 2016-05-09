package com.ks.ssm.domain;

public class UserIdAndPageQuery extends PageQuery{
	
	private Long userId;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public UserIdAndPageQuery(long userId,int pageSize, int pageNum)
	{
		super(pageSize, pageNum);
		this.userId=userId;
		
	}
}

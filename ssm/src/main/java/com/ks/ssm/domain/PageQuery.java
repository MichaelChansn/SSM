package com.ks.ssm.domain;

import com.ks.ssm.constant.CommonConstants;

public class PageQuery {
	
	public PageQuery(int pageSize, int pageNum) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.offset=(this.pageNum-1)*this.pageSize;
	}

	public PageQuery() {
		super();
		this.pageSize = CommonConstants.PAGE_SIZE;
		this.pageNum = 1;
		this.offset=(this.pageNum-1)*this.pageSize;
	}
	private int pageSize;
	
	private int pageNum;
	
	private int offset;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}

package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.ArticleAndCommentNum;
import com.ks.ssm.domain.UserIdAndPageQuery;

public interface IArticleAndCommentNumService {
	List<ArticleAndCommentNum> getArticleWithCommentNumByPage(UserIdAndPageQuery userIdAndPageQuery);

}

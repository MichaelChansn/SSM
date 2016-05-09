package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.CommentAndArticle;
import com.ks.ssm.domain.UserIdAndPageQuery;

public interface ICommentAndArticleService {
	
	List<CommentAndArticle> getCommentAndArticleWithOutBlobsByPageWithUserId(UserIdAndPageQuery userIdAndPageQuery);
	
	List<CommentAndArticle> getCommentAndArticleWithBlobsByPageWithUserId(UserIdAndPageQuery userIdAndPageQuery);

}

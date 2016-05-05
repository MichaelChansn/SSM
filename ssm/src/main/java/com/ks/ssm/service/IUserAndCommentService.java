package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.UserAndArticle;
import com.ks.ssm.domain.UserAndComment;

public interface IUserAndCommentService {
	
	public List<UserAndComment>getUserAndCommentByComment(List<Comment> commentList);

}

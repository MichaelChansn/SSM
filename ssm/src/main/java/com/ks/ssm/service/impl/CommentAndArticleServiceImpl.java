package com.ks.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.CommentAndArticle;
import com.ks.ssm.domain.UserIdAndPageQuery;
import com.ks.ssm.service.ICommentAndArticleService;

@Service("commentAndArticleService")
public class CommentAndArticleServiceImpl implements ICommentAndArticleService {

	@Resource
	private CommentMapper commentDao;
	
	@Resource
	private ArticleMapper articleDao;
	
	@Override
	public List<CommentAndArticle> getCommentAndArticleWithOutBlobsByPageWithUserId(
			UserIdAndPageQuery userIdAndPageQuery) {
		// TODO Auto-generated method stub
		List<CommentAndArticle> commentAndArticleList=new ArrayList<CommentAndArticle>();
		List<Comment> commentList=commentDao.selectByPageWithUserId(userIdAndPageQuery);//所有的状态都查出来了
		for(Comment comment : commentList)
		{
			Article article=articleDao.selectByPrimaryKeyWithoutBLOBs(comment.getArticleid());
			CommentAndArticle commentAndArticle=new CommentAndArticle();
			commentAndArticle.setArticle(article);
			commentAndArticle.setComment(comment);
			commentAndArticleList.add(commentAndArticle);
		}
		return commentAndArticleList;
	}

	@Override
	public List<CommentAndArticle> getCommentAndArticleWithBlobsByPageWithUserId(
			UserIdAndPageQuery userIdAndPageQuery) {
		// TODO Auto-generated method stub
		List<CommentAndArticle> commentAndArticleList=new ArrayList<CommentAndArticle>();
		List<Comment> commentList=commentDao.selectByPageWithUserId(userIdAndPageQuery);//所有的状态都查出来了
		for(Comment comment : commentList)
		{
			Article article=articleDao.selectByPrimaryKey(comment.getArticleid());
			CommentAndArticle commentAndArticle=new CommentAndArticle();
			commentAndArticle.setArticle(article);
			commentAndArticle.setComment(comment);
			commentAndArticleList.add(commentAndArticle);
		}
		return commentAndArticleList;
	}

}

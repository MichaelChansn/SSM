package com.ks.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.ArticleAndCommentNum;
import com.ks.ssm.domain.ArticleIdAndStatus;
import com.ks.ssm.domain.UserIdAndPageQuery;
import com.ks.ssm.service.IArticleAndCommentNumService;

@Service("articleAndCommentNumService")
public class ArticleAndCommentNumServiceImpl implements IArticleAndCommentNumService {


	@Resource
	private ArticleMapper articleDao;
	
	@Resource
	private CommentMapper commentDao;
	
	@Override
	public List<ArticleAndCommentNum> getArticleWithCommentNumByPage(UserIdAndPageQuery userIdAndPageQuery) {
		// TODO Auto-generated method stub
		List<ArticleAndCommentNum> articleAndComentNums=new ArrayList<>();
		List<Article> articles=articleDao.selectByPageWithUserId(userIdAndPageQuery);
		if (articles!=null && articles.size() > 0)
		{
			for (Article article : articles) {
				ArticleAndCommentNum articleAndCommentNum=new ArticleAndCommentNum();
				articleAndCommentNum.setArticle(article);
				articleAndCommentNum.setCommentNum(commentDao.countWithArticleIdAndStatus(new ArticleIdAndStatus(article.getId(),CommonConstants.COMMENT_STATUS_PASS)));
				articleAndComentNums.add(articleAndCommentNum);
			}
		}
		return articleAndComentNums;
	}

}

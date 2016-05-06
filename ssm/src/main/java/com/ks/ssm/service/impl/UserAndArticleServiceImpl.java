package com.ks.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.dao.UserMapper;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.ArticleIdAndStatus;
import com.ks.ssm.domain.User;
import com.ks.ssm.domain.UserAndArticle;
import com.ks.ssm.service.IUserAndArticleService;

/** 采用for循环的形式进行关联查询 */
@Service("userAndArticleService")
public class UserAndArticleServiceImpl implements IUserAndArticleService {

	@Resource
	private UserMapper userDao;

	@Resource
	private ArticleMapper articleDao;
	
	@Resource
	private CommentMapper commentDao;

	@Override
	public List<UserAndArticle> getUserAndArticleByArticle(List<Article> articleList) {
		// TODO Auto-generated method stub

		List<UserAndArticle> userAndArticleList = new ArrayList<>();
		if (articleList!=null && articleList.size() > 0) {
			for (Article article : articleList) {
				User user = userDao.selectByPrimaryKey(article.getUserid());
				int commentNums=commentDao.countWithArticleIdAndStatus(new ArticleIdAndStatus(article.getId(), CommonConstants.COMMENT_STATUS_PASS));
				UserAndArticle userAndArticle = new UserAndArticle();
				userAndArticle.setArticle(article);
				userAndArticle.setUser(user);
				userAndArticle.setCommentNums(commentNums);
				userAndArticleList.add(userAndArticle);

			}
		}
		return userAndArticleList;
	}

}

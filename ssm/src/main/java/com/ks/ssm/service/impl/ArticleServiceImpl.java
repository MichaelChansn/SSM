package com.ks.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.ArticleAndCommentNum;
import com.ks.ssm.domain.PageQuery;
import com.ks.ssm.domain.UserAndArticle;
import com.ks.ssm.domain.UserIdAndPageQuery;
import com.ks.ssm.service.IArticleService;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

	@Resource
	private ArticleMapper articleDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return articleDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Article record) {
		// TODO Auto-generated method stub
		return articleDao.insert(record);
	}

	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return articleDao.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return articleDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> selectByUserID(Long userid) {
		// TODO Auto-generated method stub
		return articleDao.selectByUserID(userid);
	}

	@Override
	public List<Article> selectByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return articleDao.selectByStatus(status);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		// TODO Auto-generated method stub
		return articleDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Article record) {
		// TODO Auto-generated method stub
		return articleDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Article record) {
		// TODO Auto-generated method stub
		return articleDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Article> selectByPageWithPublish(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		return articleDao.selectByPageWithPublish(pageQuery);
	}

	@Override
	public List<Article> selectByPageWithNotPublish(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		return articleDao.selectByPageWithNotPublish(pageQuery);
	}

	@Override
	public Article selectByPrimaryKeyWithoutBLOBs(Long id) {
		// TODO Auto-generated method stub
		return articleDao.selectByPrimaryKeyWithoutBLOBs(id);
	}

	@Override
	public List<Article> selectByPageWithUserId(UserIdAndPageQuery userIdAndPageQuery) {
		// TODO Auto-generated method stub
		return articleDao.selectByPageWithUserId(userIdAndPageQuery);
		
	}


}

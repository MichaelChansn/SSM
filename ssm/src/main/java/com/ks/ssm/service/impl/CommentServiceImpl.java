package com.ks.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.domain.ArticleIdAndStatus;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.UserIdAndPageQuery;
import com.ks.ssm.service.ICommentService;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {

	@Resource
	private CommentMapper commentDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return commentDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Comment record) {
		// TODO Auto-generated method stub
		return commentDao.insert(record);
	}

	@Override
	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return commentDao.insertSelective(record);
	}

	@Override
	public Comment selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return commentDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Comment> selectByArticleID(Long articleid) {
		// TODO Auto-generated method stub
		return commentDao.selectByArticleID(articleid);
	}

	@Override
	public List<Comment> selectByUserID(Long fromuserid) {
		// TODO Auto-generated method stub
		return commentDao.selectByUserID(fromuserid);
	}

	@Override
	public List<Comment> selectByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return commentDao.selectByStatus(status);
	}

	@Override
	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return commentDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Comment record) {
		// TODO Auto-generated method stub
		return commentDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return commentDao.updateByPrimaryKey(record);
	}

	@Override
	public int countWithStatus(boolean status) {
		// TODO Auto-generated method stub
		return commentDao.countWithStatus(status);
	}

	@Override
	public int countWithArticleIdAndStatus(ArticleIdAndStatus articleIdAndStatus) {
		// TODO Auto-generated method stub
		return commentDao.countWithArticleIdAndStatus(articleIdAndStatus);
	}

	@Override
	public Comment selectByPrimaryKeyWithOutBLOBs(Long id) {
		// TODO Auto-generated method stub
		return commentDao.selectByPrimaryKeyWithOutBLOBs(id);
	}

	@Override
	public List<Comment> selectByArticleIDWithStatus(ArticleIdAndStatus articleIdAndStatus) {
		// TODO Auto-generated method stub
		return commentDao.selectByArticleIDWithStatus(articleIdAndStatus);
	}

	@Override
	public List<Comment> selectByPageWithUserId(UserIdAndPageQuery userIdAndPageQuery) {
		// TODO Auto-generated method stub
		return commentDao.selectByPageWithUserId(userIdAndPageQuery);
	}

}

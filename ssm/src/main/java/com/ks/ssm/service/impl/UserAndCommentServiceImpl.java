package com.ks.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.dao.UserMapper;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;
import com.ks.ssm.domain.UserAndComment;
import com.ks.ssm.service.IUserAndCommentService;

/** 采用for循环的形式进行关联查询 */
@Service("userAndCommentService")
public class UserAndCommentServiceImpl implements IUserAndCommentService {

	@Resource
	private UserMapper userDao;

	@Resource
	private CommentMapper commentDao;
	@Override
	public List<UserAndComment> getUserAndCommentByComment(List<Comment> commentList) {
		// TODO Auto-generated method stub
		List<UserAndComment> userAndCommentList = new ArrayList<>();
		if (commentList!=null && commentList.size() > 0) {
			for (Comment comment : commentList) {
				User user = userDao.selectByPrimaryKey(comment.getFromuserid());
				UserAndComment userAndComment = new UserAndComment();
				userAndComment.setComment(comment);
				userAndComment.setUser(user);
				userAndCommentList.add(userAndComment);

			}
		}
		return userAndCommentList;
	}

}

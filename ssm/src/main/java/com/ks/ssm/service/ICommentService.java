package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.PageQuery;

public interface ICommentService {
	int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);
    
    List<Comment> selectByArticleID(Long articleid);
    
    List<Comment> selectByUserID(Long fromuserid);
    
    List<Comment> selectByStatus(Boolean status);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

}

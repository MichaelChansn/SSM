package com.ks.ssm.dao;

import java.util.List;

import com.ks.ssm.domain.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);
    
    List<Article> selectByUserID(Long userid);
    
    List<Article> selectByStatus(Boolean status);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}
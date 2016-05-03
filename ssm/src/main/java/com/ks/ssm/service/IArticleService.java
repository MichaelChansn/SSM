package com.ks.ssm.service;

import java.util.List;

import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.PageQuery;

public interface IArticleService {
	int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);
    
    List<Article> selectByUserID(Long userid);
    
    List<Article> selectByStatus(Boolean status);
    
    List<Article> selectByPageWithPublish(PageQuery pageQuery);
    
    List<Article> selectByPageWithNotPublish(PageQuery pageQuery);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

}

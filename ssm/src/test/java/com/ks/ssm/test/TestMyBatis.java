package com.ks.ssm.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ks.ssm.dao.ArticleMapper;
import com.ks.ssm.dao.CommentMapper;
import com.ks.ssm.dao.UserMapper;
import com.ks.ssm.domain.Article;
import com.ks.ssm.domain.Comment;
import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
  private static Logger logger = Logger.getLogger(TestMyBatis.class);
  @Resource
  private IUserService userService = null;
  @Resource
  private UserMapper userDao;
  @Resource
  private ArticleMapper articleDao;
  @Resource
  private CommentMapper commentDao;
  @Test
  public void test1() {
    User user = userService.getUserById(1);
    logger.info(JSON.toJSONString(user));
  }
  @Test
  public void testMybatis() {
    User user = userDao.selectByUserName("ks");
    logger.info("user一条记录.........................");
    logger.info(JSON.toJSONString(user));
    List<User> users=userDao.selectByEmail("ks");
    logger.info("list多条记录.........................");
    for(User userTemp : users)
    {
    	logger.info(JSON.toJSONString(userTemp));
    }
  }
  
  @Test
  public void testArticle() {
    Article article = articleDao.selectByPrimaryKey((long)1);
    logger.info("article一条记录.........................");
    logger.info(JSON.toJSONString(article));
    List<Article> articles=articleDao.selectByUserID((long) 1);
    logger.info("list多条记录.........................");
    for(Article articleTemp : articles)
    {
    	logger.info(JSON.toJSONString(articleTemp));
    }
    List<Article> articles2=articleDao.selectByStatus(false);
    logger.info("list多条记录.........................");
    for(Article articleTemp : articles2)
    {
    	logger.info(JSON.toJSONString(articleTemp));
    }
  }
  
  @Test
  public void testComment() {
    Comment comment = commentDao.selectByPrimaryKey((long)1);
    logger.info("comment一条记录.........................");
    logger.info(JSON.toJSONString(comment));
    List<Comment> comments1=commentDao.selectByArticleID((long) 1);
    logger.info("list多条记录.........................");
    for(Comment commentTemp : comments1)
    {
    	logger.info(JSON.toJSONString(commentTemp));
    }
    List<Comment> comments=commentDao.selectByUserID((long) 1);
    logger.info("list多条记录.........................");
    for(Comment commentTemp : comments)
    {
    	logger.info(JSON.toJSONString(commentTemp));
    }
    List<Comment> comments2=commentDao.selectByStatus(true);
    logger.info("list多条记录.........................");
    for(Comment commentTemp : comments2)
    {
    	logger.info(JSON.toJSONString(commentTemp));
    }
  }
}
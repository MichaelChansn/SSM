package com.ks.ssm.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ks.ssm.dao.UserMapper;
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
}
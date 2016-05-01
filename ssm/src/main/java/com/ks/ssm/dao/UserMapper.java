package com.ks.ssm.dao;

import com.ks.ssm.domain.User;

/**在mybatis中增刪改查都是有返回值的，返回值是影响的行数*/
public interface UserMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    
    User selectByUserName(String username );
    
    /**关于mybatis返回单一对象或对象列表的问题：
     * 1.返回数据类型由dao中的接口和*map.xml文件共同决定。另外，不论是返回单一对象还是对象列表，*map.xml中的配置都是一样的，都是resultMap="*Map"*或resultType=“* .* .*”类型.
     * 2.每一次mybatis从数据库中select数据之后，都会检查数据条数和dao中定义的返回值是否匹配。
     * 3.若返回一条数据，dao中定义的返回值是一个对象或对象的List列表，则可以正常匹配，将查询的数据按照dao中定义的返回值存放。
     * 4.若返回多条数据，dao中定义的返回值是一个对象，则无法将多条数据映射为一个对象，此时mybatis报错。
     * */
    User selectByEmail(String email );

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
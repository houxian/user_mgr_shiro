package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(long uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByName(String name);

    List<User> findPage();
}
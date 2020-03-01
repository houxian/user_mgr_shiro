package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UUserMapper {

    int deleteByPrimaryKey(long uid);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(long uid);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    UUser findByName(String name);

    List<UUser> findPage();
}
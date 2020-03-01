package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.URoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface URoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(URoleMenu record);

    int insertSelective(URoleMenu record);

    URoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URoleMenu record);

    int updateByPrimaryKey(URoleMenu record);
}
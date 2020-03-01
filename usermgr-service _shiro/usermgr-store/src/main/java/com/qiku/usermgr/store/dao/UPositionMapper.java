package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UPosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UPosition record);

    int insertSelective(UPosition record);

    UPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPosition record);

    int updateByPrimaryKey(UPosition record);
}
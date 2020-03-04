package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.Position;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}
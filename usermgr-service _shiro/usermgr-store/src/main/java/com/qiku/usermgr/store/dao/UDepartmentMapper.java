package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UDepartment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UDepartment record);

    int insertSelective(UDepartment record);

    UDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UDepartment record);

    int updateByPrimaryKey(UDepartment record);
}
package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UUserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    UUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUserRole record);

    int updateByPrimaryKey(UUserRole record);

    List<UUserRole> findUserRoles(long uid);
}
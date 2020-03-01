package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UMenu record);

    int insertSelective(UMenu record);

    UMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UMenu record);

    int updateByPrimaryKey(UMenu record);

    List<UMenu> findByUserName(String userName);

    List<UMenu> findAllMenu();

    List<UMenu> findMenuByUid(long uid);
}
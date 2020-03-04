package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> findByUserName(String userName);

    List<Menu> findAllMenu();

    List<Menu> findMenuByUid(long uid);
}
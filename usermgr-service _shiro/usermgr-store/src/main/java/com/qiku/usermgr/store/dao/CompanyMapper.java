package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {

    int deleteByPrimaryKey(Company key);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Company key);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}
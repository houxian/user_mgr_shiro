package com.qiku.usermgr.store.dao;

import com.qiku.usermgr.store.model.UCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UCompanyMapper {

    int deleteByPrimaryKey(UCompany key);

    int insert(UCompany record);

    int insertSelective(UCompany record);

    UCompany selectByPrimaryKey(UCompany key);

    int updateByPrimaryKeySelective(UCompany record);

    int updateByPrimaryKey(UCompany record);
}
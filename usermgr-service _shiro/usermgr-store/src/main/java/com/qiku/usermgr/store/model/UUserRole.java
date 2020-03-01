package com.qiku.usermgr.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * 
 */

public class UUserRole implements Serializable {
    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 更新时间
     */
    private Long updateDate;

    /**
     * 数据状态
     */
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
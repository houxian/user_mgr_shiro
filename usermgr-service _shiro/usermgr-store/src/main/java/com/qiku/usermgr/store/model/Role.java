package com.qiku.usermgr.store.model;

import java.io.Serializable;


/**
 * @author 
 * 
 */

public class Role implements Serializable {
    /**
     * 数据库id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Long crateDate;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Long crateDate) {
        this.crateDate = crateDate;
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
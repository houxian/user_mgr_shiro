package com.qiku.usermgr.store.model;

import java.io.Serializable;


/**
 * @author 
 * 
 */

public class UMenu implements Serializable {
    /**
     * 数据库id
     */
    private Long id;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String mName;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long mParentId;

    /**
     * 菜单url
     */
    private String mUrl;

    /**
     * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
     */
    private String mPerms;

    /**
     * 0：目录 1：菜单 2：按钮
     */
    private Integer mType;

    /**
     * 排序
     */
    private Integer mOrderNum;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Long getmParentId() {
        return mParentId;
    }

    public void setmParentId(Long mParentId) {
        this.mParentId = mParentId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmPerms() {
        return mPerms;
    }

    public void setmPerms(String mPerms) {
        this.mPerms = mPerms;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Integer getmOrderNum() {
        return mOrderNum;
    }

    public void setmOrderNum(Integer mOrderNum) {
        this.mOrderNum = mOrderNum;
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
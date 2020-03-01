package com.qiku.usermgr.store.model;

import java.io.Serializable;


/**
 * @author 
 * 
 */
public class UCompany  implements Serializable {
    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司描述
     */
    private String companyDes;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
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
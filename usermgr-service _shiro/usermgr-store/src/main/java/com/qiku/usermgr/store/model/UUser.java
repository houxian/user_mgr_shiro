package com.qiku.usermgr.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * 
 */

public class UUser  implements Serializable {

    /**
     * 数据库id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long uId;
    /**
     * 名字
     */
    private String name;

    /**
     * 性别 0男 1女
     */
    private Integer sex;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 职位id
     */
    private Long positionId;

    /**
     * 领导id
     */
    private Long leaderId;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户角色
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

    // 非数据库字段
    private String deptName;
    // 非数据库字段
    private String roleNames;
    // 非数据库字段
    private List<UUserRole> userRoles = new ArrayList<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public List<UUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UUserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
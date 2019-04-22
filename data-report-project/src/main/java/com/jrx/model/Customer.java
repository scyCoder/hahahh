package com.jrx.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 客户信息实体类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 10:41
 * @Version 1.0
 */
@ApiModel(value = "Customer", description = "客户信息实体类")
public class Customer implements Serializable {


    private static final long serialVersionUID = 583201539L;
    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private Integer custId;

    /**
     * 客户姓名
     */
    @ApiModelProperty("客户姓名")
    private String surname;

    /**
     * 客户性别
     */
    @ApiModelProperty("客户性别")
    private String gender;

    /**
     * 客户教育状况
     */
    @ApiModelProperty("客户教育状况")
    private String educaDes;

    /**
     * 客户婚姻状况
     */
    @ApiModelProperty("客户婚姻状况")
    private String marDes;

    /**
     * 客户生日
     */
    @ApiModelProperty("客户生日")
    private Integer birthday;

    /**
     * 客户住址
     */
    @ApiModelProperty("客户住址")
    private String address;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEducaDes() {
        return educaDes;
    }

    public void setEducaDes(String educaDes) {
        this.educaDes = educaDes == null ? null : educaDes.trim();
    }

    public String getMarDes() {
        return marDes;
    }

    public void setMarDes(String marDes) {
        this.marDes = marDes == null ? null : marDes.trim();
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("custId=").append(custId);
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", educaDes='").append(educaDes).append('\'');
        sb.append(", marDes='").append(marDes).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
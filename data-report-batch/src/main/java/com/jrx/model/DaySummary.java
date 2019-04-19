package com.jrx.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 日汇总计信息实体类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 10:43
 * @Version 1.0
 */
public class DaySummary implements Serializable {


    private static final long serialVersionUID = 583201539L;

    /**
     * 索引，格式：uuid_yyyyMMdd
     */
    private String sIndex;

    /**
     * 客户Id
     */
    private Integer custId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 交易日期
     */
    private Date transDate;

    /**
     * 客户姓名
     */
    private String surname;

    /**
     * 最大单笔交易金额
     */
    private BigDecimal tranMaxAmt;

    /**
     * 当天还款总金额
     */
    private BigDecimal payAmt;

    /**
     * 当天消费笔数
     */
    private Integer tranCnt;

    /**
     * 当天还款笔数
     */
    private Integer payCnt;

    /**
     * 当天交易总金额
     */
    private BigDecimal tranAmt;

    public String getsIndex() {
        return sIndex;
    }

    public void setsIndex(String sIndex) {
        this.sIndex = sIndex == null ? null : sIndex.trim();
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    public BigDecimal getTranMaxAmt() {
        return tranMaxAmt;
    }

    public void setTranMaxAmt(BigDecimal tranMaxAmt) {
        this.tranMaxAmt = tranMaxAmt;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public Integer getTranCnt() {
        return tranCnt;
    }

    public void setTranCnt(Integer tranCnt) {
        this.tranCnt = tranCnt;
    }

    public Integer getPayCnt() {
        return payCnt;
    }

    public void setPayCnt(Integer payCnt) {
        this.payCnt = payCnt;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DaySummary{");
        sb.append("sIndex='").append(sIndex).append('\'');
        sb.append(", custId=").append(custId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", transDate=").append(transDate);
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", tranMaxAmt=").append(tranMaxAmt);
        sb.append(", payAmt=").append(payAmt);
        sb.append(", tranCnt=").append(tranCnt);
        sb.append(", payCnt=").append(payCnt);
        sb.append(", tranAmt=").append(tranAmt);
        sb.append('}');
        return sb.toString();
    }
}
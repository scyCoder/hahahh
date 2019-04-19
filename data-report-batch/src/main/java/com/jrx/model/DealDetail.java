package com.jrx.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易明细信息实体类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 10:46
 * @Version 1.0
 */
public class DealDetail implements Serializable {


    private static final long serialVersionUID = 583201539L;

    /**
     * 交易Id
     */
    private Integer transId;

    /**
     * 客户Id
     */
    private Integer custId;

    /**
     * 客户账户号
     */
    private String account;

    /**
     * 客户卡号
     */
    private String cardNbr;

    /**
     * 交易流水号
     */
    private Integer tranno;

    /**
     * 账单月
     */
    private Integer monthNbr;

    /**
     * 交易金额
     */
    private BigDecimal bill;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 交易日期
     */
    private Date txnDatatime;

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getCardNbr() {
        return cardNbr;
    }

    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr == null ? null : cardNbr.trim();
    }

    public Integer getTranno() {
        return tranno;
    }

    public void setTranno(Integer tranno) {
        this.tranno = tranno;
    }

    public Integer getMonthNbr() {
        return monthNbr;
    }

    public void setMonthNbr(Integer monthNbr) {
        this.monthNbr = monthNbr;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType == null ? null : transType.trim();
    }

    public Date getTxnDatatime() {
        return txnDatatime;
    }

    public void setTxnDatatime(Date txnDatatime) {
        this.txnDatatime = txnDatatime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DealDetail{");
        sb.append("transId=").append(transId);
        sb.append(", custId=").append(custId);
        sb.append(", account='").append(account).append('\'');
        sb.append(", cardNbr='").append(cardNbr).append('\'');
        sb.append(", tranno=").append(tranno);
        sb.append(", monthNbr=").append(monthNbr);
        sb.append(", bill=").append(bill);
        sb.append(", transType='").append(transType).append('\'');
        sb.append(", txnDatatime=").append(txnDatatime);
        sb.append('}');
        return sb.toString();
    }
}
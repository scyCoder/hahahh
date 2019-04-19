package com.jrx.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 记录客户一个月内每天的交一交易金额，也用于
 * 记录客户近三个月内的消费笔数
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/18 22:32
 * @Version 1.0
 */
public class DayDeal implements Serializable {


    private static final long serialVersionUID = -5254833485101044879L;

    /**
     * 每天的交易额
     */
    private BigDecimal dealMoney;

    /**
     * 交易的日期，这里使用的是字符串
     */
    private String dateStr;

    public BigDecimal getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DayDeal{");
        sb.append("dealMoney=").append(dealMoney);
        sb.append(", dateStr='").append(dateStr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.jrx.model;

import java.io.Serializable;

/**
 * 记录客户一个月内的消费总金额和付款总金额
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/18 15:18
 * @Version 1.0
 */
public class DealData implements Serializable {


    private static final long serialVersionUID = 58201539L;
    /**
     * 一个月内的还款总金额
     */
    private Integer totalPayInMonth;
    /**
     * 一个月内的消费总金额
     */
    private Integer totalTranInMonth;

    public Integer getTotalPayInMonth() {
        return totalPayInMonth;
    }

    public void setTotalPayInMonth(Integer totalPayInMonth) {
        this.totalPayInMonth = totalPayInMonth;
    }

    public Integer getTotalTranInMonth() {
        return totalTranInMonth;
    }

    public void setTotalTranInMonth(Integer totalTranInMonth) {
        this.totalTranInMonth = totalTranInMonth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DealData{");
        sb.append("totalPayInMonth=").append(totalPayInMonth);
        sb.append(", totalTranInMonth=").append(totalTranInMonth);
        sb.append('}');
        return sb.toString();
    }
}

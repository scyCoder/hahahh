package com.jrx.mapper;

import com.jrx.model.DaySummary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaySummaryMapper {

    /**
     * 获得指定客户的单笔最大交易额、还款总金额
     * 还款次数、消费次数、当日交易金额
     *
     * @param custId
     * @return
     */
    DaySummary getCurrDateData(int custId);

}
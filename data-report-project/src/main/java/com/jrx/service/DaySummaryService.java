package com.jrx.service;

import com.jrx.model.DaySummary;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/17 10:16
 * @Version 1.0
 */
public interface DaySummaryService {

    /**
     * 添加日汇信息
     *
     * @param daySummary
     * @return
     */
    int insert(DaySummary daySummary);

    /**
     * 根据客户id，查询该客户的日汇信息
     *
     * @param custId
     * @return
     */
    DaySummary findByCustomerId(Integer custId);

    /**
     * 跟新客户日汇信息
     *
     * @param daySummary
     * @return
     */
    int update(DaySummary daySummary);

    /**
     * 根据客户id删除客户日汇信息
     *
     * @param custId
     * @return
     */
    int deleteByCustomerId(Integer custId);

    /**
     * 查询所有用户日汇信息
     *
     * @return
     */
    List<DaySummary> findAllDaySummary();

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DaySummary> findDaySummaryByPage(Integer pageNum, Integer pageSize);
}

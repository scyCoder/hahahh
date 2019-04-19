package com.jrx.service;

import com.jrx.model.DayDeal;
import com.jrx.model.DealData;
import com.jrx.model.DealDetail;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 16:56
 * @Version 1.0
 */
public interface DealDetailService {

    /**
     * 插入交易明细信息
     *
     * @param dealDetail
     * @return
     */
    int insert(DealDetail dealDetail);

    /**
     * 根据transId查询交易明细信息
     *
     * @param transId
     * @return
     */
    DealDetail findByDealDetailTransId(Integer transId);

    /**
     * 跟新交易明细信息
     *
     * @param dealDetail
     * @return
     */
    int update(DealDetail dealDetail);

    /**
     * 根据交易明细transId删除交易明细信息
     *
     * @param transId
     * @return
     */
    int deleteByDealDetailTransId(Integer transId);

    /**
     * 查询所有交易明细信息
     *
     * @return
     */
    List<DealDetail> findAllDealDetail();

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DealDetail> findDealDetailByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据客户id查询此客户一个月内的消费总额和还款总额
     *
     * @param custId
     * @return
     */
    DealData findTotalInMonthByCustId(Integer custId);

    /**
     * 统计客户一个月内每天的交易额
     *
     * @param custId
     * @return
     */
    List<DayDeal> findDayDealByCustId(Integer custId);

    /**
     * 查询客户进三个内的消费笔数
     * @param custId
     * @return
     */
    List<DayDeal> findThreeMonthsTranCountByCustId(Integer custId);

    /**
     * 根据客户的id查询
     * @param custId
     * @return
     */
    List<DealDetail> findByCustId(Integer custId);

    List<DealDetail> findBySearch(String searchStr);
}

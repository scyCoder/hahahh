package com.jrx.mapper;

import com.jrx.model.DayDeal;
import com.jrx.model.DealData;
import com.jrx.model.DealDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 交易明细接口
 *
 * @author Administrator
 */
@Mapper
public interface DealDetailMapper {

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
     * @param map 封装分页需要的参数：pageNum和PageSize
     * @return
     */
    List<DealDetail> findDealDetailByPage(Map<String, Integer> map);


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
     *
     * @param custId
     * @return
     */
    List<DayDeal> findThreeMonthsTranCountByCustId(Integer custId);

    /**
     * 根据客户的id查询
     *
     * @param custId
     * @return
     */
    List<DealDetail> findByCustId(Integer custId);

    /**
     * 根据客户的交易类行查询
     *
     * @param transType
     * @return
     */
    List<DealDetail> findByType(String transType);

    /**
     * 根据日期查询
     *
     * @param strDate
     * @return
     */
    List<DealDetail> findByDate(String strDate);

}
package com.jrx.service.impl;

import com.jrx.mapper.DealDetailMapper;
import com.jrx.model.DayDeal;
import com.jrx.model.DealData;
import com.jrx.model.DealDetail;
import com.jrx.service.DealDetailService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易明细实现类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 16:57
 * @Version 1.0
 */
@Service
public class DealDetailServiceImpl implements DealDetailService {

    @Autowired
    private DealDetailMapper dealDetailMapper;

    /**
     * 插入交易明细
     *
     * @param dealDetail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(DealDetail dealDetail) {
        return dealDetailMapper.insert(dealDetail);
    }

    /**
     * 根据transId查询交易明细
     *
     * @param transId
     * @return
     */
    @Override
    public DealDetail findByDealDetailTransId(Integer transId) {
        return dealDetailMapper.findByDealDetailTransId(transId);
    }

    /**
     * 更新交易明细
     *
     * @param dealDetail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(DealDetail dealDetail) {
        return dealDetailMapper.update(dealDetail);
    }

    /**
     * 根据transId删除交易明细
     *
     * @param transId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByDealDetailTransId(Integer transId) {
        return dealDetailMapper.deleteByDealDetailTransId(transId);
    }

    /**
     * 查询所有的交易明细
     *
     * @return
     */
    @Override
    public List<DealDetail> findAllDealDetail() {
        return dealDetailMapper.findAllDealDetail();
    }

    /**
     * 分页查询交易明细
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<DealDetail> findDealDetailByPage(Integer pageNum, Integer pageSize) {
        Map map = new HashMap();
        map.put("pageNum", (pageNum - 1) * pageSize);
        map.put("pageSize", pageSize);
        return dealDetailMapper.findDealDetailByPage(map);
    }

    /**
     * 统计客户一个月内的消费总金额和还款总金额
     *
     * @param custId
     * @return
     */
    @Override
    public DealData findTotalInMonthByCustId(Integer custId) {
        return dealDetailMapper.findTotalInMonthByCustId(custId);
    }

    /**
     * 统计客户一个月内每天的交易额
     *
     * @param custId
     * @return
     */
    @Override
    public List<DayDeal> findDayDealByCustId(Integer custId) {
        return dealDetailMapper.findDayDealByCustId(custId);
    }

    /**
     * 查询客户进三个内的消费笔数
     *
     * @param custId
     * @return
     */
    @Override
    public List<DayDeal> findThreeMonthsTranCountByCustId(Integer custId) {
        return dealDetailMapper.findThreeMonthsTranCountByCustId(custId);
    }

    /**
     * 根据客户的id查询交易明细信息
     *
     * @param custId
     * @return
     */
    @Override
    public List<DealDetail> findByCustId(Integer custId) {
        return dealDetailMapper.findByCustId(custId);
    }

    /**
     * 根据search值进行查询
     *
     * @param searchStr
     * @return
     */
    @Override
    public List<DealDetail> findBySearch(String searchStr) {
        if (NumberUtils.isNumber(searchStr)) {
            int custId = Integer.parseInt(searchStr);
            return dealDetailMapper.findByCustId(custId);
        } else if (StringUtils.contains(searchStr, "-")) {
            return dealDetailMapper.findByDate(searchStr);
        }

        return dealDetailMapper.findByType(searchStr);
    }
}

package com.jrx.test;

import com.jrx.model.DayDeal;
import com.jrx.model.DealData;
import com.jrx.model.DealDetail;
import com.jrx.service.DealDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:22
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealDetailServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DealDetailServiceImplTest.class);

    @Autowired
    private DealDetailService dealDetailService;


    /**
     * 测试交易明细信息插入
     */
    @Test
    public void testInsert() {

        DealDetail dealDetail = new DealDetail();
        dealDetail.setCustId(1);
        dealDetail.setAccount("1231");
        dealDetail.setCardNbr("1234897");
        dealDetail.setTranno(22);
        dealDetail.setMonthNbr(3);
        dealDetail.setBill(new BigDecimal(520.00));
        dealDetail.setTransType("消费");
        dealDetail.setTxnDatatime(new Date());
        int result = dealDetailService.insert(dealDetail);
        logger.info("插入后返回值为：{}", result);
    }

    /**
     * 测试根据TransId查询交易明细信息
     */
    @Test
    public void testFindByCustomerId() {
        DealDetail dealDetail = dealDetailService.findByDealDetailTransId(1);
        logger.info("根据id查询的交易明细的信息是：{}", dealDetail);
    }

    /**
     * 测试更新交易明细信息
     */
    @Test
    public void testUpdate() {
        DealDetail dealDetail = new DealDetail();
        // 更新交易明细信息的bill
        dealDetail.setTransId(21);
        dealDetail.setBill(new BigDecimal(30.00));
        int result = dealDetailService.update(dealDetail);
        logger.info("更新后的返回值：{}", result);
    }

    /**
     * 测试根据交易明细TransId，删除交易明细信息
     */
    @Test
    public void testDeleteByCustomerId() {
        int result = dealDetailService.deleteByDealDetailTransId(21);
        logger.info("删除后返回的值：{}", result);
    }

    /**
     * 查询所有交易明细信息
     */
    @Test
    public void findAllCustomer() {
        List<DealDetail> dealDetailList = dealDetailService.findAllDealDetail();
        for (DealDetail dealDetail : dealDetailList) {
            logger.info("交易明细信息：{}", dealDetail);
        }
    }

    /**
     * 分页查询交易明细信息
     */
    @Test
    public void findCustomerByPage() {
        List<DealDetail> dealDetailByPageList = dealDetailService.findDealDetailByPage(2, 5);
        for (DealDetail dealDetail : dealDetailByPageList) {
            logger.info("交易明细信息：{}", dealDetail);
        }
    }

    /**
     * 测试一个月内的消费总金额和还款总金额
     */
    @Test
    public void findTotalInMonthByCustId() {
        DealData dealData = dealDetailService.findTotalInMonthByCustId(2);
        logger.info("一个月内的消费总金额和还款总金额：{}", dealData);
    }

    /**
     * 测试一个内每天的交易额信息
     */
    @Test
    public void findDayDealByCustId() {

        List<DayDeal> dayDealList = dealDetailService.findDayDealByCustId(1);
        logger.info("一个月内每天的交易额信息：{}", dayDealList);
    }

    /**
     * 测试三个月内的消费笔数
     */
    @Test
    public void findThreeMonthsTranCountByCustId() {
        List<DayDeal> threeMonthsList = dealDetailService.findThreeMonthsTranCountByCustId(2);
        logger.info("三个月内的消费笔数：{}", threeMonthsList);

    }

    /**
     * 测试根据客户的id查询该客户的交易明细信息
     */
    @Test
    public void findByCustId() {
        List<DealDetail> dealDetailList = dealDetailService.findByCustId(1);
        logger.info("根据客户查询的交易明细信息：{}", dealDetailList);
    }


    /**
     * 根据日期查询
     */
    @Test
    public void findBySearch() {
        List<DealDetail> dateList = dealDetailService.findBySearch("消费");
        logger.info("根据search值查询：{}", dateList);
    }
}

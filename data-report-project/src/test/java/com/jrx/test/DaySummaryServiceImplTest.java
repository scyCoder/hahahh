package com.jrx.test;

import com.jrx.model.DaySummary;
import com.jrx.service.DaySummaryService;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:22
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaySummaryServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DaySummaryServiceImplTest.class);

    @Autowired
    private DaySummaryService daySummaryService;

    /**
     * 测试客户日汇信息插入
     */
    @Test
    public void testInsert() {

        DaySummary daySummary = new DaySummary();
        daySummary.setCustId(7);
        // 将字符串转为日期
        String strDate = "2019-04-17";
        String[] parttern = {"yyyy-MM-dd"};
        Date date = null;
        try {
            date = DateUtils.parseDate(strDate, parttern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        daySummary.setSurname("大王王");
        daySummary.setTransDate(date);
        daySummary.setTranMaxAmt(new BigDecimal(600.00));
        daySummary.setPayAmt(new BigDecimal(200.00));
        daySummary.setPayCnt(2);
        daySummary.setTranCnt(2);
        daySummary.setTranAmt(new BigDecimal(800.00));
        int result = daySummaryService.insert(daySummary);
        logger.info("插入返回的值：{}", result);
    }

    /**
     * 测试根据id查询用户日汇信息
     */
    @Test
    public void testFindByCustomerId() {
        DaySummary daySummary = daySummaryService.findByCustomerId(1);
        logger.info("根据id查询的客户日汇信息是：{}", daySummary);
    }

    /**
     * 测试更新客户日汇信息
     */
    @Test
    public void testUpdate() {
        DaySummary daySummary = new DaySummary();
        // 更新客户的姓名
        daySummary.setCustId(7);
        daySummary.setSurname("大屁孩");
        int result = daySummaryService.update(daySummary);
        logger.info("更新后的返回值：{}", result);
    }

    /**
     * 测试根据客户Id，删除客户日汇信息
     */
    @Test
    public void testDeleteByCustomerId() {
        int result = daySummaryService.deleteByCustomerId(7);
        logger.info("删除后返回的值：{}", result);
    }

    /**
     * 查询所有客户
     */
    @Test
    public void findAllDaySummary() {
        List<DaySummary> daySummaryList = daySummaryService.findAllDaySummary();
        for (DaySummary daySummary : daySummaryList) {

            logger.info("客户的日汇信息：{}", daySummary);
        }
    }

    /**
     * 分页查询客户日汇信息
     */
    @Test
    public void findDaySummaryByPage() {
        List<DaySummary> daySummaryByPageList = daySummaryService.findDaySummaryByPage(2, 5);
        for (DaySummary daySummary : daySummaryByPageList) {
            logger.info("客户信息为：{}", daySummary);
        }
    }
}

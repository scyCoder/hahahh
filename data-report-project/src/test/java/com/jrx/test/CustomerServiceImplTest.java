package com.jrx.test;

import com.jrx.model.Customer;
import com.jrx.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:22
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImplTest.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 测试客户插入
     */
    @Test
    public void testInsert() {

        Customer customer = new Customer();
        customer.setSurname("hello");
        customer.setGender("男");
        customer.setEducaDes("高中");
        customer.setMarDes("已婚");
        customer.setBirthday(20010301);
        customer.setAddress("厦门");
        int result = customerService.insert(customer);
        logger.info("插入后返回值为：{}", result);
    }

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testFindByCustomerId() {
        Customer customer = customerService.findByCustomerId(1);
        logger.info("根据id查询的客户是：{}", customer);
    }

    /**
     * 测试更新客户信息
     */
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        // 更新客户的姓名
        customer.setCustId(21);
        customer.setSurname("大屁孩");
        int result = customerService.update(customer);
        logger.info("更新后的返回值：{}", result);
    }

    /**
     * 测试根据客户Id，删除客户
     */
    @Test
    public void testDeleteByCustomerId() {
        int result = customerService.deleteByCustomerId(21);
        logger.info("删除后返回的值：{}", result);
    }

    /**
     * 查询所有客户
     */
    @Test
    public void findAllCustomer() {
        List<Customer> customerList = customerService.findAllCustomer();
        for (Customer customer : customerList) {

            logger.info("客户的信息：{}", customer);
        }
    }

    /**
     * 分页查询客户信息
     */
    @Test
    public void findCustomerByPage() {
        List<Customer> customerByPageList = customerService.findCustomerByPage(2, 5);
        for (Customer customer : customerByPageList) {
            logger.info("客户信息为：{}", customer);
        }
    }
}

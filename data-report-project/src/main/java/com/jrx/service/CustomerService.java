package com.jrx.service;

import com.jrx.model.Customer;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:17
 * @Version 1.0
 */
public interface CustomerService {

    /**
     * 添加用户
     *
     * @param customer
     * @return
     */
    int insert(Customer customer);

    /**
     * 根据客户id查询客户信息
     *
     * @param custId
     * @return
     */
    Customer findByCustomerId(Integer custId);


    /**
     * 更新客户信息
     *
     * @param customer
     * @return
     */
    int update(Customer customer);

    /**
     * 根据客户的Id删除客户
     *
     * @param custId
     * @return
     */
    int deleteByCustomerId(Integer custId);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<Customer> findAllCustomer();

    /**
     * 分页查询客户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Customer> findCustomerByPage(Integer pageNum, Integer pageSize);

}

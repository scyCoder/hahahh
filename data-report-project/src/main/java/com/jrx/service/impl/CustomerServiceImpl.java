package com.jrx.service.impl;

import com.jrx.mapper.CustomerMapper;
import com.jrx.model.Customer;
import com.jrx.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户实现类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:17
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 添加用户
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Customer customer) {
        return customerMapper.insert(customer);
    }

    /**
     * 根据客户id，查询客户
     *
     * @param custId
     * @return
     */
    @Override
    public Customer findByCustomerId(Integer custId) {
        return customerMapper.findByCustomerId(custId);
    }

    /**
     * 更新客户信息
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Customer customer) {

        return customerMapper.update(customer);
    }

    /**
     * 根据客户的Id删除客户
     *
     * @param custId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByCustomerId(Integer custId) {

        return customerMapper.deleteByCustomerId(custId);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Customer> findAllCustomer() {
        return customerMapper.findAllCustomer();
    }

    /**
     * 分页查询客户
     *
     * @param pageNum  页数
     * @param pageSize 每页显示大小
     * @return
     */
    @Override
    public List<Customer> findCustomerByPage(Integer pageNum, Integer pageSize) {
        Map<String, Integer> map = new HashMap();
        map.put("pageNum", (pageNum - 1) * pageSize);
        map.put("pageSize", pageSize);
        return customerMapper.findCustomerByPage(map);
    }
}

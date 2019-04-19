package com.jrx.mapper;

import com.jrx.model.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 *
 * 客户接口
 * @author Administrator
 */
@Mapper
public interface CustomerMapper {
    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    int insert(Customer customer);

    /**
     * 根据id查询客户
     *
     * @param custId
     * @return
     */
    Customer findByCustomerId(Integer custId);

    /**
     * 跟新客户信息
     *
     * @param customer
     * @return
     */
    int update(Customer customer);

    /**
     * 根据客户id删除客户信息
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
     * 分页查询
     *
     * @param map 封装分页需要的参数：pageNum和PageSize
     * @return
     */
    List<Customer> findCustomerByPage(Map<String, Integer> map);


}
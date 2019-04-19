package com.jrx.controller;

import com.jrx.common.ResponseData;
import com.jrx.model.Customer;
import com.jrx.service.CustomerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/15 14:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 增加客户
     *
     * @param customer
     * @return
     */
    @ApiOperation(value = "创建客户", notes = "根据Customer对象创建客户")
    @ApiImplicitParam(name = "customer", value = "客户详细实体customer", required = true, dataType = "Customer")
    @PostMapping("/insert-customer")
    public ResponseData insertCustomer(@RequestBody @ApiParam(name = "添加的客户对象") Customer customer) {
        int result = customerService.insert(customer);
        if (result > 0) {
            return new ResponseData(result, "200", "插入成功");
        }
        return new ResponseData(result, "500", "插入失败");
    }

    /**
     * 根据id删除客户
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "删除客户", notes = "根据id删除客户")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @DeleteMapping("/delete-by-id/{custId}")
    public ResponseData deleteByCustomerId(@PathVariable("custId") Integer custId) {
        int result = customerService.deleteByCustomerId(custId);
        if (result > 0) {
            return new ResponseData(result, "200", "删除成功");
        }
        return new ResponseData(result, "500", "删除失败");
    }

    /**
     * 更新用户
     *
     * @param customer
     * @return
     */
    @ApiOperation(value = "更新客户", notes = "更新客户")
    @ApiImplicitParam(name = "customer", value = "需要更新的客户实体类", required = true, dataType = "Customer")
    @PutMapping("/update-customer")
    public ResponseData updateCustomer(@RequestBody Customer customer) {
        int result = customerService.update(customer);
        if (result > 0) {
            return new ResponseData(result, "200", "更新成功");
        }
        return new ResponseData(result, "500", "更新失败");
    }

    /**
     * 根据客户id，查询客户
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "查询客户", notes = "根据id查询客户")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @GetMapping("/find-by-id/{custId}")
    public ResponseData findByCustomerId(@PathVariable("custId") Integer custId) {
        Customer customer = customerService.findByCustomerId(custId);
        if (null != customer) {
            return new ResponseData(customer, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询失败");
    }

    /**
     * 查询所有的客户
     *
     * @return
     */
    @ApiOperation(value = "查询所有客户", notes = "查询所有客户")
    @GetMapping("/find-all")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    public ResponseData findAllCustomer() {
        List<Customer> customerList = customerService.findAllCustomer();
        if (customerList.size() != 0) {
            return new ResponseData(customerList, "200", "查询成功");
        } else if (customerList.size() == 0) {
            return new ResponseData(null, "200", "查询成功，但没数据");
        }
        return new ResponseData(null, "500", "查询错误");
    }

    /**
     * 分页查询客户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询客户", notes = "分页查询客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询的页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = true, dataType = "int")
    })
    @GetMapping("find-by-page/{pageNum}/{pageSize}")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    public ResponseData findCustomerByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer
            pageSize) {
        List<Customer> customerByPageList = customerService.findCustomerByPage(pageNum, pageSize);
        if (customerByPageList.size() != 0) {
            return new ResponseData(customerByPageList, "200", "查询成功");
        } else if (customerByPageList.size() == 0) {
            return new ResponseData(null, "200", "查询成功，但没数据");
        }
        return new ResponseData(null, "500", "查询错误");

    }
}

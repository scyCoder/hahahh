package com.jrx.controller;

import com.jrx.common.ResponseData;
import com.jrx.model.DaySummary;
import com.jrx.service.DaySummaryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/17 10:00
 * @Version 1.0
 */
@RestController
@RequestMapping("day-summary")
public class DaySummaryController {

    @Autowired
    private DaySummaryService daySummaryService;

    /**
     * 增加客户日汇信息
     *
     * @param daySummary
     * @return
     */
    @ApiOperation(value = "创建客户日汇信息", notes = "根据DaySummary对象创建客户")
    @ApiImplicitParam(name = "daySumary", value = "daySumary", required = true, dataType = "DaySummary")
    @PostMapping("/insert-day-summary")
    public ResponseData insertCustomer(@RequestBody DaySummary daySummary) {
        int result = daySummaryService.insert(daySummary);
        if (result > 0) {
            return new ResponseData(result, "200", "插入成功");
        }
        return new ResponseData(result, "500", "插入失败");
    }

    /**
     * 根据id删除客户日汇信息
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "删除客户日汇信息", notes = "根据id删除客户日汇信息")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @DeleteMapping("/delete-by-id/{custId}")
    public ResponseData deleteByCustomerId(@PathVariable("custId") Integer custId) {
        int result = daySummaryService.deleteByCustomerId(custId);
        if (result > 0) {
            return new ResponseData(result, "200", "删除成功");
        }
        return new ResponseData(result, "500", "删除失败");
    }

    /**
     * 更新用户日汇信息
     *
     * @param daySummary
     * @return
     */
    @ApiOperation(value = "更新客户日汇信息", notes = "更新客户日汇信息")
    @ApiImplicitParam(name = "daySummary", value = "需要更新的客户日汇实体类", required = true, dataType = "DaySummary")
    @PutMapping("/update-day-summary")
    public ResponseData updateCustomer(@RequestBody DaySummary daySummary) {
        int result = daySummaryService.update(daySummary);
        if (result > 0) {
            return new ResponseData(result, "200", "更新成功");
        }
        return new ResponseData(result, "500", "更新失败");
    }

    /**
     * 根据客户id，查询客户日汇信息
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "查询客户日汇信息", notes = "根据客户id查询该客户日汇信息")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @GetMapping("/find-by-id/{custId}")
    public ResponseData findByCustomerId(@PathVariable("custId") Integer custId) {
        DaySummary daySummary = daySummaryService.findByCustomerId(custId);
        if (null != daySummary) {
            return new ResponseData(daySummary, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询失败");
    }

    /**
     * 查询所有的客户日汇信息
     *
     * @return
     */
    @ApiOperation(value = "查询所有客户日汇信息", notes = "查询所有客户日汇信息")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-all")
    public ResponseData findAllCustomer() {
        List<DaySummary> daySummaryList = daySummaryService.findAllDaySummary();
        if (daySummaryList.isEmpty()) {
            return new ResponseData(null, "500", "查询错误");

        }
        return new ResponseData(daySummaryList, "200", "查询成功");

    }

    /**
     * 分页查询客户日汇信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询客户日汇信息", notes = "分页查询客户日汇信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询的页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = true, dataType = "int")
    })
    @GetMapping("find-by-page/{pageNum}/{pageSize}")
    public ResponseData findCustomerByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer
            pageSize) {
        List<DaySummary> daySummaryByPageList = daySummaryService.findDaySummaryByPage(pageNum, pageSize);
        if (daySummaryByPageList.isEmpty()) {
            return new ResponseData(null, "500", "查询错误");
        }
        return new ResponseData(daySummaryByPageList, "200", "查询成功");


    }


}

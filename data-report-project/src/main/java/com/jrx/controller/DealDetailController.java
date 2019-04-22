package com.jrx.controller;

import com.jrx.common.ResponseData;
import com.jrx.model.DayDeal;
import com.jrx.model.DealData;
import com.jrx.model.DealDetail;
import com.jrx.service.DealDetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/16 11:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/deal-detail")
public class DealDetailController {

    @Autowired
    private DealDetailService dealDetailService;

    /**
     * 插入交易明细
     *
     * @param dealDetail
     * @return
     */
    @ApiOperation(value = "创建客户交易明细对象", notes = "根据DealDetail对象创建交易明细对象")
    @ApiImplicitParam(name = "dealDetail", value = "交易明细详细实体DealDetail", required = true, dataType = "DealDetail")
    @PostMapping("/insert-deal-detail")
    public ResponseData insetDealDetail(@RequestBody DealDetail dealDetail) {
        dealDetail.setTxnDatatime(new Date());
        int result = dealDetailService.insert(dealDetail);
        if (result > 0) {
            return new ResponseData(result, "200", "插入成功");
        }
        return new ResponseData(result, "500", "插入失败");
    }

    /**
     * 根据交易明细id,删除交易明细id
     *
     * @param transId
     * @return
     */
    @ApiOperation(value = "删除交易明细", notes = "根据交易transId删除交易明细信息")
    @ApiImplicitParam(name = "transId", value = "交易明细id", required = true, dataType = "int")
    @DeleteMapping("/delete-by-trans-id/{transId}")
    public ResponseData deleteByDealDetailTransId(@PathVariable("transId") Integer transId) {
        int result = dealDetailService.deleteByDealDetailTransId(transId);
        if (result > 0) {
            return new ResponseData(result, "200", "删除成功");
        }
        return new ResponseData(result, "500", "删除失败");
    }

    /**
     * 更新交易明细信息
     *
     * @param dealDetail
     * @return
     */
    @ApiOperation(value = "更新交易明细信息", notes = "更新交易明细")
    @ApiImplicitParam(name = "dealDetail", value = "需要更新的交易明细实体类", required = true, dataType = "dealDetail")
    @PutMapping("/update-deal-detail")
    public ResponseData updateDealDetail(@RequestBody DealDetail dealDetail) {
        int result = dealDetailService.update(dealDetail);
        if (result > 0) {
            return new ResponseData(result, "200", "更新成功");
        }
        return new ResponseData(result, "500", "更新失败");
    }

    /**
     * 根据交易transId，查询交易明细信息
     *
     * @param transId
     * @return
     */
    @ApiOperation(value = "查询交易明细", notes = "根据transId查询交易明细信息")
    @ApiImplicitParam(name = "transId", value = "交易明细Id", required = true, dataType = "int")
    @GetMapping("/find-by-trans-id/{transId}")
    public ResponseData findDealDetailByTransId(@PathVariable("transId") Integer transId) {
        DealDetail dealDetail = dealDetailService.findByDealDetailTransId(transId);
        if (null != dealDetail) {
            return new ResponseData(dealDetail, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询失败");
    }

    /**
     * 查询所有的交易明细
     *
     * @return
     */
    @ApiOperation(value = "查询所有交易明细", notes = "查询所有交易明细信息")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-all")
    public ResponseData findAllDealDetail() {
        List<DealDetail> dealDetailList = dealDetailService.findAllDealDetail();
        if (dealDetailList.isEmpty()) {
            return new ResponseData(null, "500", "查询错误");
        }

        return new ResponseData(dealDetailList, "200", "查询成功");
    }

    /**
     * 分页查询交易明细信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询交易明细", notes = "分页查询交易明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询的页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的条数", required = true, dataType = "int")
    })
    @GetMapping("/find-by-page/{pageNum}/{pageSize}")
    public ResponseData findDealDetailByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize")
            Integer pageSize) {
        List<DealDetail> dealDetailByPageList = dealDetailService.findDealDetailByPage(pageNum, pageSize);
        if (dealDetailByPageList.isEmpty()) {
            return new ResponseData(null, "500", "查询错误");
        }
        return new ResponseData(dealDetailByPageList, "200", "查询成功");


    }

    /**
     * 查询一个月内的消费和还款总金额
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "查询一个月的消费和还款总金额", notes = "查询当前用户近一个月的消费总金额和还款总金额")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-total/{custId}")
    public ResponseData findTotalInMonthByCustId(@PathVariable("custId") Integer custId) {
        DealData dealData = dealDetailService.findTotalInMonthByCustId(custId);
        if (null != dealData) {
            return new ResponseData(dealData, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询错误");
    }

    /**
     * 查询一个月内每天的交易额
     *
     * @param custId
     * @return
     */
    @ApiOperation(value = "查询一个月每天的交易额", notes = "查询当前用户近一个月每天交易额")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-day-deal/{custId}")
    public ResponseData findDayDealByCustId(@PathVariable("custId") Integer custId) {
        List<DayDeal> dayDealList = dealDetailService.findDayDealByCustId(custId);
        if (null != dayDealList) {
            return new ResponseData(dayDealList, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询错误");
    }

    @ApiOperation(value = "查询近三个月的消费笔数", notes = "查询当前用户近三个月的消费笔数")
    @ApiImplicitParam(name = "custId", value = "客户id", required = true, dataType = "int")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-three-months/{custId}")
    public ResponseData findThreeMonthsTranCountByCustId(@PathVariable("custId") Integer custId) {
        List<DayDeal> threeMonthsList = dealDetailService.findThreeMonthsTranCountByCustId(custId);
        if (null != threeMonthsList) {
            return new ResponseData(threeMonthsList, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询错误");

    }

    /**
     * 根据客户的床查询交易明细
     *
     * @param search
     * @return
     */
    @ApiOperation(value = "根据search值查询", notes = "根据输入的search值进行查询")
    @ApiImplicitParam(name = "search", value = "search值", required = true, dataType = "String")
    @CrossOrigin(origins = "http://localhost:8000", allowCredentials = "true")
    @GetMapping("/find-by-search/{search}")
    public ResponseData findBySerch(@PathVariable("search") String search) {

        List<DealDetail> dealDetailListByCustId = dealDetailService.findBySearch(search);
        if (null != dealDetailListByCustId) {
            return new ResponseData(dealDetailListByCustId, "200", "查询成功");
        }
        return new ResponseData(null, "500", "查询错误");
    }
}

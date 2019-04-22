package com.jrx.service.impl;

import com.jrx.mapper.DaySummaryMapper;
import com.jrx.model.DaySummary;
import com.jrx.service.DaySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/17 10:17
 * @Version 1.0
 */
@Service
public class DaySummaryServiceImpl implements DaySummaryService {

    @Autowired
    private DaySummaryMapper daySummaryMapper;

    @Override
    public int insert(DaySummary daySummary) {

        UUID uuid = UUID.randomUUID();
        LocalDate now = LocalDate.now();
        String dateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 创建索引
        String index = uuid + "_" + dateStr;
        daySummary.setsIndex(index);
        daySummary.setUpdateTime(new Date());



        return daySummaryMapper.insert(daySummary);
    }

    @Override
    public DaySummary findByCustomerId(Integer custId) {
        return daySummaryMapper.findByCustomerId(custId);
    }

    @Override
    public int update(DaySummary daySummary) {
        return daySummaryMapper.update(daySummary);
    }

    @Override
    public int deleteByCustomerId(Integer custId) {
        return daySummaryMapper.deleteByCustomerId(custId);
    }

    @Override
    public List<DaySummary> findAllDaySummary() {
        return daySummaryMapper.findAllDaySummary();
    }

    @Override
    public List<DaySummary> findDaySummaryByPage(Integer pageNum, Integer pageSize) {

        Map<String, Integer> map = new HashMap();

        map.put("pageNum", (pageNum - 1) * pageSize);
        map.put("pageSize", pageSize);
        return daySummaryMapper.findDaySummaryByPage(map);
    }

}

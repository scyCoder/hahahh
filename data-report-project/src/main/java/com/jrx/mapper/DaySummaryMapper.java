package com.jrx.mapper;

import com.jrx.model.DaySummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DaySummaryMapper {

    /**
     * 添加日汇信息
     *
     * @param daySummary
     * @return
     */
    int insert(DaySummary daySummary);

    /**
     * 根据客户id，查询该客户的日汇信息
     *
     * @param custId
     * @return
     */
    DaySummary findByCustomerId(Integer custId);

    /**
     * 跟新客户日汇信息
     *
     * @param daySummary
     * @return
     */
    int update(DaySummary daySummary);

    /**
     * 根据客户id删除客户日汇信息
     *
     * @param custId
     * @return
     */
    int deleteByCustomerId(Integer custId);

    /**
     * 查询所有用户日汇信息
     *
     * @return
     */
    List<DaySummary> findAllDaySummary();

    /**
     * 分页查询
     *
     * @param map 封装分页需要的参数：pageNum和PageSize
     * @return
     */
    List<DaySummary> findDaySummaryByPage(Map<String, Integer> map);


}
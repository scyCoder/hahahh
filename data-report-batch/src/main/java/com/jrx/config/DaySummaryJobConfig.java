package com.jrx.config;

import com.jrx.mapper.DaySummaryMapper;
import com.jrx.model.Customer;
import com.jrx.model.DaySummary;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/16 17:46
 * @Version 1.0
 */
@Configuration
public class DaySummaryJobConfig extends BaseConfig {

    @Autowired
    private DaySummaryMapper daySummaryMapper;

    @Bean
    public Job daySummary() {
        return jobBuilderFactory.get("daySummary" + new Date())
                .start(daySummaryStep())
                .build();
    }

    @Bean
    public Step daySummaryStep() {

        return stepBuilderFactory.get("daySummaryStep")
                .<Customer, DaySummary>chunk(1)
                .reader(daySummaryReader())
                .processor(daySummaryProcessor())
                .writer(daySummaryWriter())
                .build();
    }

    @Bean
    public ItemProcessor<Customer, DaySummary> daySummaryProcessor() {
        return new ItemProcessor<Customer, DaySummary>() {
            @Override
            public DaySummary process(Customer customer) throws Exception {
                Integer custId = customer.getCustId();
                // 单笔最大值
                DaySummary daySummary = daySummaryMapper.getCurrDateData(custId);
                // 根据uuid和当前日期创建索引
                UUID uuid = UUID.randomUUID();
                LocalDate now = LocalDate.now();
                String dateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                // 创建索引
                String s_index = uuid + "_" + dateStr;
                daySummary.setCustId(custId);
                daySummary.setSurname(customer.getSurname());
                daySummary.setsIndex(s_index);
                daySummary.setUpdateTime(new Date());
                return daySummary;
            }
        };
    }

    @Bean
    public JdbcBatchItemWriter<DaySummary> daySummaryWriter() {
        JdbcBatchItemWriter<DaySummary> writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into tb_day_summary(s_index,cust_id,update_time,trans_date,surname,tran_max_amt," +
                "pay_amt,tran_cnt,pay_cnt,tran_amt) values (:sIndex,:custId," +
                ":updateTime,:transDate,:surname,:tranMaxAmt,:payAmt,:tranCnt,:payCnt,:tranAmt)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DaySummary>());
        return writer;
    }

    @Bean
    public JdbcPagingItemReader<Customer> daySummaryReader() {
        JdbcPagingItemReader reader = new JdbcPagingItemReader();
        reader.setDataSource(dataSource);
        reader.setFetchSize(1);
        // 指定SQL语句
        MySqlPagingQueryProvider sql = new MySqlPagingQueryProvider();
        sql.setSelectClause("cust_id,surname");
        sql.setFromClause("from tb_customer");
        HashMap<String, Order> hashMap = new HashMap(1);
        hashMap.put("cust_id", Order.ASCENDING);
        sql.setSortKeys(hashMap);
        reader.setQueryProvider(sql);
        reader.setRowMapper(new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer customer = new Customer();
                customer.setCustId(resultSet.getInt("cust_id"));
                customer.setSurname(resultSet.getString("surname"));
                return customer;
            }
        });
        return reader;
    }
}

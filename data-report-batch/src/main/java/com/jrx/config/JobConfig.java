package com.jrx.config;

import com.jrx.model.Customer;
import com.jrx.model.DealDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.validation.BindException;

import java.util.Date;

/**
 * 从文件中读取cus_info.txt数据和deal_detail.txt数据并且写到数据库中
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/15 11:51
 * @Version 1.0
 */
@Configuration
public class JobConfig extends BaseConfig {


    private static final String CUST_ID = "cust_id";

    /**
     * 创建读去客户信息和交易明细的数据
     *
     * @return
     */
    @Bean
    public Job readDataJobConfig() {
        return jobBuilderFactory.get("readDataJobConfig" + new Date())
                .start(readCustomerStepFlow())
                // 两个Flow中的step在各自的线程中执行
                .split(new SimpleAsyncTaskExecutor())
                .add(readDealDetailStepFlow())
                .end()
                .build();
    }

    /**
     * 读取客户信息Step
     *
     * @return
     */
    @Bean
    public Step readCustomerDataStep() {
        return stepBuilderFactory.get("readCustomerDataStep")
                .<Customer, Customer>chunk(2)
                .reader(itemReaderCustomerData())
                .writer(itemWriterCustomerData())
                .build();
    }

    /**
     * 将从文件中读取的客户信息存到数据库中
     *
     * @return
     */
    @Bean
    public ItemWriter<Customer> itemWriterCustomerData() {
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into tb_customer(cust_id,surname,gender,educa_des,mar_des,birthday,address) values " +
                "(:custId," +
                ":surname,:gender,:educaDes,:marDes,:birthday,:address)");
        // 将读取的数据与StudentMessage对象映射
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        return writer;
    }

    /**
     * 从cus_info.txt中读取客户信息
     *
     * @return
     */
    @Bean
    public FlatFileItemReader<Customer> itemReaderCustomerData() {

        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new ClassPathResource("jobfile/cus_info.txt"));
        // 跳过第一条数据
        reader.setLinesToSkip(1);
        // 创建解析数据对象
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        // 设置字段名称
        String[] str = {CUST_ID, "surname", "gender", "educa_des", "mar_des", "birthday", "address"};
        tokenizer.setNames(str);
        // 创建映射对象
        DefaultLineMapper<Customer> mapper = new DefaultLineMapper();
        mapper.setLineTokenizer(tokenizer);
        // 把解析的一行数据映射成对象
        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
            @Override
            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
                Customer customer = new Customer();
                customer.setCustId(fieldSet.readInt(CUST_ID));
                customer.setSurname(fieldSet.readString("surname"));
                customer.setGender(fieldSet.readString("gender"));
                customer.setEducaDes(fieldSet.readString("educa_des"));
                customer.setMarDes(fieldSet.readString("mar_des"));
                customer.setBirthday(fieldSet.readInt("birthday"));
                customer.setAddress(fieldSet.readString("address"));
                return customer;
            }
        });
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }

    /**
     * 读取交易明细信息Step
     *
     * @return
     */
    @Bean
    public Step readDealDetailStep() {
        return stepBuilderFactory.get("readDealDetailStep")
                .<DealDetail, DealDetail>chunk(2)
                .reader(itemReaderDealDetail())
                .writer(itemWriterDealDetail())
                .build();
    }

    /**
     * 将读取的交易明细数据，写到数据库中
     *
     * @return
     */
    @Bean
    public ItemWriter<DealDetail> itemWriterDealDetail() {

        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into tb_deal_detail(trans_id,cust_id,account,card_nbr,tranno,month_nbr,bill,trans_type," +
                "txn_datatime) values " +
                "(:transId,:custId,:account,:cardNbr,:tranno,:monthNbr,:bill,:transType,:txnDatatime)");
        // 将读取的数据与StudentMessage对象映射
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DealDetail>());
        return writer;
    }

    /**
     * 从文件中读取交易明细信息
     *
     * @return
     */
    @Bean
    public ItemReader<DealDetail> itemReaderDealDetail() {
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new ClassPathResource("jobfile/deal_detail.txt"));
        // 跳过第一条数据
        reader.setLinesToSkip(1);
        // 创建解析数据对象
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        // 设置字段名称
        String[] str = {"trans_id", CUST_ID, "account", "card_nbr", "tranno", "month_nbr", "bill",
                "trans_type", "txn_datatime"};
        tokenizer.setNames(str);
        // 创建映射对象
        DefaultLineMapper<DealDetail> mapper = new DefaultLineMapper();
        mapper.setLineTokenizer(tokenizer);
        // 把解析的一行数据映射成对象
        mapper.setFieldSetMapper(new FieldSetMapper<DealDetail>() {
            @Override
            public DealDetail mapFieldSet(FieldSet fieldSet) throws BindException {
                DealDetail dealDetail = new DealDetail();
                dealDetail.setTransId(fieldSet.readInt("trans_id"));
                dealDetail.setCustId(fieldSet.readInt(CUST_ID));
                dealDetail.setAccount(fieldSet.readString("account"));
                dealDetail.setCardNbr(fieldSet.readString("card_nbr"));
                dealDetail.setTranno(fieldSet.readInt("tranno"));
                dealDetail.setMonthNbr(fieldSet.readInt("month_nbr"));
                dealDetail.setBill(fieldSet.readBigDecimal("bill"));
                dealDetail.setTransType(fieldSet.readString("trans_type"));
                dealDetail.setTxnDatatime(fieldSet.readDate("txn_datatime"));
                return dealDetail;
            }
        });
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }

    /**
     * 创建Flow对象，包含读取客户信息数据的Step
     *
     * @return
     */
    @Bean
    public Flow readCustomerStepFlow() {
        return new FlowBuilder<Flow>("readCustomerStepFlow")
                .start(readCustomerDataStep())
                .build();
    }

    /**
     * 创建Flow对象，包含读取交易明细的Step
     *
     * @return
     */
    @Bean
    public Flow readDealDetailStepFlow() {
        return new FlowBuilder<Flow>("readDealDetailStepFlow")
                .start(readDealDetailStep())
                .build();
    }

}

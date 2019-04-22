package com.jrx.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @Author: sunchuanyin
 * @Date: 2019/4/16 21:11
 * @Version 1.0
 */
@Configuration
public class ParentJobConfig extends BaseConfig {
    /**
     * 启动对象
     */
    @Autowired
    private JobLauncher jobLauncher;

    /**
     * 父job
     *
     * @param jobRepository
     * @param transactionManager
     * @return
     */
    @Bean
    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("parentJob21")
                .start(jobConfigStep(jobRepository, transactionManager))
                .next(daySummaryJobStep(jobRepository, transactionManager))
                .build();
    }

    /**
     * 日汇统计job
     *
     * @param jobRepository
     * @param transactionManager
     * @return
     */
    @Bean
    public Step daySummaryJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        return new JobStepBuilder(new StepBuilder("daySummaryJobStep"))
                .job(new DaySummaryJobConfig().daySummary())
                // 指定启动对象，这里是启动父job
                .launcher(jobLauncher)
                // 持久化
                .repository(jobRepository)
                // 事务管理
                .transactionManager(transactionManager)
                .build();
    }

    /**
     * 读取客户和交易明细job
     *
     * @return
     */
    @Bean
    public Step jobConfigStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("jobConfigStep"))
                .job(new JobConfig().readDataJobConfig())
                // 指定启动对象，这里是启动父job
                .launcher(jobLauncher)
                // 持久化
                .repository(jobRepository)
                // 事务管理
                .transactionManager(transactionManager)
                .build();
    }
}

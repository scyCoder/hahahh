package com.jrx;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DataReportBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataReportBatchApplication.class, args);
    }

}

package com.financial.transaction.transactionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.financial.transaction.system", "com.financial.transaction.transactionsystem"})
public class TransactionManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionManagementServiceApplication.class, args);
    }
}

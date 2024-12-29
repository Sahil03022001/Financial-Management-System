package com.financial.transaction.accountsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.financial.transaction.system", "com.financial.transaction.accountsystem"})
public class AccountManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountManagementServiceApplication.class, args);
    }
}

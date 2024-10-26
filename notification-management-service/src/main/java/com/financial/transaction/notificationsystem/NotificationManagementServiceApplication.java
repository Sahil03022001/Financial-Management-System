package com.financial.transaction.notificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.financial.transaction.system.config", "com.financial.transaction.notificationsystem"})
public class NotificationManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationManagementServiceApplication.class);
    }
}

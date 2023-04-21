package com.example.shedlock;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtLeastFor = "PT20S", defaultLockAtMostFor = "PT25S")
public class SpringSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSchedulerApplication.class, args);
    }

    @Scheduled(cron = "0/30 * * * * ?")
    @SchedulerLock(name = "scheduledTask", lockAtLeastFor = "PT20S", lockAtMostFor = "PT25S")
    public void scheduledTask() {
        System.out.println("Scheduled task started. " + ZonedDateTime.now());
        try {
            Thread.sleep(15000);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.out.println("Scheduled task ended  . " + ZonedDateTime.now());
    }
}

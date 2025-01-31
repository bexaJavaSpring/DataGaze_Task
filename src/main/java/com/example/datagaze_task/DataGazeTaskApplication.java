package com.example.datagaze_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataGazeTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataGazeTaskApplication.class, args);
    }

}

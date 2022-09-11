package com.my.junitspringbootdemoapp;

import com.my.junitspringbootdemoapp.model.CollegeStudent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class JunitSpringbootDemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunitSpringbootDemoAppApplication.class, args);
    }

    @Bean(name = "collegeStudent")
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

}

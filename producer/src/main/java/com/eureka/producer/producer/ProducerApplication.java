package com.eureka.producer.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ProducerApplication {

    public static void main(String[] args) {


//        ArrayList<String> m=new ArrayList<>();
//
//        m.add("1");
//        m.add("2");
//        m.add("4");
//
//        List<String> list= Arrays.asList(new String[]{"1","2","4"});
//
//        m.add(1,3+"");
//
//        System.out.println(m);

        SpringApplication.run(ProducerApplication.class, args);
    }
}

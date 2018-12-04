package com.eureka.producer.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author huaili
 * @Date 2018/11/21 10:26
 * @Description TODO
 **/
@RestController
public class ProducerController {

    @GetMapping("/get")
    public Mono<String> getTest(String msg){
        return Mono.just("get test success msg="+msg);
    }

    @GetMapping("/get1")
    public String getTest1(String msg){
        return "get test success msg="+msg;
    }
}

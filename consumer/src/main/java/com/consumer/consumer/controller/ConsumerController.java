package com.consumer.consumer.controller;

import com.consumer.consumer.feign.testAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huaili
 * @Date 2018/11/21 10:46
 * @Description TODO
 **/
@RestController
public class ConsumerController {

    @Autowired
    testAPI testAPI;

    @GetMapping("/ctest")
    public String get(String msg){
        return testAPI.getTest(msg);
    }
}

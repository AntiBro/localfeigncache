package com.consumer.consumer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.util.*;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerApplication {

    static class TestAPP{
       private int a;
       private int b;

       public TestAPP(int a,int b){
           this.a=a;
           this.b=b;
       }
        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "TestAPP{ a="+a+"; b="+b+"}";
        }
    }



    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);

//        List list=new ArrayList(Arrays.asList(new String[]{"1","2","5","6"}));
//
//        list.add(2,10);
//        System.out.println(list);


     //   Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), handler);

//        Map<String, List<TestAPP>> maps=new HashMap<>();
//
//        List a=new ArrayList();
//
//        for(int i=0;i<2;i++){
//            a.add(new TestAPP(i,i));
//        }
//
//        maps.put("1",a);
//
//        List b= maps.get("1");
//
//        b.add(new TestAPP(3,3));
//
//        System.out.println(maps.get("1"));
//
//
//
//        Map<String,TestAPP> mapbean=new HashMap<>();
//        mapbean.put("1",new TestAPP(1,1));
//
//        TestAPP aa=mapbean.get("1");
//
//        aa.setA(2);
//        System.out.println(mapbean);

    }
}

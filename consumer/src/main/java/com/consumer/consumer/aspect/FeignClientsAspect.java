package com.consumer.consumer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author huaili
 * @Date 2018/11/21 11:01
 * @Description TODO
 **/
//@Aspect
//@Component
public class FeignClientsAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.consumer.consumer.feign.*.*(..))")
    public void allPublicMethodCall() {
        // no need to implement this pointcut
        System.out.println("cee");
    }
    @Around("allPublicMethodCall()")
    public Object logDefaultApiCallInfo(ProceedingJoinPoint pjp) throws Throwable {

        Object apiControllerCalled = pjp.getTarget();
        Class<?>[] clzzs=apiControllerCalled.getClass().getInterfaces();
        for(Class<?> clzz:clzzs){
            logger.info("invoked feign interface=["+clzz.getName()+"]");
            Annotation[] anos=clzz.getAnnotations();
            FeignClient feignClient= AnnotationUtils.findAnnotation(clzz,FeignClient.class);

            logger.info("invoked feign value =["+feignClient.value()+"]");
//            for(Annotation ano:anos){
//                if(ano.getClass().equals(FeignClient.class)){
//                    logger.info("feign value="+((FeignClient)ano).value());
//                }
//                logger.info("invoked feign annotion=["+ano+"]"+ano.getClass());
//            }

        }


        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String apiName = methodSignature.getName();

        logger.info("feign class:"+apiControllerCalled.getClass().getName());

        logger.info("feign method:"+apiName);

        Object returnValue = pjp.proceed();

        return returnValue;
    }






}

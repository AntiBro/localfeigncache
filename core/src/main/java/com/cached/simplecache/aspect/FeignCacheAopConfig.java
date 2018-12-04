package com.cached.simplecache.aspect;

import com.cached.simplecache.cache.LocalCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;


@Aspect
@Component
public class FeignCacheAopConfig {

    @Autowired
    LocalCache localCache;

    private static final String CACHE_KEY_TEMPLATE="_#className=[%s],#methodName=[%s],#paramsType=[%s],#returnType=[%s]_";

    Logger log= LoggerFactory.getLogger(getClass());


    public FeignCacheAopConfig(){
        log.info("loading FeignCacheAopConfig ");
    }


    @Pointcut("@annotation(com.cached.simplecache.annotion.FeignCache)")
    public void FeignCacheCutPoint(){

    }

    @Around("FeignCacheCutPoint()")
    public Object cachepointcut(ProceedingJoinPoint point) throws Throwable {
       // System.out.println("测试通过了feign annotion pointcut");


        String cacheKey=generateKey(point);

        Object val=localCache.getCache(cacheKey);

        if(val!=null){
            log.info("hit the cache, key=[{}],value=[{}]",cacheKey,val);
            return val;
        }

        Object retOldval=point.proceed();

        localCache.setCache(cacheKey,retOldval);

        System.out.println("测试通过了feign annotion pointcut");

        return retOldval;

    }

    /**
     * 根据切面函数来构造cache的map
     * @return
     */
    private String generateKey(ProceedingJoinPoint point){
        Object target=point.getTarget();
        String calledClzzName=target.getClass().getName();
        MethodSignature siguration=(MethodSignature)point.getSignature();


        String methodName=siguration.getMethod().getName();
        String retTypeName=siguration.getReturnType().getName();
        String argsParamsName=generateArgsKeyName(point);

        String cachekey=String.format(CACHE_KEY_TEMPLATE,calledClzzName,methodName,argsParamsName,retTypeName);





        log.info("feign cached key=[{}]",cachekey);

        return cachekey;
    }

    private String generateArgsKeyName(ProceedingJoinPoint point){
        Object[] argsObjects=point.getArgs();
        StringBuffer argsStrBuf=new StringBuffer("");
        Stream.of(argsObjects).forEach(argsObject ->{
            argsStrBuf.append(argsObject.getClass().getName());
        });

        return argsStrBuf.toString();
        }
//
//    @Pointcut("execution(* com.consumer.consumer.controller.*.*(..))")
//    public void controllpoint(){}
//
//
//    @Around("controllpoint()")
//    public Object controllerpoint(ProceedingJoinPoint point) throws Throwable {
//
//      //  System.out.println("执行器的point");
//
//        Object retval=point.proceed();
//
//        return retval;
//
//    }
}

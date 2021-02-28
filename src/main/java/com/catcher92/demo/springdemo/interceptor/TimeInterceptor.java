package com.catcher92.demo.springdemo.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public class TimeInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void before(JoinPoint point){
        String methodName = getMethodName(point);
        logger.warn("*************************** {} start ***************************", methodName);
    }

    public void after(JoinPoint point){
        String methodName = getMethodName(point);
        logger.warn("*************************** {} end ***************************", methodName);
    }

    public Object aroundByWatch(ProceedingJoinPoint jpoint){
        Object obj = null;
        Signature signature = jpoint.getSignature();
        String name = getMethodName(jpoint);
        logger.warn("************************************** start {}  **************************************", name);

        StopWatch stopWatch = new StopWatch("Controller");
        if (stopWatch.isRunning()) {
            stopWatch.stop();
        }
        stopWatch.start(signature.toString());

        try {
            obj = jpoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
                logger.warn(stopWatch.prettyPrint() + "\n************************************** end {}  **************************************", name);
            }
        }
        return obj;
    }

    public Object around(ProceedingJoinPoint jpoint){
        Object obj = null;
        try {
            Long startTime = System.currentTimeMillis();
            Object[] args = jpoint.getArgs();
            obj = jpoint.proceed(args);
            Long endTime = System.currentTimeMillis();
            String methodName = getMethodName(jpoint);
            logger.warn(new StringBuilder(methodName).append("耗时：").append(endTime-startTime).append("毫秒").toString());
        } catch (Throwable e) {
            logger.error(getClass().toString() + " around afterThrowing show");
        }
        return obj;
    }

    private String getMethodName(JoinPoint jpoint){
        return jpoint.getSignature().getDeclaringTypeName()+"."+jpoint.getSignature().getName()+"()";
    }
}
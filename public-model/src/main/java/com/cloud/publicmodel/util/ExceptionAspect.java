package com.cloud.publicmodel.util;

import com.cloud.publicmodel.StaticConstants;
import com.cloud.publicmodel.contans.ExceptionConstants;
import com.cloud.publicmodel.entity.response.ExceptionResponse;
import com.cloud.publicmodel.exception.ConcurrentExcessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.Method;
import java.util.Calendar;

@Aspect
@Configuration
public class ExceptionAspect {
    @Pointcut("execution(public * com.cloud..*(..))")
    public void log(){}

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 日志插入
     */
    @AfterThrowing(pointcut = "log()",throwing = "ex") //pointcut = "log()",throwing = "ex"
    public void doAfterThrowing(Throwable ex){
        if (ex != null){
            mongoTemplate.insert(new ExceptionResponse(
                    Calendar.getInstance().getTime().toString(),
                    ex.getCause() != null?ex.getCause().toString():"",
                    ()->{
                        StringBuffer buffer = new StringBuffer();
                        for (StackTraceElement element : ex.getStackTrace()){
                            buffer.append("\r\n\t").append(element);
                        }
                        return buffer.toString();
                    },
                    ex.getClass().toString()
            ), StaticConstants.LOGS_EXCEPTION);
        }
    }

    /**
     * 从搜索方法的并发数量,来判断当前是否应该出现熔断
     */
    @Pointcut("execution(public * com.cloud..controller.CommodityController.search(String))")
    public void filter(){}


    @Around(value = "filter()")
    public synchronized Object setConcurrentNum(ProceedingJoinPoint pjd) throws Throwable {
        if (ConcurrentAction.getSearchConcurrentNumber().get()<1){
            ConcurrentAction.getSearchConcurrentNumber().incrementAndGet();
            Object re = pjd.proceed();
            ConcurrentAction.getSearchConcurrentNumber().decrementAndGet();
            return re;
        }else{
            Method m = pjd.getTarget().
                    getClass().
                    getDeclaredMethod(pjd.getSignature().getName(), Class.class);
            m.setAccessible(true);
            Object re  = m.invoke(pjd.getTarget().getClass().newInstance(),ConcurrentExcessException.class);
            return re;
        }
    }
}

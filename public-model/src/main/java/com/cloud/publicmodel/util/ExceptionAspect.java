package com.cloud.publicmodel.util;

import com.cloud.publicmodel.StaticConstants;
import com.cloud.publicmodel.entity.response.ExceptionResponse;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

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
        mongoTemplate.insert(new ExceptionResponse(
                Calendar.getInstance().getTime().toString(),
                ex.getCause().toString(),
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

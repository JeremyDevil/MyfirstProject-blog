package com.zyx.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.ResourceHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.zyx.blog.web.*.*(..))")
    public  void log(){

    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod =joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestlLog requestlLog = new RequestlLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestlLog);
    }
    @After("log()")
    public void doAfter(){

    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void  doAfterReturn(Object result){
        logger.info("Result : {}",result);
    }
    private class RequestlLog{
        private String url;
        private String ip;
        public String ClassMethod;
        private Object[] args;

        public RequestlLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            ClassMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestlLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", ClassMethod='" + ClassMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}

package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingAOP {

    @Pointcut("execution(* org.example.expert.domain.comment.controller..*(..))")
    public void commentControllerMethod() {
    }

    @Pointcut("execution(* org.example.expert.domain.user.controller..*(..))")
    public void userControllerMethod() {
    }

    @Around("commentControllerMethod() || userControllerMethod()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("AOP METHOD: " + request.getMethod());
        log.info("AOP URI: " + request.getRequestURI());

        log.info("AOP");

        Object result = joinPoint.proceed();

        log.info("AOP 2");

        return result;
    }
}

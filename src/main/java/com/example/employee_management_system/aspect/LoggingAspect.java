package com.example.employee_management_system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.employeemanagement.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Calling method: " + joinPoint.getSignature());
    }
}


package com.mssample.cart.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class EntryExitLogger {

	@Around("@annotation(EnableLogging)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	    log.debug("Entering " + joinPoint.getSignature() + " with arguments" + joinPoint.getArgs());
	    Object proceed = joinPoint.proceed();
	    long executionTime = System.currentTimeMillis() - start;
	    log.debug("Exiting " + joinPoint.getSignature() +  " result =" + proceed + " which was executed in " + executionTime + "ms");
	    return proceed;
	}
}

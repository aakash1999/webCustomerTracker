package com.crm.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.crm.controllers.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.crm.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.crm.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage()||forDaoPackage()||forServicePackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String theMethod = joinPoint.getSignature().toString();
		myLogger.info("=======> Before execution of the method : " + theMethod);
		
		//display the arguments in the methods
		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			myLogger.info("=======> arguments in the method : " + object);
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()",returning = "theResult")
	private void after(JoinPoint joinPoint, Object theResult) {
		String theMethod = joinPoint.getSignature().toString();
		myLogger.info("=======> After execution of the method : " + theMethod);
		
		myLogger.info("=======>The returned value from the method is : " + theResult);
	}
	
	
	
}

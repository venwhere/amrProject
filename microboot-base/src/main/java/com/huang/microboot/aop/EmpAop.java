package com.huang.microboot.aop;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class EmpAop {
	private Logger logger=LoggerFactory.getLogger(EmpAop.class);
	@Around("execution(* org.huang.service..*.*(..))")
	public Object aroundInvoke(ProceedingJoinPoint joinpoint) throws Throwable {
		logger.info("方法执行前的参数："+Arrays.toString(joinpoint.getArgs()));
		Object result=joinpoint.proceed();
		logger.info("方法执行后返回值："+result);
		return result;
	}

}

package com.douzone.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExceptionTimeAspect {
	
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();

		Object result = pjp.proceed();

		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		String task = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName();

		System.out.println("[Execution Time] [" + task + "] " + totalTime + "mills");
		
		return result;
	}
}

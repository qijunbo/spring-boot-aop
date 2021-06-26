package com.example.aopdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
	
	@Pointcut("execution(* com.example.aopdemo.GreetingController.greeting(..))")
	 public  void pointcut() {
		 
	 }
	
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("【前置通知】");
        System.out.println("\tkind=" + joinPoint.getKind());
    }
 

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("【后置通知】");
        System.out.println("\tkind=" + joinPoint.getKind());
    }
    
    @AfterThrowing(value = "pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("【@AfterThrowing】");
        System.out.println("\tkind=" + joinPoint.getKind());
    }
    
    @AfterReturning(value = "pointcut()")
    public void afterReturning(JoinPoint joinPoint)  {
    	Object[] args = joinPoint.getArgs();
 
        System.out.println("【@AfterReturning】");
        
    	for(Object o : args) {
    		System.out.println(o.toString());
    	}
        System.out.println("\tkind=" + joinPoint.getKind());
        
    }
    
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint  joinPoint) throws Throwable {
    	
    	Object[] args = joinPoint.getArgs();

    	System.out.println("【@Around】");
    	
    	for(Object o : args) {
    		System.out.println(o.toString());
    	}
        System.out.println("\tkind=" + joinPoint.getKind());
        
        return joinPoint.proceed(new String[] {"something new !"});
    }
 

}

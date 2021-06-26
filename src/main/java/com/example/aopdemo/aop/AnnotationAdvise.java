package com.example.aopdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.aopdemo.service.SimpleService;

@Aspect
@Component
public class AnnotationAdvise {

	@Autowired
	private ApplicationContext context;

	@Around("@annotation(com.example.aopdemo.aop.ImportValidation)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		Object[] args = joinPoint.getArgs();

		System.out.println("【@Around】");

		for (Object o : args) {
			System.out.println(o.toString());
		}

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		System.out.println("\tparm=" + signature.getName());

		ImportValidation annotation = signature.getMethod().getAnnotation(ImportValidation.class);

		Class[] services = annotation.services();

		for (Class serviceName : services) {
			 try {
				 // 这个地方可能什么都获取不到, 就会抛出BeansException
				Object bean = context.getBean(serviceName);
				if (bean instanceof SimpleService) {
					SimpleService service = (SimpleService) bean;
					return joinPoint.proceed(new String[] { service.demo() });
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}  
		}

		return joinPoint.proceed(services);
	}

}

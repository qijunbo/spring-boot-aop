package com.example.aopdemo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.aopdemo.service.SimpleService;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ImportValidation {

	  String value() default "";
	  
	  Class[] services() default  SimpleService.class ;
}

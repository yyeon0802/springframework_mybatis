
package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
//@Service
//@Aspect //Aspect = Pointcut + Advice
public class LogAdvice {
	
	// pointcut을 컨테이너에서 어노테이션으로 인식
	//@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	//public void allPointcut() {}
	
	//@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	//public void getPointcut() {}
	
	//@Before("allPointcut()")
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}

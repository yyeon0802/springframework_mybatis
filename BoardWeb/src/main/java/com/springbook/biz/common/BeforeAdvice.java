package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

//@Service
//@Aspect

public class BeforeAdvice {

	// pointcut을 컨테이너에서 어노테이션으로 인식
//	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
//	public void allPointcut() {
//	}
//	@Before("allPointcut()")
	
	//외부 객체(PointcutCommon) 사용하는 pointcut
	
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {

		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();

		System.out.println("[사전 처리]" + method + "() 메소드 ARGS 정보 : " + args[0].toString());
	}
}
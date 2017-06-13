package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RuntimeCheckAspect {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(RuntimeCheckAspect.class);
	
	// PointCut
	// public 리턴타입 아무거나 컨트롤러에 있는 모든 메소드의 매개변수는 상관없다.
	//  PointCut에 지정된 위치에서 Advice실행.
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam12DBController.*(..))")
	private void runtimeCheckMethod() {
		
	}
	
	// Advice
	//Around라는 Advice를 적용할 메소드 지정. runtimeCheckMethod()를 타고 들어가서  
	//execution(public * com.mycompany.myapp.cotroller.Exam12DBController.*(..))"위치에서 실행됨.
	@Around("runtimeCheckMethod()")
	public Object runtimeCheckAdivce(ProceedingJoinPoint joinPoint)throws Throwable{
		//before code
		long startTime= System.nanoTime();
		
		Object result=joinPoint.proceed();		//PointCut 위치에 있는 메소드를 실행함.
		
		
		//after code
		long endTime=System.nanoTime();
		long time=endTime-startTime;
		
		String realMethod=joinPoint.getSignature().toShortString();
		LOGGER.info(realMethod+ "실행시간 : " + time);
		
		return result;
		
		
	}
}

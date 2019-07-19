package com.lili.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 */
@Component
@Aspect
public class LogAspect {
    //定义切入点
    //切入点表达式： public 返回值类型   包名.类名.方法名(参数列表)
    @Pointcut("execution( * com.lili.Service.impl.*.*(..))")
    public  void  test(){}



/*    @Before("test()")
    public  void  before(JoinPoint joinPoint){
    String name= joinPoint.getSignature().getName();
    String str=(String)joinPoint.getArgs()[0];
        System.out.println(name);
        System.out.println(str);
        System.out.println("=======前置通知======");
    }
    @After("test()")
    public  void  after(){
        System.out.println("=======后置通知======");
    }
    @AfterReturning("test()")
    public  void  afterReturning(){
        System.out.println("=======返回后通知======");
    }
    @AfterThrowing("test()")
    public  void  throwing(){
        System.out.println("=======抛出异常通知======");
    }*/



    @Around("test()")
    public  Object  around(ProceedingJoinPoint joinPoint){


        Object o=null;
        try {
            System.out.println("=====前置通知====");
            o=joinPoint.proceed();
            System.out.println("====返回后通知===");
        } catch (Throwable throwable) {
            // throwable.printStackTrace();
            System.out.println("=======抛出异常通知=======");
        } finally {
            System.out.println("====后置通知fff===");
        }
        System.out.println("====后置通知===");
        return o;

    }
}

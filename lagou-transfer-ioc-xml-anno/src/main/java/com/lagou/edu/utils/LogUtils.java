package com.lagou.edu.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* com.lagou.edu.service.impl.TransferServiceImpl.*(..))")
    public void pt1() {
    }

    /**
     * 业务逻辑开始之前执行
     */
    @Before("pt1()")
    public void beforeMethod(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            Object arg = args[i];
//            System.out.println(arg);
//        }
        System.out.println("前置通知before，业务逻辑开始执行之前执行.......");
    }

    /**
     * 业务逻辑结束时执行（无论异常与否）
     */
    @After("pt1()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("最终通知after，业务逻辑结束时执行，无论异常与否都执行.......joinPoint: " + joinPoint);
    }

    /**
     * 业务逻辑正常时执行
     */
    @AfterReturning(value = "pt1()",returning = "retVal")
    public Object successMethod(Object retVal) {
        System.out.println("后置通知after-returning，业务逻辑正常时执行.......retVal: " + retVal);
        return retVal;
    }

    /**
     * 异常时执行
     */
    @AfterThrowing(value = "pt1()", throwing = "throwable", argNames = "joinPoint,throwable")
    public void exceptionMethod(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("异常通知after-throwing，异常时执行.......joinPoint: " + joinPoint + " throwable: " + throwable);
    }




    /**
     * 环绕通知
     */
//    @Around("pt1()")
    public Object arroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知around中的beforemethod，类似前置通知@Before....proceedingJoinPoint: "+ proceedingJoinPoint);

        Object result = null;
        try {
            try {
                // 控制原有业务逻辑是否执行，如果注释掉这句，就不执行了
                result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()); // 类似 method.invoke
            } finally {
                System.out.println("环绕通知中的afterMethod，类似最终通知@After....");
            }
            System.out.println("环绕通知中的successMethod，类似后置通知@AfterReturning....");
        } catch (Exception e) {
            System.out.println("环绕通知中的exceptionmethod，类似异常通知@AfterThrowing....");
        }
        System.out.println("环绕通知around的方法返回值，result: "+ result);

        return result;
    }
}

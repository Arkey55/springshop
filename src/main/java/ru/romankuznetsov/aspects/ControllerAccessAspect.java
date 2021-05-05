package ru.romankuznetsov.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAccessAspect {
    @Before("execution(public String ru.romankuznetsov.beans.JwtTokenUtil.generateToken(..))")
    public void logTokenCreationRequest() {
        System.out.println("Запрос на создание токена");
    }

    @AfterReturning(
            pointcut = "execution(public String ru.romankuznetsov.beans.JwtTokenUtil.generateToken(..))",
            returning = "result")
    public void logTokenCreationResponse(String result) {
        System.out.println("Создан Token: " + result);
    }

    @Before("execution(public * ru.romankuznetsov.controllers.AuthController.createToken(..))")
    public void logAuthRequest(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("В AuthController вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Аргументы:");
            for (Object o : args) {
                System.out.println(o);
            }
        }
    }
}

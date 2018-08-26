package com.jt61016.exceptionHandler.alert.service;

import com.google.common.base.Throwables;
import com.jt61016.exceptionHandler.alert.constant.AlertTypeEnum;
import com.jt61016.exceptionHandler.alert.dto.AlertPoint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author jiangtao
 * @Date 2018/8/26 下午2:37.
 */
@Component
@Slf4j
@Aspect
public class ServiceExceptionHandler {

    @Autowired
    AlertService alertService;

    @Pointcut(value = "execution(public * com.jt61016..*.service..*.*(..))")
    private void servicePointCut() {
    }

    @Around(value = "servicePointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            processException(pjp, args, throwable);
            return null;
        }
    }

    private void processException(final ProceedingJoinPoint joinPoint, final Object[] args, Throwable throwable) {
        String inputParam = "";
        if (Objects.nonNull(args) && 0 < args.length) {
            StringBuilder sb = new StringBuilder(126);
            for (Object arg : args) {
                sb.append(",");
                sb.append(arg);
            }
            inputParam = sb.toString();
        }
        String errorStack = Throwables.getStackTraceAsString(throwable);
        log.warn("出错的方法定义: {}\n 实际入参:({})\n 详细错误:{}", joinPoint.toLongString(), inputParam, errorStack);
        System.out.println("出错的方法定义: " + joinPoint.toLongString() + "\n 实际入参:("+ inputParam + ")\n 详细错误:" + errorStack);

        collectException(joinPoint, throwable);
    }

    private void collectException(final ProceedingJoinPoint joinPoint, Throwable throwable) {
        Class bussissClass = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        AlertPoint alertPoint = new AlertPoint(bussissClass.getName(), method.getName(),
                throwable.getClass().getName(), throwable.getMessage(), AlertTypeEnum.GENEAL_EXCEPTION);
        this.alertService.collect(alertPoint);
    }
}

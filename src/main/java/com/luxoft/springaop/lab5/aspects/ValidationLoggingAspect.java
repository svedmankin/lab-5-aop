package com.luxoft.springaop.lab5.aspects;

import java.util.logging.Logger;

import com.luxoft.springaop.lab5.exceptions.ValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ValidationLoggingAspect {
    private final static Logger LOG = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* *.setAge(..))")
    public void setAgeMethod() {
    }

    @AfterThrowing(pointcut="setAgeMethod()", throwing="e")
    public void validationExceptionLogger(JoinPoint joinPoint,
                                          ValidationException e) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        StringBuilder args = new StringBuilder();
        for (Object arg : methodArgs) {
            if (args.length() > 0)
                args.append(", ");
            args.append(arg.toString());
        }

        LOG.severe("ValidationException in method " + methodName + " with args " + args);
    }
}

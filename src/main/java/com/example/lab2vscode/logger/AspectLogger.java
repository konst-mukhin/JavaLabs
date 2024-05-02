package com.example.lab2vscode.logger;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("execution(public * com.example.lab2vscode.controller.*.*(..))")
  public void callAtController() {}

  @After("callAtController()")
  public void afterMethodCall(final JoinPoint jp) {
    String args =
        Arrays.stream(jp.getArgs()).map(arg -> arg.toString()).collect(Collectors.joining(","));

    if (logger.isInfoEnabled()) {
      logger.info("After({} args[{}])", jp.getSignature().getName(), args);
    }
  }

  @AfterReturning(value = "callAtController()")
  public void afterMethodReturn(final JoinPoint jp) {
    if (logger.isInfoEnabled()) {
      logger.info("After returning({})", jp.getSignature().getName());
    }
  }

  @AfterThrowing(value = "callAtController()", throwing = "ex")
  public void afterMethodThrow(final JoinPoint jp, final Throwable ex) {
    if (logger.isInfoEnabled()) {
      logger.error(
          "After Throwing({})" + " exception({})", jp.getSignature().getName(), ex.getMessage());
    }
  }
}

package com.epam.adok.core.annotation.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class LoggingInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object logAll(InvocationContext context) throws Exception {

        String methodName = context.getMethod().getName();

        log.info("Entering '{}' method...", methodName);

        Object[] parameters = context.getParameters();

        if (parameters.length != 0) {
            log.info("Input parameters : ");
            for (Object parameter : parameters) {
                log.info(" ---> {}", parameter);
            }
        }
        Object proceed = context.proceed();

        log.info("Method '{}' returns '{}'.", methodName, proceed);

        return proceed;
    }
}

package br.com.box.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

public class InterceptadorExcecaoController implements Serializable {

    private static final long serialVersionUID = -1127901267196070851L;

    private Logger log = Logger.getLogger(InterceptadorExcecaoController.class);

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Throwable {
        log = Logger.getLogger(context.getClass());
        try {
            return context.proceed();
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}

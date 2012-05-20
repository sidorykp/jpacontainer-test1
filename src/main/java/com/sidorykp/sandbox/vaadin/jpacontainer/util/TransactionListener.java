package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 5/20/12
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TransactionListener implements ApplicationContext.TransactionListener {
    protected static final Logger log = LoggerFactory.getLogger(TransactionListener.class);

    @Override
    public void transactionStart(Application application, Object o) {
        log.debug("transactionStart");
    }

    @Override
    public void transactionEnd(Application application, Object o) {
        log.debug("transactionEnd");
    }
}

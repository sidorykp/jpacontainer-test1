package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/1/12
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware
{
    public static ApplicationContext ctx;

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        ctx = applicationContext;
    }

}

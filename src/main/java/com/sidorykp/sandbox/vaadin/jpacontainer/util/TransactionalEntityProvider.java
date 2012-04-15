package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.vaadin.addon.jpacontainer.provider.MutableLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.HibernateLazyLoadingDelegate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/1/12
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionalEntityProvider<T> extends MutableLocalEntityProvider<T> {

    @PersistenceContext
    private EntityManager em;

    public TransactionalEntityProvider(Class<T> entityClass) {
        super(entityClass);
    }

    @PostConstruct
    public void init() {
        setEntityManager(em);
        // NOTE it REALLY works
        setLazyLoadingDelegate(new HibernateLazyLoadingDelegate());
    }
}

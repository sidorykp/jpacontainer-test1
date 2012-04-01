package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.vaadin.addon.jpacontainer.provider.MutableLocalEntityProvider;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        setTransactionsHandledByProvider(false);
    }

    @PostConstruct
    public void init() {
        setEntityManager(em);
        setEntitiesDetached(false);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    protected void runInTransaction(Runnable operation) {
        super.runInTransaction(operation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T updateEntity(final T entity) {
        return super.updateEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T addEntity(final T entity) {
        return super.addEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeEntity(final Object entityId) {
        super.removeEntity(entityId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEntityProperty(final Object entityId, final String propertyName, final Object propertyValue)
            throws IllegalArgumentException {
        super.updateEntityProperty(entityId, propertyName, propertyValue);
    }
}

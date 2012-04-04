package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/1/12
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionalCachedEntityProvider<T> extends CachingMutableLocalEntityProvider<T> {

    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    private EntityManager em;

    public TransactionalCachedEntityProvider(Class<T> entityClass) {
        super(entityClass);
    }

    @PostConstruct
    public void init() {
        setEntityManager(em);
        setTransactionsHandledByProvider(false);
        setEntitiesDetached(false);
        setCacheEnabled(true);
        setEntityCacheMaxSize(10000);
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

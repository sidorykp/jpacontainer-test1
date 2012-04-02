package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.sidorykp.sandbox.vaadin.jpacontainer.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/2/12
 * Time: 1:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SampleDataProviderImpl implements SampleDataProvider {
    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public void prepareSampleData() {
        long size = (Long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
        if (size == 0) {
            for (int i = 1; i <= 10; ++ i) {
            // create two Person objects as test data
            Person boss = new Person();
            boss.setFirstName("John " + i);
            boss.setLastName("Bigboss");
            boss.setCity("Turku");
            boss.setPhoneNumber("+358 02 555 221");
            boss.setZipCode("20200");
            boss.setStreet("Ruukinkatu 2-4");
            em.persist(boss);

            Person p = new Person();
            p.setFirstName("Marc " + i);
            p.setLastName("Hardworker");
            p.setCity("Turku");
            p.setPhoneNumber("+358 02 555 222");
            p.setZipCode("20200");
            p.setStreet("Ruukinkatu 2-4");
            p.setBoss(boss);
            em.persist(p);
            }
        }
    }
}

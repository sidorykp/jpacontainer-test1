package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.sidorykp.sandbox.vaadin.jpacontainer.domain.Person;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/1/12
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PersonEntityProvider extends TransactionalEntityProvider<Person> {
    protected PersonEntityProvider() {
        super(Person.class);
    }
}

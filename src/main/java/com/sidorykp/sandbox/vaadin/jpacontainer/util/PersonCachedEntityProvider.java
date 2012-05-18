package com.sidorykp.sandbox.vaadin.jpacontainer.util;

import com.sidorykp.sandbox.vaadin.jpacontainer.domain.PersonCached;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/3/12
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("personCached")
@Scope("session")
public class PersonCachedEntityProvider extends TransactionalCachedEntityProvider<PersonCached>{
    protected PersonCachedEntityProvider() {
        super(PersonCached.class);
    }
}

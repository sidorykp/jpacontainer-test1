package com.sidorykp.sandbox.vaadin.jpacontainer.ui;

import com.sidorykp.sandbox.vaadin.jpacontainer.domain.Person;
import com.sidorykp.sandbox.vaadin.jpacontainer.domain.PersonCached;
import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.data.Property;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/1/12
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
@Scope("session")
public class AutoCrudViews extends Window {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    @Qualifier("person")
    protected EntityProvider<?> epPerson;

    @Autowired
    @Qualifier("personCached")
    protected EntityProvider<?> epPersonCached;

    public AutoCrudViews() {

    }

    @PostConstruct
    public void SetUp() {
        final HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        Tree navTree = new Tree();
        navTree.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                BasicCrudView cv = (BasicCrudView) event.getProperty()
                        .getValue();
                cv.refreshContainer();
                horizontalSplitPanel.setSecondComponent(cv);
            }
        });
        navTree.setSelectable(true);
        navTree.setNullSelectionAllowed(false);
        navTree.setImmediate(true);

        horizontalSplitPanel.setSplitPosition(200,
                HorizontalSplitPanel.UNITS_PIXELS);
        horizontalSplitPanel.addComponent(navTree);
        setContent(horizontalSplitPanel);

        // add a basic crud view for all entities known by the JPA
        // implementation, most often this is not desired and developers
        // should just list those entities they want to have editors for
        Metamodel metamodel = em.getEntityManagerFactory().getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();
        for (EntityType<?> entityType : entities) {
            Class<?> javaType = entityType.getJavaType();
            BasicCrudView view = null;
            if(javaType == Person.class) {
                view = new BasicCrudView(javaType, epPerson);
            } else if (javaType == PersonCached.class) {
                view = new BasicCrudView(javaType, epPersonCached);
            }
            navTree.addItem(view);
            navTree.setItemCaption(view, view.getCaption());
            navTree.setChildrenAllowed(view, false);
            if(javaType == Person.class || javaType == PersonCached.class) {
                view.setVisibleTableProperties("firstName","lastName", "boss");
                view.setVisibleFormProperties("firstName","lastName", "phoneNumber", "addresses", "boss");
            }

        }

        // select first entity view
        navTree.setValue(navTree.getItemIds().iterator().next());
    }
}

package com.sidorykp.sandbox.vaadin.jpacontainer.ui;

import com.sidorykp.sandbox.vaadin.jpacontainer.domain.Person;
import com.sidorykp.sandbox.vaadin.jpacontainer.domain.PersonCached;
import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.util.EntityManagerPerRequestHelper;
import com.vaadin.data.Property;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Button;
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

    @Autowired
    protected EntityManagerPerRequestHelper emHelper;

    public static final String APP_CLOSE = "APP_CLOSE";

    public static final String ERROR_BUTTON = "ERROR_BUTTON";

    public static int instanceCount = 0;

    public AutoCrudViews() {
        instanceCount ++;
    }

    @PostConstruct
    public void SetUp() {
        this.setCaption("Main Window #" + instanceCount);
        final HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        Tree navTree = new Tree();
        navTree.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() instanceof  BasicCrudView) {
                    BasicCrudView cv = (BasicCrudView) event.getProperty()
                            .getValue();
                    try {
                        cv.refreshContainer();
                    } catch (Exception e) {
                        // NOTE it occurs when a second user starts his application
                        e.printStackTrace();
                    }
                    horizontalSplitPanel.setSecondComponent(cv);
                } else if (event.getProperty().getValue() instanceof  Button) {
                    String buttonData = (String) ((Button) event.getProperty().getValue()).getData();
                    if (APP_CLOSE.equals(buttonData)) {
                        getApplication().close();
                    } else if (ERROR_BUTTON.equals(buttonData)) {
                        // NOTE this exception is just "visible" on the server as com.vaadin.event.ListenerMethod$MethodException, it does not get to the client
                        throw new RuntimeException("Pressing the button has thrown an exception");
                    }
                } else if (event.getProperty().getValue() instanceof  VisuallyDesigned) {
                	VisuallyDesigned vd = (VisuallyDesigned) event.getProperty().getValue();
                	horizontalSplitPanel.setSecondComponent(vd);
                }
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
                view = new BasicCrudView(javaType, epPerson, emHelper);
            } else if (javaType == PersonCached.class) {
                view = new BasicCrudView(javaType, epPersonCached, emHelper);
            } else {
                continue;
            }
            navTree.addItem(view);
            navTree.setItemCaption(view, view.getCaption());
            navTree.setChildrenAllowed(view, false);
            if(javaType == Person.class || javaType == PersonCached.class) {
                view.setVisibleTableProperties("firstName","lastName", "boss");
                view.setVisibleFormProperties("firstName","lastName", "phoneNumber", "addresses", "boss");
            }

        }

        Button button = new Button();
        button.setData(APP_CLOSE);
        navTree.addItem(button);
        navTree.setItemCaption(button, "Close application");
        navTree.setChildrenAllowed(button, false);

        button = new Button();
        button.setData(ERROR_BUTTON);
        navTree.addItem(button);
        navTree.setItemCaption(button, "Throw exception");
        navTree.setChildrenAllowed(button, false);
        
        VisuallyDesigned vd = new VisuallyDesigned();
        navTree.addItem(vd);
        navTree.setItemCaption(vd, "Visually designed");
        navTree.setChildrenAllowed(vd, false);

        // select first entity view
        navTree.setValue(navTree.getItemIds().iterator().next());
    }
}

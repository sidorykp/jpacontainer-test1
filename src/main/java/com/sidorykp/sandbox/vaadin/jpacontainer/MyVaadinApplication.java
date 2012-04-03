package com.sidorykp.sandbox.vaadin.jpacontainer;

import com.sidorykp.sandbox.vaadin.jpacontainer.ui.AutoCrudViews;

import com.sidorykp.sandbox.vaadin.jpacontainer.util.ApplicationContextProvider;
import com.sidorykp.sandbox.vaadin.jpacontainer.util.SampleDataProvider;
import com.vaadin.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application {
    @Autowired
    protected AutoCrudViews window;

    @Autowired
    protected SampleDataProvider sampleDataProvider;

	@Override
	public void init() {
        sampleDataProvider.prepareSampleData();
		setMainWindow(window);
	}

}

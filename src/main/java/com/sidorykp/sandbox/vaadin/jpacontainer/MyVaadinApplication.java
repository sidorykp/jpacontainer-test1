package com.sidorykp.sandbox.vaadin.jpacontainer;

import com.sidorykp.sandbox.vaadin.jpacontainer.ui.AutoCrudViews;

import com.sidorykp.sandbox.vaadin.jpacontainer.util.SampleDataProvider;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Configurable
public class MyVaadinApplication extends Application implements HttpServletRequestListener {
    @Autowired
    protected AutoCrudViews window;

    @Autowired
    protected SampleDataProvider sampleDataProvider;

    protected static final Logger log = LoggerFactory.getLogger(MyVaadinApplication.class);

	@Override
	public void init() {
        sampleDataProvider.prepareSampleData();
		setMainWindow(window);
	}

    @Override
    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        WebApplicationContext context = (WebApplicationContext) getContext();
        log.debug("context: " + context);
    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

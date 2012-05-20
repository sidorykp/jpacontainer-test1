package com.sidorykp.sandbox.vaadin.jpacontainer;

import com.sidorykp.sandbox.vaadin.jpacontainer.ui.AutoCrudViews;

import com.sidorykp.sandbox.vaadin.jpacontainer.util.ErrorCode;
import com.sidorykp.sandbox.vaadin.jpacontainer.util.SampleDataProvider;
import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.util.EntityManagerPerRequestHelper;
import com.vaadin.service.ApplicationContext;
import com.vaadin.terminal.Terminal;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Window;
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
public class MyVaadinApplication extends Application implements HttpServletRequestListener, ApplicationContext.TransactionListener {
    @Autowired
    protected AutoCrudViews window;

    @Autowired
    protected SampleDataProvider sampleDataProvider;

    protected static final Logger log = LoggerFactory.getLogger(MyVaadinApplication.class);

    @Autowired
    protected EntityManagerPerRequestHelper emHelper;

	@Override
	public void init() {
        log.debug("application init");
        getContext().addTransactionListener(this);
        sampleDataProvider.prepareSampleData();
        window.prepareGui();
		setMainWindow(window);
        setTheme("jpacontainer-test1theme");
	}

    @Override
    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        WebApplicationContext context = (WebApplicationContext) getContext();
        log.debug("context: " + ((context == null) ? "null" : context.hashCode()));
        if (emHelper != null) {
            log.debug("requestStart");
            // NOTE this REALLY works
            emHelper.requestStart();
        }
    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        if (emHelper != null) {
            log.debug("requestEnd");
            // NOTE this REALLY works
            emHelper.requestEnd();
        }
    }
    @Override
    public void transactionStart(Application application, Object o) {
        log.debug("transactionStart");
    }

    @Override
    public void transactionEnd(Application application, Object o) {
        log.debug("transactionEnd");
    }

    @Override
    // NOTE it handles both internal Vaadin errors and application errors
    public void terminalError(Terminal.ErrorEvent event) {
        window.showNotification("An internal error has occurred, please " +
                "contact the administrator!", Window.Notification.TYPE_ERROR_MESSAGE);

        // print the error
        log.error(ErrorCode.GENERAL_UNCAUGHT.toString(), event.getThrowable());
    }

    // Override the default implementation
    public static SystemMessages getSystemMessages() {
        CustomizedSystemMessages messages =
                new CustomizedSystemMessages();
        messages.setCookiesDisabledCaption("Cookies are disabled");
        messages.setCookiesDisabledMessage("Oh no, your browser does not allow cookies");
        messages.setCookiesDisabledNotificationEnabled(true);
        //messages.setCookiesDisabledURL("http://www.vaadin.com");
        return messages;
    }
}

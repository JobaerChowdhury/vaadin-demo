package com.cefalo.vaadin;

import com.cefalo.vaadin.view.HelpView;
import com.cefalo.vaadin.view.ReportView;
import com.cefalo.vaadin.view.StartView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.cefalo.vaadin.MyAppWidgetset")
public class MyUI extends UI {

    Navigator navigator;
    public static final String MAINVIEW = "help";
    public static final String REPORTVIEW = "report";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CssLayout topPanel = getTopPanel();
        CssLayout contentPanel = getContentPanel();
        Component leftPanel = getLeftPanel();

        CssLayout layout = new CssLayout();
        layout.setStyleName("main-layout");

        leftPanel.setWidth(15, Unit.PERCENTAGE);
        layout.addComponent(leftPanel);

        VerticalLayout right = new VerticalLayout();
        right.setStyleName("right-panel");
        right.addComponent(topPanel);
        right.addComponent(contentPanel);

        right.setWidth(80, Unit.PERCENTAGE);
        layout.addComponent(right);

        layout.setSizeFull();
        setContent(layout);
    }

    private Component getLeftPanel() {

        final VerticalLayout left = new VerticalLayout();
        left.setStyleName("left-panel");


        Button dashboard = new Button("Dashboard");
        dashboard.setSizeFull();
        left.addComponent(dashboard);

        Button inspect = new Button("Inspect");
        inspect.setSizeFull();
        left.addComponent(inspect);

        Button controller = new Button("Controller");
        controller.setSizeFull();
        left.addComponent(controller);

        Button analytics = new Button("Analytics");
        analytics.setSizeFull();
        left.addComponent(analytics);

        return left;
    }

    private CssLayout getContentPanel() {
        final CssLayout layout = new CssLayout();
        layout.setStyleName("content-panel");
        layout.setSizeFull();

        final Label dummy = new Label("<h2>This is the main content area</h2>", ContentMode.HTML);
        layout.addComponent(dummy);

        return layout;
    }

    private CssLayout getTopPanel() {
        final CssLayout layout = new CssLayout();
        layout.setStyleName("top-menu-panel");
        layout.setSizeFull();

        final Link help = new Link();
        help.setCaption("Help");
        help.setStyleName("top-menu");
        layout.addComponent(help);

        final Link admin = new Link();
        admin.setCaption("Admin");
        admin.setStyleName("top-menu");
        layout.addComponent(admin);

        final Link operations = new Link();
        operations.setCaption("Operations");
        operations.setStyleName("top-menu");
        layout.addComponent(operations);

        return layout;
    }

    private void showNavigator() {
        getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartView(navigator));
        navigator.addView(MAINVIEW, new HelpView(navigator));
        navigator.addView(REPORTVIEW, new ReportView(navigator));
    }

    private void helloWorld() {
        final VerticalLayout layout = new VerticalLayout();

        final Label hello = new Label("Hello, Vaadin!");
        layout.addComponent(hello);

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                + ", it works!"));
        });

        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

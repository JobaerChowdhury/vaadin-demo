package com.cefalo.vaadin;

import com.cefalo.vaadin.view.AdminView;
import com.cefalo.vaadin.view.HelpView;
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
import java.util.List;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.cefalo.vaadin.MyAppWidgetset")
public class MyUI extends UI {

    Navigator navigator;
    public static final String OPERATION_VIEW = "";
    public static final String ADMIN_VIEW = "admin";
    public static final String HELP_VIEW = "help";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
//        Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartView(navigator));
        navigator.addView(ADMIN_VIEW, new AdminView(navigator));
        navigator.addView(HELP_VIEW, new HelpView(navigator));
    }

    public static CssLayout buildMainContent(List<String> names) {
        CssLayout topPanel = getTopPanel();
        CssLayout contentPanel = getContentPanel(names);

        CssLayout layout = new CssLayout();
        layout.setStyleName("main-layout");

        topPanel.setWidth(100, Unit.PERCENTAGE);
        layout.addComponent(topPanel);

        layout.addComponent(contentPanel);

        layout.setSizeFull();
        return layout;
    }


    private static Component getLeftPanel(List<String> names) {

        final VerticalLayout left = new VerticalLayout();
        left.setStyleName("left-panel");

        for (String name : names) {
            Button button = new Button(name);
            button.setSizeFull();
            left.addComponent(button);
        }

        return left;
    }

    private static CssLayout getContentPanel(List<String> names) {
        CssLayout mainContentPanel = getMainContentPanel();
        Component leftPanel = getLeftPanel(names);

        CssLayout content = new CssLayout();
        content.setSizeFull();
        leftPanel.setWidth(15, Unit.PERCENTAGE);

        content.addComponent(leftPanel);

        mainContentPanel.setWidth(85, Unit.PERCENTAGE);
        content.addComponent(mainContentPanel);

        return content;
    }

    private static CssLayout getMainContentPanel() {
        final CssLayout layout = new CssLayout();
        layout.setStyleName("content-panel");
        layout.setSizeFull();

        final Label dummy = new Label("<h2>This is the main content area</h2>", ContentMode.HTML);
        layout.addComponent(dummy);

        return layout;
    }

    private static CssLayout getTopPanel() {
        final CssLayout layout = new CssLayout();
        layout.setStyleName("top-menu-panel");

        final Button help = new Button();
        help.setStyleName("link top-menu");
        help.setCaption("Help");
        help.addClickListener(clickEvent -> Notification.show("Clicked on help!"));
        layout.addComponent(help);

        final Button admin = new Button();
        admin.setCaption("Admin");
        admin.setStyleName("link top-menu");
        admin.addClickListener(e -> Notification.show("Clicked on Admin!"));
        layout.addComponent(admin);

        final Button operations = new Button();
        operations.setCaption("Operations");
        operations.setStyleName("link top-menu");
        operations.addClickListener(e -> Notification.show("Clicked on Operations!"));
        layout.addComponent(operations);

        return layout;
    }

    private void showNavigator() {
        getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartView(navigator));
        navigator.addView(OPERATION_VIEW, new AdminView(navigator));
        navigator.addView(HELP_VIEW, new HelpView(navigator));
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

    //    If you have only one ui mapping to the root path, then define the servlet as following commented line.
//    Otherwise you will also have to specify the VAADIN path as shown below. This is helpful for experimenting
//    since we are writing multiple UIs for experiment.
//    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @WebServlet(urlPatterns = {"/myui/*", "/VAADIN/*"}, name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

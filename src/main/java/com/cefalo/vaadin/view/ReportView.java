package com.cefalo.vaadin.view;

/**
 * Created by jobaer on 1/21/16.
 */

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * A start view for navigating to the main view
 */
public class ReportView extends VerticalLayout implements View {
    protected static final String MAINVIEW = "main";

    public ReportView(Navigator navigator) {
        setSizeFull();

        Button button = new Button("Go back",
            event -> navigator.navigateTo(""));
        addComponent(button);
        Label label = new Label("This is the report view");
        addComponent(label);
        setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Reports view");
    }
}
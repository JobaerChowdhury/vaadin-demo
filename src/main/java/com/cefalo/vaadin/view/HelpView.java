package com.cefalo.vaadin.view;

/**
 * Created by jobaer on 1/21/16.
 */

import com.cefalo.vaadin.MyUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * A start view for navigating to the main view
 */
public class HelpView extends VerticalLayout implements View {

    public HelpView(Navigator navigator) {
        setSizeFull();

        Button button = new Button("Go back",
            event -> navigator.navigateTo(""));
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        Label label = new Label("This is the Help view");
        addComponent(label);
        setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        Button report = new Button("Go to reports",
            event -> navigator.navigateTo(MyUI.REPORTVIEW));
        addComponent(report);
        setComponentAlignment(report, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Help view");
    }
}
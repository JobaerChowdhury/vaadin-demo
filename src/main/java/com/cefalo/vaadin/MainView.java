package com.cefalo.vaadin;

/**
 * Created by jobaer on 1/21/16.
 */

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * A start view for navigating to the main view
 */
public class MainView extends VerticalLayout implements View {
    protected static final String MAINVIEW = "main";

    public MainView(Navigator navigator) {
        setSizeFull();

        Button button = new Button("Go back",
            new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    navigator.navigateTo("");
                }
            });
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}
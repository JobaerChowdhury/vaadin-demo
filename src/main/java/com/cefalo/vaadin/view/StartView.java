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
public class StartView extends VerticalLayout implements View {

    public StartView(Navigator navigator) {
        setSizeFull();

        Button button = new Button("Go to Help View",
            new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    navigator.navigateTo(MyUI.MAINVIEW);
                }
            });

        Button report = new Button("Go to Report View",
            new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    navigator.navigateTo(MyUI.REPORTVIEW);
                }
            });

        addComponent(button);
        Label label = new Label("This is the start view");
        addComponent(label);
        setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        addComponent(report);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        setComponentAlignment(report, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Initial view");
    }
}
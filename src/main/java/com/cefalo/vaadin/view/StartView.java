package com.cefalo.vaadin.view;

/**
 * Created by jobaer on 1/21/16.
 */

import com.cefalo.vaadin.MyUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A start view for navigating to the main view
 */
public class StartView extends CssLayout implements View {

    public StartView(Navigator navigator) {
        List<String> names = new ArrayList<>();
        names.add("Dashboard");
        names.add("Inspect");
        names.add("Controller");
        names.add("Analytics");

        CssLayout layout = MyUI.buildMainContent(names);
        setSizeFull();
        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Initial view");
    }
}
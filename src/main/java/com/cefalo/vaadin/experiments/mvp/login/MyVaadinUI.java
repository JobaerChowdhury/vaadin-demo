package com.cefalo.vaadin.experiments.mvp.login;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * Created by jobaer on 1/24/2016.
 */
public class MyVaadinUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        LoginView loginView = new LoginViewImpl();
        LoginPresenter loginPresenter = new LoginPresenter(loginView, new DummyUserService());

        loginView.setHandler(loginPresenter);
        loginView.init();

        navigator.addView("", loginView);
        setNavigator(navigator);
        navigator.navigateTo("");
    }

    @WebServlet(urlPatterns = "/login/*", name = "MyLoginServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyVaadinUI.class, productionMode = false)
    public static class MyLoginServlet extends VaadinServlet {

    }
}

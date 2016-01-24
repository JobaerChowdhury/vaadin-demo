package com.cefalo.vaadin.experiments.mvp.login;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by jobaer on 1/24/2016.
 */
public class LoginViewImpl extends VerticalLayout implements LoginView {
    private LoginViewHandler handler;
    private TextField txtUsername;
    private TextField txtPassword;
    private Button loginButton;

    @Override
    public void setHandler(LoginViewHandler handler) {
        this.handler = handler;
    }

    @Override
    public void init() {
        txtUsername = new TextField("Username");
        addComponent(txtUsername);

        txtPassword = new TextField("Password");
        addComponent(txtPassword);

        loginButton = new Button("Login");
        addComponent(loginButton);
        loginButton.addClickListener( clickEvent -> {
            handler.login();
        });
    }

    @Override
    public TextField getTxtUsername() {
        return txtUsername;
    }

    @Override
    public TextField getTxtPassword() {
        return txtPassword;
    }

    @Override
    public Button getBtnLogin() {
        return loginButton;
    }

    @Override
    public void afterSuccessfulLogin() {
        UI.getCurrent().getNavigator().navigateTo("only-for-signed-in-users");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

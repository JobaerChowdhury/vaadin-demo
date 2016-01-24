package com.cefalo.vaadin.experiments.mvp.login;

import com.vaadin.ui.TextField;

/**
 * Created by jobaer on 1/24/2016.
 */
public class LoginPresenter implements LoginViewHandler {
    private LoginView loginView;
    private UserService userService;

    public LoginPresenter(LoginView loginView, UserService userService) {
        this.loginView = loginView;
        this.userService = userService;
    }

    @Override
    public void login() {
        TextField usernameField = loginView.getTxtUsername();
        TextField passwordField = loginView.getTxtPassword();

        String username = usernameField.getValue();
        String password = passwordField.getValue();

        try {
            userService.login(username, password);
            loginView.afterSuccessfulLogin();
        } catch (UserException exp) {
            // todo handle exception
        }
    }
}

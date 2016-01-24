package com.cefalo.vaadin.experiments.mvp.login;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

/**
 * Created by jobaer on 1/24/2016.
 */
public interface LoginView extends View {
    void setHandler(LoginViewHandler handler);
    void init();

    TextField getTxtUsername();
    TextField getTxtPassword();

    Button getBtnLogin();

    void afterSuccessfulLogin();
}

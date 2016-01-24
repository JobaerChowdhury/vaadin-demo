package com.cefalo.vaadin.experiments.mvp.login;

/**
 * Created by jobaer on 1/24/2016.
 */
public interface UserService {
    User login(String username, String password) throws UserException;
}

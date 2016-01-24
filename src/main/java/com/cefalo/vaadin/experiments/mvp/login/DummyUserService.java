package com.cefalo.vaadin.experiments.mvp.login;

/**
 * Created by jobaer on 1/24/2016.
 */
public class DummyUserService implements UserService {
    @Override
    public User login(String username, String password) throws UserException {
        return new User(username, password);
    }
}

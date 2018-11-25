package org.dropwizard.auth.core;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import org.dropwizard.auth.data.UserDAO;

import java.util.Optional;

public class DropwizardAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDAO userDAO;

    public DropwizardAuthenticator (
            UserDAO userDAO
    ) {
        this.userDAO = userDAO;
    }

    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if(userDAO.findByCredentials(credentials.getUsername(), credentials.getPassword()).size() > 0) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }
}

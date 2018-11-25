package org.dropwizard.auth.core;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import org.dropwizard.auth.data.UserDAO;
import org.dropwizard.auth.data.UserEntity;

import java.util.Objects;
import java.util.Optional;

public class DropwizardAuthenticator implements Authenticator<BasicCredentials, User> {

    private static final Encrypter encrypter;
    private final UserDAO userDAO;

    static {
        encrypter = new Encrypter();
    }

    public DropwizardAuthenticator (
            UserDAO userDAO
    ) {
        this.userDAO = userDAO;
    }

    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        UserEntity entity = userDAO.findByUsername(credentials.getUsername());

        if(Objects.nonNull(entity) && encrypter.checkPassword(credentials.getPassword(), entity.getPassword())) {

            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }
}

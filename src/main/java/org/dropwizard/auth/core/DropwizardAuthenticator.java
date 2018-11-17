package org.dropwizard.auth.core;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class DropwizardAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if("password".equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.absent();
    }
}

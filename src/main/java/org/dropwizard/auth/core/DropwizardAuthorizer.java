package org.dropwizard.auth.core;

import io.dropwizard.auth.Authorizer;

public class DropwizardAuthorizer implements Authorizer<User> {
    private static final String ADMIN = "ADMIN";
    private static final String TEST_NAME = "testName";

    public boolean authorize(User user, String role) {
        return user.getName().equals(TEST_NAME) && role.equals(ADMIN);
    }
}

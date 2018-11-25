package org.dropwizard.auth.core;

import io.dropwizard.auth.Authorizer;
import io.dropwizard.hibernate.UnitOfWork;
import org.dropwizard.auth.data.UserDAO;
import org.dropwizard.auth.data.UserRole;

import java.util.Objects;

public class DropwizardAuthorizer implements Authorizer<User> {

    private final UserDAO userDAO;

    public DropwizardAuthorizer (
            UserDAO userDAO
    ) {
        this.userDAO = userDAO;
    }

    @UnitOfWork
    public boolean authorize(User user, String role) {
        UserRole userRole = UserRole.valueOf(role);

        return Objects.nonNull(userDAO.findByRole(user.getUserName(), userRole));
    }
}

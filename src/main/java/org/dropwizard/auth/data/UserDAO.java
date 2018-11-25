package org.dropwizard.auth.data;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<UserEntity> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public UserEntity findByUsername(String username) {
        return uniqueResult(
                namedQuery("org.dropwizard.auth.data.User.find")
                        .setParameter(UserEntity.USERNAME, username)
        );
    }

    public UserEntity findByRole(String username, UserRole role) {
        return uniqueResult(
                namedQuery("org.dropwizard.auth.data.User.findWithRole")
                        .setParameter(UserEntity.USERNAME, username)
                        .setParameter(UserEntity.ROLE, role.name())
        );
    }
}

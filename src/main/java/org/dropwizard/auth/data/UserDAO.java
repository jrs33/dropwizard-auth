package org.dropwizard.auth.data;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<UserEntity> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<UserEntity> findByCredentials(String username, String password) {
        return list(
                namedQuery("org.dropwizard.auth.data.User.find")
                        .setParameter(UserEntity.USERNAME, username)
                        .setParameter(UserEntity.PASSWORD, password)
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

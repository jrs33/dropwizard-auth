package org.dropwizard.auth;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.dropwizard.auth.core.DropwizardAuthenticator;
import org.dropwizard.auth.core.DropwizardAuthorizer;
import org.dropwizard.auth.core.User;
import org.dropwizard.auth.data.UserDAO;
import org.dropwizard.auth.data.UserEntity;
import org.dropwizard.auth.resource.AuthenticatedResource;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class AuthApplication extends Application<AuthConfiguration> {

    private final HibernateBundle<AuthConfiguration> hibernateBundle = new HibernateBundle<AuthConfiguration>(UserEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(AuthConfiguration authConfiguration) {
            return authConfiguration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new AuthApplication().run(args);
    }

    @Override
    public String getName() {
        return "Auth";
    }

    @Override
    public void initialize(final Bootstrap<AuthConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final AuthConfiguration configuration,
                    final Environment environment) {

        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        DropwizardAuthenticator dropwizardAuthenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(DropwizardAuthenticator.class, UserDAO.class, userDAO);
        DropwizardAuthorizer dropwizardAuthorizer = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(DropwizardAuthorizer.class, UserDAO.class, userDAO);

        BasicCredentialAuthFilter<User> authFilterBuilder =
                new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(dropwizardAuthenticator)
                .setAuthorizer(dropwizardAuthorizer)
                .setRealm("A REALM")
                .buildAuthFilter();

        environment.jersey().register(new AuthDynamicFeature(authFilterBuilder));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        // Allows @Auth annotation to inject custom principals into resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        // Registering resources;
        environment.jersey().register(new AuthenticatedResource());
    }

}

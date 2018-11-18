package org.dropwizard.auth;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.dropwizard.auth.core.DropwizardAuthenticator;
import org.dropwizard.auth.core.DropwizardAuthorizer;
import org.dropwizard.auth.core.User;
import org.dropwizard.auth.resource.AuthenticatedResource;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class AuthApplication extends Application<AuthConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AuthApplication().run(args);
    }

    @Override
    public String getName() {
        return "Auth";
    }

    @Override
    public void initialize(final Bootstrap<AuthConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AuthConfiguration configuration,
                    final Environment environment) {

        BasicCredentialAuthFilter<User> authFilterBuilder =
                new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new DropwizardAuthenticator())
                .setAuthorizer(new DropwizardAuthorizer())
                .setRealm("A REALM")
                .buildAuthFilter();

        environment.jersey().register(new AuthDynamicFeature(authFilterBuilder));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        // Allows @Auth annotation to inject custom principals into resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        // Registering resources
        environment.jersey().register(new AuthenticatedResource());
    }

}

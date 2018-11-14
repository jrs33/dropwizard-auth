package org.dropwizard.auth;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AuthApplication extends Application<AuthConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AuthApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-auth";
    }

    @Override
    public void initialize(final Bootstrap<AuthConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AuthConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

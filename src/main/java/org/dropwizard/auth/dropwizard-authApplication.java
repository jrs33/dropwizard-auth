package org.dropwizard.auth;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dropwizard-authApplication extends Application<dropwizard-authConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dropwizard-authApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-auth";
    }

    @Override
    public void initialize(final Bootstrap<dropwizard-authConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final dropwizard-authConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

package org.dropwizard.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AuthConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory databaseConnection = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return databaseConnection;
    }

    public void setDatasourceFactory(DataSourceFactory sourceFactory) {
        this.databaseConnection = sourceFactory;
    }
}

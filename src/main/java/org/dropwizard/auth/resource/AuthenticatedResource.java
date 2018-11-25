package org.dropwizard.auth.resource;

import io.dropwizard.auth.Auth;
import org.dropwizard.auth.core.User;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(AuthenticatedResource.AUTHENTICATED_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticatedResource {

    public static final String AUTHENTICATED_PATH  = "/auth";

    @Path("/anyone")
    @GET
    @PermitAll
    public User isAnyone(@Auth User user) {
        return user;
    }

    @Path("/role")
    @GET
    @RolesAllowed({"ADMIN", "SUPER_ADMIN"})
    public User isAdmin(@Auth User user) {
        return user;
    }

    @Path("/deny")
    @GET
    @DenyAll
    public User isDenied(@Auth User user) {
        return user;
    }
}

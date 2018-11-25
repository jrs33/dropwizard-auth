package org.dropwizard.auth.resource;

import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.dropwizard.auth.core.User;
import org.dropwizard.auth.data.UserDAO;

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
    @RolesAllowed("ADMIN")
    public User isAdmin(@Auth User user) {
        return user;
    }

    @Path("/deny")
    @GET
    @DenyAll
    public User isDenied(@Auth User user) {
        return user;
    }

    @Path("/optional")
    @GET
    public Optional<User> isOptionalAuth(@Auth Optional<User> user) {
        return user.isPresent() ? user : Optional.<User>absent();
    }
}

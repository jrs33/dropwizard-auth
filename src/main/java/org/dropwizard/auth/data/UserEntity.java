package org.dropwizard.auth.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = "org.dropwizard.auth.data.User.find",
                query = "select u from UserEntity u " +
                        "where u.username = :username " +
                        "and u.password = :password"
        ),
        @NamedQuery(
                name = "org.dropwizard.auth.data.User.findWithRole",
                query = "select u from UserEntity u " +
                        "where u.username = :username " +
                        "and u.role = :role"
        )
})
public class UserEntity {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    @Id
    @Column(name = USERNAME)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = ROLE)
    private String role;

    public UserEntity() {
    }

    public UserEntity(
            String username,
            String password,
            String role
    ) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

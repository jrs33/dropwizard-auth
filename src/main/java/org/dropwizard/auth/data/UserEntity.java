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
    private UserRole role;

    public UserEntity() {
    }

    public UserEntity(
            String username,
            String password,
            UserRole role
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

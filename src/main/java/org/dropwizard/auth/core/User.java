package org.dropwizard.auth.core;

import java.security.Principal;

public class User implements Principal {

    private long id;
    private String userName;
    private String password;

    public User(
            long id,
            String userName,
            String password
    ) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(User otherUser) {
        return (this.id == otherUser.id &&
                this.userName.equals(otherUser.userName) &&
                this.password.equals(otherUser.password));
    }

    public String toString() {
        return String.format(
                "User:{id:%d, userName: %s, password: %s}",
                this.id,
                this.userName,
                this.password);
    }

    public String getName() {
        return userName;
    }
}

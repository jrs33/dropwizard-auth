package org.dropwizard.auth.core;

import java.security.Principal;

public class User implements Principal {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean equals(User otherUser) {
        return this.userName.equals(otherUser.userName);
    }

    public String toString() {
        return String.format(
                "User:{userName: %s}",
                this.userName);
    }

    public String getName() {
        return userName;
    }
}

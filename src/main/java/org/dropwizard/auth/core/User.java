package org.dropwizard.auth.core;

import java.security.Principal;

public class User implements Principal {

    private long id;
    private String userName;

    public User(String userName) {
        this.id = 0L;
        this.userName = userName;
    }

    public User(
            long id,
            String userName
    ) {
        this.id = id;
        this.userName = userName;
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

    public boolean equals(User otherUser) {
        return (this.id == otherUser.id &&
                this.userName.equals(otherUser.userName));
    }

    public String toString() {
        return String.format(
                "User:{id:%d, userName: %s, password: %s}",
                this.id,
                this.userName);
    }

    public String getName() {
        return userName;
    }
}

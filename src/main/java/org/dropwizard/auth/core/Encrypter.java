package org.dropwizard.auth.core;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypter {

    private final int MIN_LENGTH = 8;

    public Encrypter() {
    }

    protected String hashPassword(String password) throws IllegalArgumentException {
        if(isValidPlainText(password)) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        throw new IllegalArgumentException("invalid_password");
    }

    protected boolean checkPassword(
            String password,
            String hashedPassword
    ) throws IllegalArgumentException {
        if(isValidPlainText(password)) {
            return BCrypt.checkpw(password, hashedPassword);
        }

        throw new IllegalArgumentException("invalid_password");
    }

    private boolean isValidPlainText(String plainTextPassword) {
        return plainTextPassword.length() >= MIN_LENGTH;
    }
}

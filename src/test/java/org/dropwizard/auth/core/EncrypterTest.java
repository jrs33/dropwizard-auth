package org.dropwizard.auth.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EncrypterTest {

    final String PLAIN_TEXT = "thisIsARandomHelloTestPass!#";

    Encrypter encrypter;

    @Before
    public void setUp() {
        encrypter = new Encrypter();
    }

    @Test
    public void testPasswordHashing() {
        String hashedPassword = encrypter.hashPassword(PLAIN_TEXT);
        Assert.assertNotEquals(PLAIN_TEXT, hashedPassword);
    }

    @Test
    public void testPasswordMatching() {
        boolean isEqual = encrypter.checkPassword(PLAIN_TEXT, encrypter.hashPassword(PLAIN_TEXT));
        Assert.assertTrue(isEqual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPasswordHashing() {
        encrypter.hashPassword("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPasswordMatching() {
        encrypter.checkPassword("", "");
    }
}

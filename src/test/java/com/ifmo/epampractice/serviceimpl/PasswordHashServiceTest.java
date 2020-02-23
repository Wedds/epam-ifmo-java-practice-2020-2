package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.exceptions.HashingException;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.services.PasswordHashService;
import org.junit.Test;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class PasswordHashServiceTest {

    @Test
    public void resultingHashTest() {
        final SecureString testPassword = new SecureString();
        testPassword.append(
                new char[] { 'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd', '1', '2', '3' }
        );
        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(testPassword);
            Assert.assertNotEquals(null, hashStr);

            String[] split = hashStr.split("#");
            Assert.assertEquals(2, split.length);
            Assert.assertTrue(split[0] != null && split[0].length() > 0);
            Assert.assertTrue(split[1] != null && split[1].length() > 0);
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void validPasswordAuthTest() {
        final SecureString testPassword = new SecureString();
        testPassword.append(
                new char[] { 'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd', '1', '2', '3' }
        );

        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(testPassword);
            boolean result = hashService.isMatching(hashStr, testPassword);
            Assert.assertTrue(result);
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void invalidPasswordAuthTest() {
        final SecureString testPassword1 = new SecureString();
        testPassword1.append(
                new char[] { 'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd', '1', '2', '3' }
        );
        final SecureString testPassword2 = new SecureString();
        testPassword2.append(
                new char[] { 'w', 'r', 'o', 'n', 'g', 'p', 'a', 's', 's' }
        );

        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(testPassword1);
            boolean result = hashService.isMatching(hashStr, testPassword2);
            Assert.assertFalse(result);
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}

package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.exceptions.HashingException;
import com.ifmo.epampractice.services.PasswordHashService;
import org.junit.Test;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class PasswordHashServiceTest {

    @Test
    public void resultingHashTest() {
        final String TEST_PASSWORD = "helloworld123";
        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(TEST_PASSWORD);
            Assert.assertNotEquals(null, hashStr);

            String[] split = hashStr.split("#");
            Assert.assertEquals(2, split.length);
            Assert.assertEquals(true, (split[0] != null && split[0].length() > 0));
            Assert.assertEquals(true, (split[1] != null && split[0].length() > 0));
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void validPasswordAuthTest() {
        final String TEST_PASSWORD = "helloworld123";

        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(TEST_PASSWORD);
            boolean result = hashService.isMatching(hashStr, TEST_PASSWORD);
            Assert.assertEquals(true, result);
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void invalidPasswordAuthTest() {
        final String TEST_PASSWORD_1 = "helloworld123";
        final String TEST_PASSWORD_2 = "wrongpassword456";

        PasswordHashService hashService = new PasswordHashServiceImpl();

        try {
            String hashStr = hashService.getHash(TEST_PASSWORD_1);
            boolean result = hashService.isMatching(hashStr, TEST_PASSWORD_2);
            Assert.assertEquals(false, result);
        }
        catch (HashingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }



}

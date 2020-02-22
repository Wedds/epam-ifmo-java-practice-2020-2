package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.exceptions.HashingException;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.services.PasswordHashService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordHashServiceImpl implements PasswordHashService {

    private static final char SALT_SEPARATOR = '#';
    private static final String HASH_ALGORITHM = "SHA-512";

    // Allowed characters for hash/salt output: 0-9 a-z A-Z :;<=>?@
    private static final byte ALPHABET_START = 48;
    private static final byte ALPHABET_END = 122;
    private static final byte ALPHABET_SIZE = ALPHABET_END - ALPHABET_START + 1;

    @Override
    public String getHash(SecureString password) {
        if (password == null || password.isDisposed()) {
            throw new IllegalArgumentException(
                    "Trying to pass null or disposed SecureString as argument.");
        }

        byte[] saltBytes = getSalt();
        byte[] passwordBytes = password.getBytes(StandardCharsets.US_ASCII);
        byte[] passwordHashBytes = getHash(passwordBytes, saltBytes);

        clearSensitiveData(passwordBytes);

        return new String(saltBytes, StandardCharsets.US_ASCII) +
                SALT_SEPARATOR +
                new String(passwordHashBytes, StandardCharsets.US_ASCII);
    }

    @Override
    public boolean isMatching(String saltedHash, SecureString password) {

        if (password == null || password.isDisposed()) {
            throw new IllegalArgumentException(
                    "Trying to pass null or disposed SecureString as argument.");
        }

        String[] hashParts = saltedHash.split(Character.toString(SALT_SEPARATOR));
        if (hashParts.length != 2) {
            throw new IllegalArgumentException("'saltedHash' should contain a salt and a hash " +
                    "separated by '" + SALT_SEPARATOR + "' character.");
        }

        byte[] saltBytes = hashParts[0].getBytes(StandardCharsets.US_ASCII);
        byte[] hashBytes = hashParts[1].getBytes(StandardCharsets.US_ASCII);
        byte[] passwordBytes = password.getBytes(StandardCharsets.US_ASCII);
        byte[] passwordHashBytes = getHash(passwordBytes, saltBytes);

        clearSensitiveData(passwordBytes);

        return Arrays.equals(hashBytes, passwordHashBytes);
    }

    private byte[] getSalt() {
        SecureRandom rand = new SecureRandom();
        byte[] saltBytes = new byte[16];
        rand.nextBytes(saltBytes);

        ensureIsInAlphabet(saltBytes);
        return saltBytes;
    }

    private byte[] getHash(byte[] input, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashBytes = md.digest(input);

            ensureIsInAlphabet(hashBytes);
            return hashBytes;
        }
        catch (NoSuchAlgorithmException e) {
            throw new HashingException("Hashing algorithm not found: " + HASH_ALGORITHM, e);
        }
    }

    private void clearSensitiveData(byte[] data) {
        Arrays.fill(data, (byte) 0);
    }

    private void ensureIsInAlphabet(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ensureIsInAlphabet(bytes[i]);
        }
    }

    /**
     * Ensures input byte value is between ALPHABET_START and ALPHABET_END.
     */
    private byte ensureIsInAlphabet(byte input) {
        return (byte)(ALPHABET_START + (Math.abs(input) % ALPHABET_SIZE));
    }
}

package com.ifmo.epampractice.security;


import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SecureStringTest {
    @Test
    public void stringDisposeTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };
        SecureString str1 = new SecureString();
        str1.append(chars);
        str1.dispose();

        Assert.assertTrue(str1.isDisposed());

        String str2 = String.valueOf(chars);
        byte[] bytes1 = str1.getBytes(StandardCharsets.US_ASCII);
        byte[] bytes2 = str2.getBytes(StandardCharsets.US_ASCII);
        Assert.assertFalse(Arrays.equals(bytes1, bytes2)); // There's no assertArrayNotEquals()
    }

    @Test
    public void stringAppendTest() {
        char[] chars1 = new char[] { 'n', 'i', 'c', 'e' };
        char[] chars2 = new char[] { ' ', 'd', 'a', 'y' };

        SecureString str1 = new SecureString();
        str1.append(chars1);
        str1.append(chars2);

        String str2 = String.valueOf(chars1) + String.valueOf(chars2);

        byte[] bytes1 = str1.getBytes(StandardCharsets.US_ASCII);
        byte[] bytes2 = str2.getBytes(StandardCharsets.US_ASCII);
        Assert.assertArrayEquals(bytes1, bytes2);
    }

    @Test
    public void equalStringsTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };

        SecureString str1 = new SecureString();
        SecureString str2 = new SecureString();
        str1.append(chars);
        str2.append(chars);

        Assert.assertEquals(str1, str2);
        Assert.assertEquals(str1.hashCode(), str2.hashCode());
    }

    @Test
    public void notEqualStringsTest() {
        char[] chars1 = new char[] { 'n', 'i', 'c', 'e' };
        char[] chars2 = new char[] { ' ', 'd', 'a', 'y' };

        SecureString str1 = new SecureString();
        SecureString str2 = new SecureString();
        str1.append(chars1);
        str2.append(chars2);

        Assert.assertNotEquals(str1, str2);

        // Formally, there's no guarantee string hashes will always be different.
        // However, in such simple case as this (two short strings of same size) we can expect this.
        Assert.assertNotEquals(str1.hashCode(), str2.hashCode());
    }

    @Test
    public void getBytesTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };
        SecureString str = new SecureString();
        str.append(chars);

        byte[] bytes1 = new String(chars).getBytes(StandardCharsets.US_ASCII);
        byte[] bytes2 = str.getBytes(StandardCharsets.US_ASCII);

        Assert.assertArrayEquals(bytes1, bytes2);
    }


}

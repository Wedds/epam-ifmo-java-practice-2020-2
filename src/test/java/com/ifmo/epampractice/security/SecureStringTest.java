package com.ifmo.epampractice.security;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class SecureStringTest {

    @Test
    public void stringValueTest() throws NoSuchFieldException {
        char[] chars = new char[]{'n', 'i', 'c', 'e'};
        SecureString str = new SecureString();
        SecureRandom random = Mockito.mock(SecureRandom.class);
        FieldSetter.setField(str, str.getClass().getDeclaredField("rand"), random);
        Mockito.when(random.nextInt()).thenReturn(1);

        str.append(chars);

        Assert.assertEquals(chars.length, str.getLength());

        for (int i = 0; i < str.getLength(); i++) {
            Assert.assertEquals(chars[i], (byte) (str.getChars().get(i) ^ 1));
        }
    }

    @Test
    public void stringDisposeTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };
        SecureString str = new SecureString(chars);
        str.dispose();

        Assert.assertTrue(str.isDisposed());

        boolean areAllEqual = true;
        for (int i = 0; i < str.getLength(); i++) {
            if (str.charAt(i) != chars[i])
                areAllEqual = false;
        }

        Assert.assertFalse(areAllEqual);
    }

    @Test
    public void stringAppendTest() {
        char[] chars1 = new char[] { 'n', 'i', 'c', 'e' };
        char[] chars2 = new char[] { ' ', 'd', 'a', 'y' };

        SecureString str = new SecureString(chars1);
        Assert.assertEquals(chars1.length, str.getLength());

        str.append(chars2);
        Assert.assertEquals(chars1.length + chars2.length, str.getLength());

        for (int i = 0; i < chars1.length; i++) {
            Assert.assertEquals(chars1[i], str.charAt(i));
        }
        for (int i = 0; i < chars2.length; i++) {
            Assert.assertEquals(chars2[i], str.charAt(i + chars1.length));
        }
    }

    @Test
    public void equalStringsTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };

        SecureString str1 = new SecureString(chars);
        SecureString str2 = new SecureString(chars);

        Assert.assertEquals(str1, str2);
        Assert.assertEquals(str1.hashCode(), str2.hashCode());
    }

    @Test
    public void notEqualStringsTest() {
        char[] chars1 = new char[] { 'n', 'i', 'c', 'e' };
        char[] chars2 = new char[] { ' ', 'd', 'a', 'y' };

        SecureString str1 = new SecureString(chars1);
        SecureString str2 = new SecureString(chars2);

        Assert.assertNotEquals(str1, str2);

        // Formally, there's no guarantee string hashes will always be different.
        // However, in such simple case as this (two short strings of same size) we can expect this.
        Assert.assertNotEquals(str1.hashCode(), str2.hashCode());
    }

    @Test
    public void getBytesTest() {
        char[] chars = new char[] { 'n', 'i', 'c', 'e' };

        byte[] bytes1 = new String(chars).getBytes(StandardCharsets.US_ASCII);
        byte[] bytes2 = new SecureString(chars).getBytes(StandardCharsets.US_ASCII);

        Assert.assertArrayEquals(bytes1, bytes2);
    }


}

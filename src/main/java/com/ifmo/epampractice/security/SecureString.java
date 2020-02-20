package com.ifmo.epampractice.security;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class SecureString {
    private List<Integer> chars = new ArrayList<>();
    private List<Integer> pad = new ArrayList<>();
    private boolean isDisposed = false;

    public SecureString() {}
    public SecureString(char[] str) {
        append(str);
    }

    public void append(char[] str) {
        SecureRandom rand = new SecureRandom();
        for (char c : str) {
            int padValue = rand.nextInt();
            pad.add(padValue);
            chars.add(c ^ padValue);
        }
    }

    /**
     * Call this to manually clear memory after using the string.
     */
    public void dispose() {
        for (int i = 0; i < chars.size(); i++) {
            chars.set(i, 0);
            pad.set(i, 0);
        }
        isDisposed = true;
    }

    public boolean isDisposed() {
        return isDisposed;
    }

    public int getLength() {
        return chars.size();
    }

    public char charAt(int index) {
        return (char)(chars.get(index) ^ pad.get(index));
    }

    public byte[] getBytes(Charset charset) {
        CharBuffer charBuffer = CharBuffer.allocate(getLength());
        for (int i = 0; i < getLength(); i++) {
            charBuffer.put(charAt(i));
        }
        charBuffer.flip();
        ByteBuffer byteBuffer = charset.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());

        // Clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0);
        Arrays.fill(charBuffer.array(), (char) 0);
        return bytes;
    }

    @Override
    public String toString() {
        return "(SecureString)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SecureString that = (SecureString) o;

        if (isDisposed != that.isDisposed || getLength() != that.getLength())
            return false;

        for (int i = 0; i < getLength(); i++) {
            if (charAt(i) != that.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = (isDisposed ? 7 : 0);
        for (int i = 0; i < getLength(); i++) {
            hash = hash * 31 + charAt(i);
        }
        return hash;
    }
}

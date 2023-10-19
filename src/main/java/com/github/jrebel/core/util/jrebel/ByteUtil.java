package com.github.jrebel.core.util.jrebel;


import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;

public class ByteUtil {
    private static final Random a;

    public static String a(final byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        return new String(Base64.getEncoder().encode(binaryData), Charset.forName("UTF-8"));
    }

    public static byte[] a(final String s) {
        if (s == null) {
            return null;
        }
        return Base64.getDecoder().decode(s.getBytes(Charset.forName("UTF-8")));
    }

    public static byte[] a(final int n) {
        final byte[] array = new byte[n];
        ByteUtil.a.nextBytes(array);
        return array;
    }

    static {
        a = new Random();
    }
}

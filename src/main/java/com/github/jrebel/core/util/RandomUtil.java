package com.github.jrebel.core.util;

import java.util.Random;

public class RandomUtil {

    public static int next(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    public static long next(long min, long max) {
        Random random = new Random();
        return random.nextLong(max - min + 1) + min;
    }

}

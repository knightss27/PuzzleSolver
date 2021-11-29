package main;

// Author: Andrew Merrill

import java.util.Random;

public class MyRandom {
    public static Random random = new Random();

    public static double nextDoubleInRange(double low, double high) {
        return low + random.nextDouble() * (high - low);
    }

    public static int nextIntInRange(int low, int high) {
        return low + random.nextInt(high - low + 1);
    }
}
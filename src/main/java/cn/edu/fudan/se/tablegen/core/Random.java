package cn.edu.fudan.se.tablegen.core;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Random {
    public static final int RANDOM_NAME_ARRAY_LENGTH = 200;
    private final String[] randomNameArray = new String[RANDOM_NAME_ARRAY_LENGTH];
    private final String[] randomDescriptionArray = new String[RANDOM_NAME_ARRAY_LENGTH];

    private java.util.Random random;

    public Random(long seed) {
        random = new java.util.Random(seed);
        for (int i = 0; i < randomNameArray.length; i++) {
            randomNameArray[i] = randomString(10, 20);
            randomDescriptionArray[i] = randomString(500, 1000);
        }
    }

    private String randomString(int minLength, int maxLength) {
        String name = "";
        int length = minLength + random.nextInt(maxLength - minLength);
        for (int j = 0; j < length; j++) {
            name += (char) ('a' + random.nextInt(26));
        }
        return name;
    }

    public String nextRandomName() {
        return randomNameArray[random.nextInt(RANDOM_NAME_ARRAY_LENGTH)];
    }

    public String nextRandomDescription() {
        return randomDescriptionArray[random.nextInt(RANDOM_NAME_ARRAY_LENGTH)];
    }

    public int nextInt(int max) {
        return random.nextInt(max);
    }

    public double nextDouble(int max) {
        return random.nextDouble() * max;
    }
}

package com.example.a309.snowfalling;

/**
 * Created by 309 on 2018-04-06.
 */

public class Random {

    private static final java.util.Random RANDOM = new java.util.Random();

    public float getRandom(float min, float max){
        float lower = Math.min(min, max);
        float upper = Math.max(min, max);

        return getRandom(upper - lower) + lower;
    }

    public int getRandom(int temp){
        return RANDOM.nextInt(temp);
    }

    public float getRandom(float temp){
        return RANDOM.nextFloat() * temp;
    }
}

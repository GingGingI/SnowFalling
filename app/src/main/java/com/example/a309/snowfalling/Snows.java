package com.example.a309.snowfalling;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;


public class Snows {
    private static final float ANGLE_RANGE = 0.1f;
    private static final float HALF_ANGLE_RANGE = ANGLE_RANGE / 2f;
    private static final float HALF_PI = (float) Math.PI / 2f;
    private static final float ANGLE_SEED = 25f;
    private static final float ANGLE_DIVISOR = 10000f;

    private static final float INCREMENT_LOWER = 2f;
    private static final float INCREMENT_UPPER = 15f;
//  눈송이 속도 최소lower 최대upper
    private static final float SNOW_SIZE_LOWER = 4f;
    private static final float SNOW_SIZE_UPPER = 10f;
//  눈송이 사이즈 최소 lower 최대 upper
    private static final float SNOW_DIRECTION = 0;
//  0 : Down, 1 : UP
    private final Random random;
    private final Point position;
    private float angle;
    private final float increment;
    private final float snowsize;
    private final Paint paint;

    public static Snows create(int width, int height, Paint paint){
        Random random = new Random();
        int x = random.getRandom(width);
        int y = random.getRandom(height);
        Point position = new Point(x, y);
        float angle = random.getRandom(ANGLE_RANGE) / ANGLE_SEED * ANGLE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = random.getRandom(INCREMENT_LOWER, INCREMENT_UPPER);
        float snowsize = random.getRandom(SNOW_SIZE_LOWER, SNOW_SIZE_UPPER);
        return new Snows(random, position, angle, increment, snowsize, paint);
    }

    Snows(Random random, Point position, float angle, float increment, float snowsize, Paint paint){
        this.random = random;
        this.position = position;
        this.angle = angle;
        this.increment = increment;
        this.snowsize = snowsize;
        this.paint = paint;
    }

    private void move (int width, int height){
        double x = position.x + (increment * Math.cos(angle) * 3);
        //좌우 움직임
//                GoDown
//                double y = position.y + (increment * Math.sin(angle));
//                GoUp
                 double y = position.y - (increment * Math.sin(angle));
        //상하움직임

        angle += random.getRandom(- ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR;

        position.set((int) x,(int) y);
        if (!ChkIsInside(width, height)){
            reset(width, height);
        }
    }


    private boolean ChkIsInside(int width, int height) {
        int x = position.x - 10;
//        GoDown
//        int y = position.y + 100;
//        return x >= -snowsize - 1 && x + snowsize <= width && y >= -snowsize -1 && y -snowsize < height;
//        GoUp
        int y = position.y;
        return x >= -snowsize - 1 && x + snowsize <= width && y >= -snowsize +1 && y +snowsize < height;
    }

    private void reset(int width, int height) {
        position.x = random.getRandom(width);
//        GoDown
//        position.y = (int) (-snowsize -1);
//        GoUp
        position.y = (int) (height -snowsize + 1);
        angle = random.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGLE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }

    public void draw(Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawCircle(position.x, position.y, snowsize, paint);
    }
}

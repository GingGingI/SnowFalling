package com.example.a309.snowfalling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class fallingView extends View {

    private static final int Num_Snows = 50;
    private static final int DELAY = 5;
    //눈송이수는 150개제한
    //5밀리초 단위로 뷰 갱신

    private Snows[] snows;

    public fallingView(Context context) {
        super(context);
    }
    public fallingView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public fallingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void resize(int width, int height){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        //안티엘리언싱 적용한 흰색 스트로크눈송이 지정

         /*
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        //안티엘리언싱 적용한 흰색 가득찬눈송이 지정*/

        snows = new Snows[Num_Snows];
        //눈송이수만큼 배열생성
        for (int i = 0; i < Num_Snows; i++){
            snows[i] = Snows.create(width, height, paint);
            //XY크기의 눈송이를 페인트형식으로 생성
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh){
            resize(w, h);
        }
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for (Snows snow : snows){
            snow.draw(canvas);
        }
        getHandler().postDelayed(runnable, DELAY);
        //5밀리초마다 runnable실행
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
            //다음draw를위한 화면초기화
        }
    };
}

package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;
import android.graphics.RectF;


import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.JoyStickClass;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.activity.MainActivity;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.BoxCollidable;


public class Player implements GameObject, BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f/4.5f;
    private static final float LASER_DURATION =FIRE_INTERVAL/3;
    private float fireTime;
    private float x, y;
    private float dx, dy;
    private float tx, ty;
    private float speed;
    private static GameBitmap planeBitmap;
    private static GameBitmap fireBitmap;
    private float angle = 0;

    private JoyStickClass js;
    public Player(float x, float y) {
        this.js=MainActivity.js;
        this.x = x;
        this.y = y;

        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.planeBitmap = new GameBitmap(R.mipmap.player);
        this.fireTime=0.0f;
    }

    public void moveTo(float x, float y) {
        this.tx=x;
    }

    public void update() {
        MainGame game = MainGame.get();
        if(this.x<0)
            this.x=0;
        if(this.y<0)
            this.y=0;
        this.x += js.getX()*game.frameTime*3.f;
        this.y += js.getY()*game.frameTime*3.f;
    }
    public float calcAngleDegree(float x,float y)
    {
        return (float) (Math.atan2(y,x) *180/Math.PI);
    }

    public void draw(Canvas canvas) {
        planeBitmap.draw(canvas,x,y);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        planeBitmap.getBoundingRect(x, y, rect);
    }
}


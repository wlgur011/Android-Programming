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
    private static GameBitmap planeBitmap;
    private float angle = 0;
    private Vector2 Pos,Dir;
    private JoyStickClass js;
    private float SPEED=5.f;
    public Player(Vector2 Pos) {
        this.js=MainActivity.js;
        this.Pos=Pos;
        this.Dir=new Vector2(0,0);
        this.planeBitmap = new GameBitmap(R.mipmap.player);
        this.planeBitmap.Set_Scale(80.f,80.f);

    }

    public void update() {
        MainGame game = MainGame.get();
        if(Pos.x<0)
            Pos.x=0;
        if(Pos.y<0)
            Pos.y=0;

        Dir.x= js.getX()*game.frameTime*3.f;
        Dir.y= js.getY()*game.frameTime*3.f;
        Dir.nor();
        Pos.add(Dir.mul(SPEED) );
    }
    public float calcAngleDegree(float x,float y)
    {
        return (float) (Math.atan2(y,x) *180/Math.PI);
    }

    public void draw(Canvas canvas) {
        planeBitmap.draw(canvas,Pos.x,Pos.y);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        planeBitmap.getBoundingRect(Pos.x, Pos.y, rect);
    }

    public Vector2 getPos()
    {
        return Pos;
    }
}


package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.Recyclable;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.BoxCollidable;

public class Laser implements GameObject, BoxCollidable {
    private static final String TAG = Laser.class.getSimpleName();
    private GameBitmap bitmap;

    private float LIFETIME=0.8f;
    private float curTime=0.f;
    static private Paint paint=new Paint();
    private RectF BoundingRect;
    private boolean bisInit=false;

    private int type;
    Vector2 Pos;

    private Laser(Vector2 Pos, int type){
        this.type=type;
        this.Pos=Pos;
        this.BoundingRect=new RectF();
        this.paint.setColor(Color.YELLOW);

        if(type==0) {
            this.bitmap = new GameBitmap(R.mipmap.laser_x);
            this.bitmap.Set_Scale(4000.f,30.f);

            BoundingRect.set(this.Pos.x-2000,this.Pos.y-15
                    ,this.Pos.x+2000,this.Pos.y+15);
        }
        else if(type==1) {
            this.bitmap = new GameBitmap(R.mipmap.laser_y);
            this.bitmap.Set_Scale(30.f,4000.f);

            BoundingRect.set(this.Pos.x-15,this.Pos.y-2000
                    ,this.Pos.x+15,this.Pos.y+2000);
        }
    }

    public static Laser get(Vector2 Pos, int type){

        MainGame game= MainGame.get();
        Laser laser =(Laser)game.get(Laser.class);

        if(laser ==null)
            return new Laser(Pos,type);

        laser.init(Pos,type);
        return laser;
    }

    private void init( Vector2 Pos, int type) {
        curTime=0.f;
        this.Pos=Pos;
        this.bisInit=false;
        this.type=type;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        curTime+=game.frameTime;
        if(curTime>=LIFETIME)
            game.remove(this);

        if(!bisInit) {
            bisInit=true;
            Player player = game.player;
        }

    }

    @Override
    public void draw(Canvas canvas) {



        bitmap.draw(canvas,Pos.x,Pos.y);
        //canvas.drawRect(BoundingRect,paint);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        rect.set(BoundingRect);
        //bitmap.getBoundingRect(Pos.x, Pos.y, rect);
    }


}

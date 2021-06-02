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

public class Warning implements GameObject, BoxCollidable {
    private static final String TAG = Warning.class.getSimpleName();

    private GameBitmap bitmap = null;

    private float LIFETIME=3.f;
    private float curTime=0.f;
    private boolean bisInit=false;
    Vector2 Pos;
    private int type;
    private Warning(Vector2 Pos, int type){
        this.type=type;
        this.Pos=Pos;
        if(type==0) {
            this.bitmap = new GameBitmap(R.mipmap.warnx);
        }
        else if(type==1) {
            this.bitmap = new GameBitmap(R.mipmap.warny);
        }
        this.bitmap.Set_Scale(90.f,90.f);
    }
    public static Warning get(Vector2 Pos, int type){
        MainGame game= MainGame.get();
        Warning warning =(Warning)game.get(Warning.class);

        if(warning ==null)
            return new Warning(Pos,type);

        warning.init(Pos,type);
        return warning;
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
        if(curTime>=LIFETIME) {
            Laser laser = Laser.get(new Vector2(this.Pos.x,this.Pos.y),this.type);
            game.add(MainGame.Layer.Laser,laser);
            game.remove(this);
        }
        if(!bisInit) {
            bisInit=true;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas,Pos.x,Pos.y);


    }

    @Override
    public void getBoundingRect(RectF rect) {

    }

}

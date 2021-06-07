package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.BoxCollidable;

public class Warning_item implements GameObject, BoxCollidable {
    private static final String TAG = Warning_item.class.getSimpleName();

    private GameBitmap bitmap = null;
    static private Paint paint=new Paint();
    private boolean bisInit=false;
    Vector2 Pos;
    private RectF BoundingRect;
    private Warning_item(Vector2 Pos){

        this.paint.setColor(Color.YELLOW);

        this.Pos=Pos;
        this.BoundingRect=new RectF();
        this.bitmap = new GameBitmap(R.mipmap.laser_item);
        this.bitmap.Set_Scale(90.f,90.f);

        BoundingRect.set(this.Pos.x-45,this.Pos.y-45
                ,this.Pos.x+45,this.Pos.y+45);
    }
    public static Warning_item get(Vector2 Pos){
        MainGame game= MainGame.get();
        Warning_item warning =(Warning_item)game.get(Warning_item.class);

        if(warning ==null)
            return new Warning_item(Pos);

        warning.init(Pos);
        return warning;
    }

    private void init( Vector2 Pos) {

        this.Pos=Pos;
        this.bisInit=false;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();


        if(!bisInit) {
            bisInit=true;
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
    }

}

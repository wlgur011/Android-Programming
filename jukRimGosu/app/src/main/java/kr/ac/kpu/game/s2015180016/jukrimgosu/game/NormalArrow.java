package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.Recyclable;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.BoxCollidable;

public class NormalArrow implements GameObject, BoxCollidable, Recyclable {
    private static final String TAG = NormalArrow.class.getSimpleName();
    private float x;
    private final GameBitmap bitmap;

    static private Paint paint=new Paint();
    private RectF BoundingRect;
    private boolean bisInit=false;
    private float y;
    private float speed;
    Vector2 Pos;
    private Vector2 Dir;
    private Vector2 BoundingRectOffSetDir;
    private float delta_x;
    private float delta_y;
    private Vector2 Angle;

    private NormalArrow(Vector2 Pos, float speed){
        this.Pos=Pos;
        this.speed= speed;
        this.bitmap = new GameBitmap(R.mipmap.arrow2);
        this.bitmap.Set_Scale(90.f,30.f);
        this.BoundingRect=new RectF();
        this.paint.setColor(Color.YELLOW);
    }

    public static NormalArrow get(Vector2 Pos, float speed){
        MainGame game= MainGame.get();
        NormalArrow normalArrow =(NormalArrow)game.get(NormalArrow.class);

        if(normalArrow ==null)
            return new NormalArrow(Pos,speed);


        normalArrow.init(Pos,speed);
        return normalArrow;
    }

    private void init(Vector2 Pos, float speed) {
        this.Pos=Pos;
        this.speed= speed;
        this.bisInit=false;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();


        if(!bisInit) {
            bisInit=true;
            Player player = game.player;
            Vector2 playerPos = new Vector2(player.getPos());
            Dir = playerPos.sub(Pos);
            delta_x =  player.getPos().x-this.Pos.x;
            delta_y =  player.getPos().y-this.Pos.y;
            Angle=new Vector2(delta_x,delta_y);
            Angle.nor();
            BoundingRectOffSetDir=new Vector2();
            Dir.nor();

        }
        Dir.nor();
        Pos.add(Dir.mul(this.speed));
        BoundingRectOffSetDir.set(Pos);
        Dir.nor();
        BoundingRectOffSetDir.add(Dir.mul(30));
        BoundingRect.set(BoundingRectOffSetDir.x-5,BoundingRectOffSetDir.y-5
                ,BoundingRectOffSetDir.x+5,BoundingRectOffSetDir.y+5);

        if (Pos.x < 0 ||Pos.x >1600 || Pos.y<0 || Pos.y >1600) {
            game.remove(this);

        }

    }

    @Override
    public void draw(Canvas canvas) {

        float angle = (float) Math.atan2(Angle.y, Angle.x);
        float degree = (float) (angle * 180 / Math.PI);
        canvas.save();
        canvas.drawRect(BoundingRect,paint);
        canvas.rotate(degree, Pos.x, Pos.y);
        bitmap.draw(canvas,Pos.x,Pos.y);

        canvas.restore();

    }

    @Override
    public void getBoundingRect(RectF rect)
    {
        rect.set(BoundingRect);
        //bitmap.getBoundingRect(Pos.x, Pos.y, rect);
    }


    @Override
    public void recycle() {

    }
}

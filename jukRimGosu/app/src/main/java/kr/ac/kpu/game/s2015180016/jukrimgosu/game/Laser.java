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

public class Laser implements GameObject, BoxCollidable, Recyclable {
    private static final String TAG = Laser.class.getSimpleName();
    private GameBitmap bitmap;

    static private Paint paint=new Paint();
    private RectF BoundingRect;
    private boolean bisInit=false;

    private int type;
    Vector2 Pos;

    private Laser(Vector2 Pos, int type){
        this.type=type;
        this.Pos=Pos;
        this.bitmap = new GameBitmap(R.mipmap.arrow2);
        this.bitmap.Set_Scale(90.f,30.f);
        this.BoundingRect=new RectF();
        this.paint.setColor(Color.YELLOW);

        if(type==0) {
            this.bitmap = new GameBitmap(R.mipmap.laser_x);
        }
        else if(type==1) {
            this.bitmap = new GameBitmap(R.mipmap.laser_y);
        }
    }

    public static Laser get(Vector2 Pos, int type){
        MainGame game= MainGame.get();
        Laser normalArrow =(Laser)game.get(Laser.class);

        if(normalArrow ==null)
            return new Laser(Pos,type);


        normalArrow.init(Pos,type);
        return normalArrow;
    }

    private void init( Vector2 Pos, int type) {
        this.Pos=Pos;
        this.bisInit=false;
        this.type=type;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();


        if(!bisInit) {
            bisInit=true;
            Player player = game.player;
            Vector2 playerPos = new Vector2(player.getPos());



        }

        //game.remove(this);

    }

    @Override
    public void draw(Canvas canvas) {


        canvas.drawRect(BoundingRect,paint);
        bitmap.draw(canvas,Pos.x,Pos.y);

    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(Pos.x, Pos.y, rect);
    }


    @Override
    public void recycle() {

    }
}

package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.Recyclable;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.BoxCollidable;

public class GuideArrow implements GameObject, BoxCollidable, Recyclable {
    private static final String TAG = GuideArrow.class.getSimpleName();
    private float x;
    private final GameBitmap bitmap;

    private boolean bisInit=false;
    private float y;
    private int speed;
    Vector2 Pos;
    private Vector2 Dir;
    private Vector2 Angle;
    private float delta_x;
    private float delta_y;
    private MainGame game;
    private Player player;
    private Vector2 playerPos;

    private GuideArrow(Vector2 Pos, int speed){
        this.Pos=Pos;
        this.speed= speed;
        this.bitmap = new GameBitmap(R.mipmap.arrow1);
        this.bitmap.Set_Scale(90.f,30.f);
    }
    //  private static ArrayList<Bullet> recycleBin =new ArrayList<>();
    public static GuideArrow get(Vector2 Pos, int speed){
        MainGame game= MainGame.get();
        GuideArrow normalArrow =(GuideArrow)game.get(GuideArrow.class);
        if(normalArrow ==null)
            return new GuideArrow(Pos,speed);

        normalArrow.init(Pos,speed);
        return normalArrow;
    }

    private void init( Vector2 Pos, int speed) {
        this.Pos=Pos;
        this.speed= speed;


        MainGame game= MainGame.get();

    }

    @Override
    public void update() {

        if(!bisInit) {
            game = MainGame.get();
            bisInit=true;
            this.Angle=new Vector2(0,0);
            this.Dir=new Vector2();
            this.playerPos=new Vector2();
            game=MainGame.get();
            player = game.player;
        }

        playerPos.set(player.getPos());
        Dir.set(playerPos.sub(Pos));
        Dir.nor();

        Pos.add(Dir.mul(this.speed));

        delta_x =  player.getPos().x-this.Pos.x;
        delta_y =  player.getPos().y-this.Pos.y;
        Angle.set(delta_x,delta_y);
        Angle.nor();
        //game.remove(this);

    }

    @Override
    public void draw(Canvas canvas) {
        float angle = (float) Math.atan2(Angle.y, Angle.x);
        float degree = (float) (angle * 180 / Math.PI);
        canvas.save();
        canvas.rotate(degree, Pos.x, Pos.y);
        bitmap.draw(canvas,Pos.x,Pos.y);
        canvas.restore();

    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(Pos.x, Pos.y, rect);
    }


    @Override
    public void recycle() {

    }
}

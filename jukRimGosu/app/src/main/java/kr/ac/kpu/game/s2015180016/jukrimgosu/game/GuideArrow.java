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

    private GuideArrow(Vector2 Pos, int speed){
        this.Pos=Pos;
        this.speed= -speed;
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
        this.speed= -speed;
        MainGame game= MainGame.get();

    }

    @Override
    public void update() {
        MainGame game = MainGame.get();

        Player player = game.player;
        Vector2 playerPos = new Vector2(player.getPos());
        Dir = playerPos.sub(Pos);
        Dir.nor();

        Pos.add(Dir);
        //game.remove(this);

    }

    @Override
    public void draw(Canvas canvas) {
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

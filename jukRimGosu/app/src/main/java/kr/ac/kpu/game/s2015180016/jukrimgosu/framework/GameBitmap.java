package kr.ac.kpu.game.s2015180016.jukrimgosu.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;


public class GameBitmap {
    private static HashMap<Integer,Bitmap> bitmaps=new HashMap<Integer, Bitmap>();

    public static Bitmap load(int resId) {
        Bitmap bitmap  = bitmaps.get(resId);
        if(bitmap == null){
            Resources res = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bitmap = BitmapFactory.decodeResource(res, resId, opts);
            bitmaps.put(resId,bitmap);
        }
        return bitmap;
    }

    protected final Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float Sx=50.f;
    protected float Sy=50.f;
    public GameBitmap(int resID){
        bitmap =load(resID);

    }

    private int halfWidth;
    private int halfHeight;

    public void draw(Canvas canvas, float x, float y){
        int hw = getWidth()/2;
        int hh =  getHeight()/2;

        dstRect.set(x-Sx*0.5f,y-Sy*0.5f,x+Sx*0.5f,y+Sy*0.5f);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public int getHeight() {
        return bitmap.getWidth();
    }

    public int getWidth() {
        return bitmap.getHeight();
    }
    public void getBoundingRect(float x,float y,RectF rect) {
        int hw = getWidth()/2;
        int hh = getHeight()/2;
        float dl = x-hw* GameView.MULTIPLIER;
        float dt = y-hh *GameView.MULTIPLIER;
        float dr =  x+hw*GameView.MULTIPLIER;
        float db =  y+hh*GameView.MULTIPLIER;

        rect.set(dl,dt,dr,db);
    }
    public void Set_Scale(float Sx,float Sy)
    {
        this.Sx=Sx;
        this.Sy=Sy;
    }
}

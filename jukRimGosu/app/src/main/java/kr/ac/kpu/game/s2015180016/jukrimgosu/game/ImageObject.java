package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class ImageObject implements GameObject {
    private final Bitmap bitmap;

    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    public boolean isGameOver=false;
    public ImageObject(int resId, float x, float y) {
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0, 0, w, h);
        float l = x - w / 2 * GameView.MULTIPLIER;
        float t = y - h / 2 * GameView.MULTIPLIER;
        float r = x + w / 2 * GameView.MULTIPLIER;
        float b = y + h / 2 * GameView.MULTIPLIER;
        dstRect.set(l, t, r, b);
    }
    @Override
    public void update() {

        if(isGameOver)
        {
            isGameOver=false;
            GameView.view.pauseGame();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}

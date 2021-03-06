package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameBitmap;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class Score implements GameObject {
    private final Bitmap bitmap;
    private final int right;
    private final int top;

    private float CurrScore=0.f;
    public void setScore(int score) {
        this.score = score;
        this.displayScore = score;
    }
    public void addScore(int amount) {
        this.CurrScore += amount;
    }

    private int score, displayScore;
    private Rect src = new Rect();
    private RectF dst = new RectF();

    public Score(int right, int top) {
        bitmap = GameBitmap.load(R.mipmap.number_24x32);
        this.right = right;
        this.top = top;
    }
    @Override
    public void update() {
        MainGame game=MainGame.get();
        CurrScore+=game.frameTime*100f;
        score= (int) CurrScore;
        if (displayScore < score) {
            displayScore+=10;
        }

    }

    public void Init()
    {
        CurrScore=0.f;
        score=0;
        displayScore=0;
    }
    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        int nw = bitmap.getWidth() / 10;
        int nh = bitmap.getHeight();
        int x = right;
        int dw = (int) (nw * GameView.MULTIPLIER);
        int dh = (int) (nh * GameView.MULTIPLIER);
        while (value > 0) {
            int digit = value % 10;
            src.set(digit * nw, 0, (digit + 1) * nw, nh);
            x -= dw;
            dst.set(x, top, x + dw, top + dh);
            canvas.drawBitmap(bitmap, src, dst, null);
            value /= 10;
        }
    }

}

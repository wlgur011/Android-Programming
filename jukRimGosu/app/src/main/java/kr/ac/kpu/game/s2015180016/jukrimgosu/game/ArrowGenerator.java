package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class ArrowGenerator implements GameObject {

    private static final float INITIAL_SPAWN_INTERVAL = 5.0f;
    private static final String TAG = ArrowGenerator.class.getSimpleName();
    private final MainGame game;
    private float time;
    private float spawnInterval;
    private int wave;

    public ArrowGenerator() {
        time = INITIAL_SPAWN_INTERVAL;
        spawnInterval = INITIAL_SPAWN_INTERVAL;
         game = MainGame.get();
    }
    @Override
    public void update() {

        time += game.frameTime*5.f;
        if (time >= spawnInterval) {
            generate();
            Pattern1();
            time -= spawnInterval;

        }
    }
    private void Pattern1()
    {
        for(int i=0; i<9; i++)
        {
            float hw= GameView.view.getWidth();
            float hh= GameView.view.getHeight();
            float x = (float) (hw*0.5f + 800.f * Math.cos(i*40.f* Math.PI/180.f));
            float y = (float) (hh*0.5f + 800.f * Math.sin(i*40.f* Math.PI/180.f));
            NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),3);
            game.add(MainGame.Layer.N_Arrow,Arrow);
        }
    }
    private void generate() {
        wave+=10;
        MainGame game = MainGame.get();
        Random r = new Random();
        int RandInt=r.nextInt(360);

        float hw= GameView.view.getWidth();
        float hh= GameView.view.getHeight();


        float x = (float) (hw*0.5f + 800.f * Math.cos(RandInt* Math.PI/180.f));
        float y = (float) (hh*0.5f + 800.f * Math.sin(RandInt* Math.PI/180.f));
        NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),3);
        game.add(MainGame.Layer.N_Arrow,Arrow);
        GuideArrow Arrow2 = GuideArrow.get(new Vector2(x,y),3);
        game.add(MainGame.Layer.G_Arrow,Arrow2);
    }

    @Override
    public void draw(Canvas canvas) {
        // does nothing
    }
}


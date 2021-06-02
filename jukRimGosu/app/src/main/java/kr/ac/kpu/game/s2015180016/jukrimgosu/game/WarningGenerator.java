package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class WarningGenerator implements GameObject {

    private static final float INITIAL_SPAWN_INTERVAL = 5.0f;
    private static final String TAG = WarningGenerator.class.getSimpleName();
    private final MainGame game;
    private float time;
    private float spawnInterval;

    public WarningGenerator() {
        time = INITIAL_SPAWN_INTERVAL;
        spawnInterval = INITIAL_SPAWN_INTERVAL;
         game = MainGame.get();
    }
    @Override
    public void update() {

        time += game.frameTime*5.f;
        if (time >= spawnInterval) {
            generate();
            time -= spawnInterval;

        }
    }
    private void generate() {
        MainGame game = MainGame.get();
        Random r = new Random();
        int RandInt=r.nextInt(360);

        float hw= GameView.view.getWidth();
        float hh= GameView.view.getHeight();

        float RandRadius=r.nextInt(1200)-600;

        float x = (float) (hw*0.5f + RandRadius * Math.cos(RandInt* Math.PI/180.f));
        float y = (float) (hh*0.5f + RandRadius * Math.sin(RandInt* Math.PI/180.f));
     //NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),3);
     //game.add(MainGame.Layer.N_Arrow,Arrow);

    }

    @Override
    public void draw(Canvas canvas) {
        // does nothing
    }
}


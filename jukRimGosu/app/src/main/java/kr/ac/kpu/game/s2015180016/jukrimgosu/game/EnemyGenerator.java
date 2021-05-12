package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class EnemyGenerator implements GameObject {

    private static final float INITIAL_SPAWN_INTERVAL = 5.0f;
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private float time;
    private float spawnInterval;
    private int wave;

    public EnemyGenerator() {
        time = INITIAL_SPAWN_INTERVAL;
        spawnInterval = INITIAL_SPAWN_INTERVAL;
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        time += game.frameTime;
        if (time >= spawnInterval) {
            generate();
            time -= spawnInterval;
        }
    }

    private void generate() {
        wave++;
        //Log.d(TAG, "Generate now !!");
        MainGame game = MainGame.get();
        int tenth = GameView.view.getWidth() / 10;
        Random r = new Random();
        for (int i = 1; i <= 9; i += 2) {
            int x = tenth * i;
            int y = 0;
            int level = wave / 1 -r.nextInt(3);
            if(level<1)level=1;
            if(level>20)level=20;
            Enemy enemy =  Enemy.get(level, x, y, 700);
            game.add(MainGame.Layer.enemy,enemy);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // does nothing
    }
}

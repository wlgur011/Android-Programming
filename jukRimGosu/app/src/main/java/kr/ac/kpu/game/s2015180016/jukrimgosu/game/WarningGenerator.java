package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class WarningGenerator implements GameObject {


    private static final String TAG = WarningGenerator.class.getSimpleName();
    private final MainGame game;
    public float totalTime;
    public float warningTime=0.f;

    public WarningGenerator() {

         game = MainGame.get();
    }
    @Override
    public void update() {

        totalTime += game.frameTime;

        if(totalTime>15.f) {
            warningTime+=game.frameTime;
            if(warningTime>3.f){
                warningTime=0.f;
                generate();
            }

        }
    }
    private void generate() {
        MainGame game = MainGame.get();
        Random r = new Random();
        int RandInt=r.nextInt(360);
        int RandType=r.nextInt(2);
        float hw= GameView.view.getWidth();
        float hh= GameView.view.getHeight();

        float RandRadius=r.nextInt(1200)-600;

        float x = (float) (hw*0.5f + RandRadius * Math.cos(RandInt* Math.PI/180.f));
        float y = (float) (hh*0.5f + RandRadius * Math.sin(RandInt* Math.PI/180.f));

        int RandType2=r.nextInt(5);
        if(RandType2==3) {
            Warning_item warning= Warning_item.get(new Vector2(x,y));
            game.add(MainGame.Layer.Warning_item,warning);
        }
        else {
        Warning warning = Warning.get(new Vector2(x,y),RandType);
        game.add(MainGame.Layer.Warning,warning);
        }
     //NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),3);
     //game.add(MainGame.Layer.N_Arrow,Arrow);

    }

    @Override
    public void draw(Canvas canvas) {
        // does nothing
    }
}


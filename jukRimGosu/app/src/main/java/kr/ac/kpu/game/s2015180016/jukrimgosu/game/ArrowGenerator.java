package kr.ac.kpu.game.s2015180016.jukrimgosu.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;

public class ArrowGenerator implements GameObject {


    private static final String TAG = ArrowGenerator.class.getSimpleName();
    private final MainGame game;

    private float totalTime;
    private float time[]={0.0f,0.f,0.f};
    private float normalArrowTerm=1.f;
    private float normalArrowSpeed=2.f;
    public ArrowGenerator() {
        normalArrowSpeed=2.f;
         game = MainGame.get();
    }
    public void Init()
    {
        totalTime=0.f;
        normalArrowTerm=1.f;
        normalArrowSpeed=2.f;
    }
    @Override
    public void update() {
        totalTime+= game.frameTime;

        time[0] += game.frameTime;
        if (time[0] >=normalArrowTerm) {
            generate();
            time[0]=0.f;
        }
        if (totalTime>10.f) {
            time[1] += game.frameTime;
            if (time[1] >= 2.f) {
                normalArrowTerm=0.8f;
                normalArrowSpeed=3.f;
                Pattern1();
                time[1]=0.f;
            }
        }
        if (totalTime>20.f) {
            time[2] += game.frameTime;
            if(time[2]>5.f)
            {
                time[2]=0.f;
                Pattern2();
            }
            normalArrowTerm=0.5f;
            normalArrowSpeed=4.f;
        }
        if (totalTime>25.f) {
            if(normalArrowTerm>0.3f){
            normalArrowTerm=0.5f-totalTime*0.002f;}
            if(normalArrowSpeed<10.f){
            normalArrowSpeed=4.f+totalTime*0.03f;}
        }
    }
    private void Pattern1()
    {
        Random r = new Random();
        int RandInt=r.nextInt(360);
        float hw= GameView.view.getWidth();
        float hh= GameView.view.getHeight();
        float x = (float) (hw*0.5f + 800.f * Math.cos(RandInt* Math.PI/180.f));
        float y = (float) (hh*0.5f + 800.f * Math.sin(RandInt* Math.PI/180.f));
        GuideArrow Arrow2 = GuideArrow.get(new Vector2(x,y),1.5f);
        game.add(MainGame.Layer.G_Arrow,Arrow2);
    }
    private void Pattern2()
    {
        for(int i=0; i<9; i++)
        {
            float hw= GameView.view.getWidth();
            float hh= GameView.view.getHeight();
            float x = (float) (hw*0.5f + 800.f * Math.cos(i*40.f* Math.PI/180.f));
            float y = (float) (hh*0.5f + 800.f * Math.sin(i*40.f* Math.PI/180.f));
            NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),5);
            game.add(MainGame.Layer.N_Arrow,Arrow);
        }
    }
    private void generate() {

        Random r = new Random();
        int RandInt=r.nextInt(360);
        float hw= GameView.view.getWidth();
        float hh= GameView.view.getHeight();
        float x = (float) (hw*0.5f + 800.f * Math.cos(RandInt* Math.PI/180.f));
        float y = (float) (hh*0.5f + 800.f * Math.sin(RandInt* Math.PI/180.f));
        NormalArrow Arrow = NormalArrow.get(new Vector2(x,y),normalArrowSpeed);
        game.add(MainGame.Layer.N_Arrow,Arrow);

    }

    @Override
    public void draw(Canvas canvas) {
        // does nothing
    }
}


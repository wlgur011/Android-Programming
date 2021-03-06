package kr.ac.kpu.game.s2015180016.jukrimgosu.game;


import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.GameObject;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.Recyclable;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.activity.MainActivity;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;
import kr.ac.kpu.game.s2015180016.jukrimgosu.utils.CollisionHelper;


public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    private static MainGame instance;
    public Player player;
    private Score score;

    public static MainGame get() {

        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }
    public float frameTime;
    private boolean initialized;

    private  ArrowGenerator arrowGenerator;
    private WarningGenerator warningGenerator;
    //    Player player;
    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recycleBin.put(clazz, array);
        }
        array.add(object);
    }
    public GameObject get(Class clazz) {
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null || array.isEmpty()) return null;
        return array.remove(0);
    }

    public enum Layer {
        bg1, N_Arrow,G_Arrow,Warning,Warning_item,Laser, player, ui,bg2, controller, ENEMY_COUNT;
    }
    public boolean initResources() {
        if (initialized) {
            return false;
        }

        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.ENEMY_COUNT.ordinal());

        player = new Player(new Vector2(w/2, h - 700));
        add(Layer.player, player);

        int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);

        ImageObject bg = new ImageObject(R.mipmap.back,w/2 ,h/2);
        add(Layer.bg1, bg);

        arrowGenerator=new ArrowGenerator();
        add(Layer.controller, arrowGenerator);
        warningGenerator=new WarningGenerator();
        add(Layer.controller, warningGenerator);
        initialized = true;

        GameView.view.mediaPlayer.start();
        return true;
    }

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    private ImageObject GameOver;
    void makeGameOver()
    {  int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        GameOver= new ImageObject(R.mipmap.gameover,w/2 ,h/2);
        GameOver.isGameOver=true;
        add(Layer.bg2, GameOver);
    }
    public void update() {

        //if (!initialized) return;
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }

        ArrayList<GameObject> player = layers.get(Layer.player.ordinal());
        ArrayList<GameObject> Arrows = layers.get(Layer.N_Arrow.ordinal());
        ArrayList<GameObject> Item = layers.get(Layer.Warning_item.ordinal());
        ArrayList<GameObject> GArrows = layers.get(Layer.G_Arrow.ordinal());
        ArrayList<GameObject> lasers = layers.get(Layer.Laser.ordinal());

        for (GameObject o1: lasers) {
            Laser laser = (Laser) o1;
            boolean collided = false;
            for (GameObject o2: Arrows ) {
                NormalArrow NArrow = (NormalArrow) o2;
                if (CollisionHelper.collides(NArrow, laser)) {
                    remove(NArrow, false);
                    collided = true;
                    score.addScore(20);
                    break;
                }
            }
            for (GameObject o2: GArrows ) {
                GuideArrow GArrow = (GuideArrow) o2;
                if (CollisionHelper.collides(GArrow, laser)) {
                    remove(GArrow, false);
                    collided = true;
                    score.addScore(100);
                    break;
                }
            }
            for (GameObject o2: player ) {
                Player tPlayer = (Player) o2;
                if (CollisionHelper.collides(tPlayer, laser)) {
                    collided = true;
                    makeGameOver();
                    break;
                }
            }
            if (collided) {
                break;
            }
        }
      for (GameObject o1: player) {
          Player TPlayer = (Player) o1;

          boolean collided = false;

          for (GameObject o2: Item ) {
              Warning_item item = (Warning_item) o2;
              if (CollisionHelper.collides(item, TPlayer)) {
                  TPlayer.roundLaser();
                  remove(item, false);
                  break;
              }
          }
          for (GameObject o2: Arrows ) {
              NormalArrow NArrow = (NormalArrow) o2;
              if (CollisionHelper.collides(NArrow, TPlayer)) {
                  remove(NArrow, false);
                  collided = true;

                  break;
              }
          }
          for (GameObject o2: GArrows ) {
              GuideArrow GArrow = (GuideArrow) o2;
              if (CollisionHelper.collides(GArrow, TPlayer)) {
                  remove(GArrow, false);
                  collided = true;

                  break;
              }
          }
          if (collided) {
              makeGameOver();
              break;
          }
      }

    }
    public void AllRemove()
    {
        ArrayList<GameObject> Arrows = layers.get(Layer.N_Arrow.ordinal());
        ArrayList<GameObject> Item = layers.get(Layer.Warning_item.ordinal());
        ArrayList<GameObject> GArrows = layers.get(Layer.G_Arrow.ordinal());
        ArrayList<GameObject> lasers = layers.get(Layer.Laser.ordinal());
        ArrayList<GameObject> bg2 = layers.get(Layer.bg2.ordinal());

        GameView.view.mediaPlayer.seekTo(0);
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        score.setScore(0);
        score.Init();
        warningGenerator.totalTime=0.f;
        warningGenerator.warningTime=0.f;
        arrowGenerator.Init();
        player.SetPos(w/2, h - 700);
        for (GameObject o : Arrows) {
            remove(o,true); }
        for (GameObject o : Item) {
            remove(o,true); }
        for (GameObject o : GArrows) {
            remove(o,true); }
        for (GameObject o : lasers) {
            remove(o,true); }
        for (GameObject o : bg2) {
            remove(o,true); }
    }
    public Canvas mCanvas;
    public void draw(Canvas canvas) {
        mCanvas=canvas;
        //if (!initialized) return;
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {

            return true;
        }
        return false;
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof Recyclable) {
                            ((Recyclable) gameObject).recycle();
                            recycle(gameObject);
                        }
                        //Log.d(TAG, "Removed: " + gameObject);
                        break;
                    }
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }
}
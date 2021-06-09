package kr.ac.kpu.game.s2015180016.jukrimgosu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kpu.game.s2015180016.jukrimgosu.R;
import kr.ac.kpu.game.s2015180016.jukrimgosu.framework.JoyStickClass;
import kr.ac.kpu.game.s2015180016.jukrimgosu.ui.view.GameView;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RelativeLayout layout_joystick;
    ImageView image_joystick,image_border;
    public static JoyStickClass js;
    TextView textView1, textView2, textView3, textView4, textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        GameView.MULTIPLIER = metrics.density * 0.8f;
        Log.d(TAG, "Density: " + metrics.density + " DPI:" + metrics.densityDpi + " Multiplier:" + GameView.MULTIPLIER);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);


        //textView1 = (TextView)findViewById(R.id.textView1);
        //textView2 = (TextView)findViewById(R.id.textView2);
        //textView3 = (TextView)findViewById(R.id.textView3);
        //textView4 = (TextView)findViewById(R.id.textView4);
        //textView5 = (TextView)findViewById(R.id.textView5);

        js = new JoyStickClass(getApplicationContext(), layout_joystick, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                //if(arg1.getAction() == MotionEvent.ACTION_DOWN
                //        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                //    textView1.setText("X : " + String.valueOf(js.getX()));
                //    textView2.setText("Y : " + String.valueOf(js.getY()));
                //    textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                //    textView4.setText("Distance : " + String.valueOf(js.getDistance()));
//
                //    int direction = js.get8Direction();
                //    if(direction == JoyStickClass.STICK_UP) {
                //        textView5.setText("Direction : Up");
                //    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                //        textView5.setText("Direction : Up Right");
                //    } else if(direction == JoyStickClass.STICK_RIGHT) {
                //        textView5.setText("Direction : Right");
                //    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                //        textView5.setText("Direction : D R");
                //    } else if(direction == JoyStickClass.STICK_DOWN) {
                //        textView5.setText("Direction : D");
                //    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                //        textView5.setText("Direction : D L");
                //    } else if(direction == JoyStickClass.STICK_LEFT) {
                //        textView5.setText("Direction : Left");
                //    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                //        textView5.setText("Direction : Up Left");
                //    } else if(direction == JoyStickClass.STICK_NONE) {
                //        textView5.setText("Direction : Center");
                //    }
                //} else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                //    textView1.setText("X :");
                //    textView2.setText("Y :");
                //    textView3.setText("Angle :");
                //    textView4.setText("Distance :");
                //    textView5.setText("Direction :");
                //}
                return true;
            }
        });
    }
    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

}
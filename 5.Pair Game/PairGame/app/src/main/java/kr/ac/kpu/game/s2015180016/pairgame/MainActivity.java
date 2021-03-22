package kr.ac.kpu.game.s2015180016.pairgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = MainActivity.class.getSimpleName();//"MainActivity";

    private static final int buttonIds[] = {
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33,
            R.id.card_40, R.id.card_41, R.id.card_42, R.id.card_43
    };
    private int[] cards = {
            R.mipmap.cat1,  R.mipmap.cat1,  R.mipmap.cat2,  R.mipmap.cat2,
            R.mipmap.cat3,  R.mipmap.cat3,  R.mipmap.cat4,  R.mipmap.cat4,
            R.mipmap.cat5,  R.mipmap.cat5,  R.mipmap.cat6,  R.mipmap.cat6,
            R.mipmap.cat7,  R.mipmap.cat7,  R.mipmap.cat8,  R.mipmap.cat8,
            R.mipmap.cat9,  R.mipmap.cat9,  R.mipmap.cat10,  R.mipmap.cat10
    };
    private ImageButton prevBtn;
    private int visibleCardCount = cards.length;
    private TextView scoreTextView;


    private int flips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView= findViewById(R.id.scoreTextView);
        startGame();

    }

    public void setFlips(int flips) {
        this.flips = flips;
        scoreTextView.setText("Flips :" + flips);
    }


    public void OnBtnCard(View view) {
        if(view == prevBtn) {
            int color=getResources().getColor(R.color.purple_700);
            scoreTextView.setTextColor(color);
            return;
        }

        scoreTextView.setTextColor(R.color.gray);

        setFlips(flips+1);
        int prevCard =0;
        if(prevBtn!=null) {
            prevBtn.setImageResource(R.mipmap.card_blue_back);
            prevCard = (Integer)prevBtn.getTag();
        }
        Log.d( TAG,"OnBtnCard() has been Called" + view.getId());

        int buttonIndex = getButtonIndex(view.getId());
        int card = cards[buttonIndex];
        ImageButton imageButton =(ImageButton)view;
        imageButton.setImageResource(card);

        if(card == prevCard){
            setFlips(flips+1);
            visibleCardCount-=2;
            imageButton.setVisibility(View.INVISIBLE);
            prevBtn.setVisibility(View.INVISIBLE);
            prevBtn=null;

            if(visibleCardCount==0)
                askRestart();;
            return;
        }

        prevBtn = imageButton;
    }

    private int getButtonIndex(int resId) {
        for (int i = 0; i < buttonIds.length; i++) {
        if(buttonIds[i]==resId) {
            return i;
            }
        }
        return -1;
    }

    public void OnBtnRestart(View view) {
        askRestart();
    }

    private void askRestart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // String title = getResources().getString(R.string.restart_dialog_title);

       // builder.setTitle(title);
        builder.setTitle(R.string.restart_dialog_title);
        builder.setMessage(R.string.restart_dialog_message);
        builder.setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });
        builder.setNegativeButton(R.string.common_no,null);

        AlertDialog alert =builder.create();
        alert.show();
    }

    private void startGame() {
        setFlips(0);
        Random random = new Random();
        for(int i=0; i< cards.length; i++){
            int ri = random.nextInt(cards.length);
            int temp = cards[i];
            cards[i]=cards[ri];
            cards[ri]=temp;
        }
        for(int i=0; i<buttonIds.length; i++){
            ImageButton b = findViewById(buttonIds[i]);
            b.setImageResource(R.mipmap.card_blue_back);
            b.setTag(cards[i]);
            b.setVisibility(View.VISIBLE);

        }
        prevBtn=null;
    }
}
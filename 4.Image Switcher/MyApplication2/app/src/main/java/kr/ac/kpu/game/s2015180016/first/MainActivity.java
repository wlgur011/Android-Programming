package kr.ac.kpu.game.s2015180016.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {


    private TextView mainTextView;
    private ImageView mainImageView;
    private ImageButton nextImageBtn;
    private ImageButton prevImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView  = findViewById(R.id.mainTextView);
        mainTextView.setText("3 / 5");

        mainImageView = findViewById(R.id.mainImageView);
        nextImageBtn=findViewById(R.id.nextImageBtn);
        prevImageBtn=findViewById(R.id.prevImageBtn);
    }

    int resourceAry [] = {R.mipmap.cat1,R.mipmap.cat2,R.mipmap.cat3,R.mipmap.cat4,R.mipmap.cat5};
    int nextBtnAry [] = {R.mipmap.next_p,R.mipmap.next,R.mipmap.next_d};
    int prevBtnAry [] = {R.mipmap.prev_p,R.mipmap.prev,R.mipmap.prev_d};
    int idx=2;
    int page=idx+1;


    public void onBtnPrev(View view) {
        nextImageBtn.setImageResource(nextBtnAry[1]);
        if(idx<=0) {
            idx=0;
            page=idx+1;
            prevImageBtn.setImageResource(prevBtnAry[2]);
        }
        else  {
            idx-=1;
            page-=1;
            if(idx<=0)
                prevImageBtn.setImageResource(prevBtnAry[2]);
            else
                prevImageBtn.setImageResource(prevBtnAry[0]);
        }
        mainTextView.setText( Integer.toString(page) +" / 5");
        mainImageView.setImageResource(resourceAry[idx]);
    }
    public void onBtnNext(View view) {
        prevImageBtn.setImageResource(prevBtnAry[1]);
        if(idx>=4){
            idx=4;
            page=idx+1;
            nextImageBtn.setImageResource(nextBtnAry[2]);
        }
        else{
            idx+=1;
            page+=1;
            if(idx>=4)
                nextImageBtn.setImageResource(nextBtnAry[2]);
            else
                nextImageBtn.setImageResource(nextBtnAry[0]);
        }
        mainTextView.setText( Integer.toString(page) +" / 5");
        mainImageView.setImageResource(resourceAry[idx]);

    }
}
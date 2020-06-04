package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnStart,btnStop,btnResume;
    ImageView incosre;
    Animation rounding;
    Animation atg;
    Chronometer timeHeare;
    ImageView refreshImage;
    long pauseOfSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.button_start);
        incosre = findViewById(R.id.bg_sahem);
        btnStop = findViewById(R.id.button_stop);
        btnResume = findViewById(R.id.button_resume);
        timeHeare = findViewById(R.id.timer);
        refreshImage = findViewById(R.id.refreshImage);
        btnStop.setAlpha(0);
        refreshImage.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);

        rounding = AnimationUtils.loadAnimation(this,R.anim.rounding);
        atg = AnimationUtils.loadAnimation(this,R.anim.btg);
        btnStart.startAnimation(atg);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incosre.startAnimation(rounding);
                btnStop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStart.animate().alpha(0).setDuration(300).start();
                timeHeare.setBase(SystemClock.elapsedRealtime());
                timeHeare.start();
                btnStop.startAnimation(atg);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               timeHeare.stop();
              incosre.clearAnimation();
              pauseOfSet = SystemClock.elapsedRealtime() - timeHeare.getBase();
              refreshImage.setVisibility(View.VISIBLE);
              btnResume.setVisibility(View.VISIBLE);
              btnResume.startAnimation(atg);
              refreshImage.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    reload();
                  }
              });
            }
        });
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incosre.startAnimation(rounding);
                timeHeare.setBase(SystemClock.elapsedRealtime()-pauseOfSet);
                timeHeare.start();
            }
        });

    }
    public void reload(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
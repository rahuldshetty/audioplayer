package com.songapp.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {


    private Button english,konkani,audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        english = findViewById(R.id.logoEnglish);
        konkani = findViewById(R.id.logoKonkani);
        audio = findViewById(R.id.logoSongs);


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(1);
            }
        });

        konkani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(2);
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain(3);
            }
        });
    }

    void goToMain(int num){
        //1-english 2-konkani 3-music
        Intent act=new Intent(SplashActivity.this,MainActivity.class);
        act.putExtra("CLASS",num);
        startActivity(act);
    }

}

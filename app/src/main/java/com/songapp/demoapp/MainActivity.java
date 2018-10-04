package com.songapp.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.songapp.demoapp.texts.*;

import com.songapp.demoapp.songs.*;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainView;
    private FrameLayout mMainFrame;

    private TextsFragment textsFragment;
    private SongFragment songFragment;
    private WeekFragment weekFragment;


    public static int selectGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);
        mMainFrame=findViewById(R.id.main_frame);
        mMainView=findViewById(R.id.nav_view);


        textsFragment=new TextsFragment();
        songFragment=new SongFragment();
        weekFragment=new WeekFragment();


        mMainView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {


                    case R.id.navText:
                        mMainView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        setFragment(weekFragment);
                        return true;

                    case R.id.navSong:
                        mMainView.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
                        setFragment(songFragment);
                        return true;

                    default :
                        return false;
                }
            }
        });
        setFragment(weekFragment);





    }






    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}

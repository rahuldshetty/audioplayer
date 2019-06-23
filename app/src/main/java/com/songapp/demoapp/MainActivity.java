package com.songapp.demoapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.songapp.demoapp.texts.*;

import com.songapp.demoapp.songs.*;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainView;
    private FrameLayout mMainFrame;


    private TextsFragment textsFragment;
    private SongFragment songFragment;
    private KonkaniFragment konkaniFragment;

    public static MyPlayer player;
    public static int currentSong=-1;


    public static String group="";
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
        konkaniFragment=new KonkaniFragment();

        player=new MyPlayer();

        mMainView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.navKonkani:
                        setFragment(konkaniFragment);
                        group="KONKANI";
                        return true;

                    case R.id.navEnglish:
                        group="ENGLISH";
                        setFragment(textsFragment);
                        return true;

                    case R.id.navSong:
                        setFragment(songFragment);
                        return true;

                    default :
                        return false;
                }
            }
        });

        switch (getIntent().getIntExtra("CLASS",1)){
            case 1:
                setFragment(textsFragment);
                group="ENGLISH";
                mMainView.setSelectedItemId(R.id.navEnglish);
                break;
            case 2:
                setFragment(konkaniFragment);
                mMainView.setSelectedItemId(R.id.navKonkani);
                group="KONKANI";
                break;
            case 3:
                setFragment(songFragment);
                mMainView.setSelectedItemId(R.id.navSong);
                break;
        }


    }


    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}

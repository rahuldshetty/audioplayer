package com.songapp.demoapp.songs;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.songapp.demoapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPlayer {
    //control
    MediaPlayer player;
    private List<AssetFileDescriptor> songs;
    private AssetFileDescriptor currentsong;

    //view
    private ImageView playbutton;
    private TextView name;
    private ImageView image;
    private SeekBar seeker;
    private String[] songnames;

    MyPlayer(){
        player=new MediaPlayer();
    }

    void play(){
        player.start();
        playbutton.setImageResource(R.drawable.pause);
    }
    void next(){
        setsong(songs.get((songs.indexOf(currentsong)+1)%songs.size()));
    }
    void prev(){
        setsong(songs.get((songs.indexOf(currentsong)-1)%songs.size()));
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setsong(AssetFileDescriptor location){
        if(player.isPlaying()){
            player.stop();
        }
        currentsong=location;
        try {
            player.reset();
            player.setDataSource(currentsong.getFileDescriptor(),currentsong.getStartOffset(),currentsong.getLength());
            name.setText(songnames[songs.indexOf(currentsong)]);
            player.prepare();
            play();
        }catch (Exception e){

        }
    }
    public void setControls(final ImageView playpause){
        this.playbutton=playpause;
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    pause();
                }else play();
            }
        });
        playbutton.setImageResource(R.drawable.play);
    }

    public void setViews(CircleImageView songCover, TextView songname) {
        this.image=songCover;this.name=songname;
    }

    public void pause() {
        player.pause();
        playbutton.setImageResource(R.drawable.play);
    }

    public void setSongList(ArrayList<AssetFileDescriptor> songList, String[] songlist) {
        this.songnames=songlist;
        this.songs=songList;
        try {
            player.setDataSource(songs.get(0).getFileDescriptor(),songs.get(0).getStartOffset(),songs.get(0).getLength());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentsong=songs.get(0);
        name.setText(songnames[0]);
    }
    void seek(){
        if(player.isPlaying()){
        int length = player.getCurrentPosition()*100;
        seeker.setProgress(length/player.getDuration());}
    }

    public void setSeeker(SeekBar bar) {
        this.seeker=bar;
        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    float done=progress/100;
                    player.pause();
                    player.seekTo((player.getDuration()*progress)/100);
                    player.start();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seek();
            }
        }, 1000, 500);
    }
}

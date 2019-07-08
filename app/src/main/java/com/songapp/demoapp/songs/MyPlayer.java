package com.songapp.demoapp.songs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MyPlayer implements Serializable {
    //control
    MediaPlayer player;
    private List<AssetFileDescriptor> songs;
    private AssetFileDescriptor currentsong;

    TelephonyManager telephonyManager;

    static int[] songimageId={R.drawable.imgsample,R.drawable.imgsample,R.drawable.s5,R.drawable.s9,R.drawable.s4,R.drawable.s6,R.drawable.s2,R.drawable.s2,R.drawable.s1};
    //view
    private ImageView playbutton;
    private TextView name;

    public void setCoverImage(CircleImageView coverImage) {
        this.coverImage = coverImage;
    }

    private CircleImageView coverImage;
    private SeekBar seeker;
    private String[] songnames;

    private ImageView prevBtn,fwdBtn;

    public boolean isPlaying(){
        return player.isPlaying();
    }

    public  void reload(){

        if(MainActivity.currentSong==-1)
            return;

        long totalDuration=player.getDuration();
        String total=milliSecondsToTimer(totalDuration);
        endTimer.setText(total);

        if(MainActivity.currentSong==0)
        {
            prevBtn.setVisibility(View.INVISIBLE);
            prevBtn.setEnabled(false);
        }
        else if(MainActivity.currentSong==8)
        {
            fwdBtn.setVisibility(View.INVISIBLE);
            fwdBtn.setEnabled(false);
        }
        else{
            fwdBtn.setEnabled(true);
            prevBtn.setEnabled(true);
            fwdBtn.setVisibility(View.VISIBLE);
            prevBtn.setVisibility(View.VISIBLE);
        }


        if(MainActivity.currentSong!=-1)
         coverImage.setImageResource(songimageId[MainActivity.currentSong]);

        if(player.isPlaying())
        {
            pause();
            play();
        }
        else{
            pause();
            startTimer.setText(milliSecondsToTimer(player.getCurrentPosition()));


        }
    }

    void changeNextBackUI(){
        long duration=player.getDuration();
        String total=milliSecondsToTimer(duration);
        endTimer.setText(total);
        coverImage.setImageResource(songimageId[MainActivity.currentSong]);
    }

    public void setPrevBtn(ImageView prevBtn) {
        this.prevBtn = prevBtn;
    }

    public void setFwdBtn(ImageView fwdBtn) {
        this.fwdBtn = fwdBtn;
    }

    public void setEndTimer(TextView endTimer) {
        this.endTimer = endTimer;
    }

    private TextView endTimer;

    public void setStartTimer(TextView startTimer) {
        this.startTimer = startTimer;
    }

    private TextView startTimer;

    public MyPlayer(){
        player=new MediaPlayer();

    }

    void play(){
        player.start();
        startTimer.post(mUpdatetimer);
        playbutton.setImageResource(R.drawable.pause);
    }
    void next(){
        MainActivity.currentSong+=1;
        setsong(songs.get((songs.indexOf(currentsong)+1)%songs.size()));
        changeNextBackUI();
    }
    void prev(){
        MainActivity.currentSong-=1;
        setsong(songs.get((songs.indexOf(currentsong)-1)%songs.size()));
        changeNextBackUI();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setsong(AssetFileDescriptor location){

        if(MainActivity.currentSong==0)
        {
            fwdBtn.setEnabled(true);
            fwdBtn.setVisibility(View.VISIBLE);
            prevBtn.setVisibility(View.INVISIBLE);
            prevBtn.setEnabled(false);
        }
        else if(MainActivity.currentSong==8)
        {
            prevBtn.setEnabled(true);
            prevBtn.setVisibility(View.VISIBLE);
            fwdBtn.setVisibility(View.INVISIBLE);
            fwdBtn.setEnabled(false);
        }
        else{
            fwdBtn.setEnabled(true);
            prevBtn.setEnabled(true);
            fwdBtn.setVisibility(View.VISIBLE);
            prevBtn.setVisibility(View.VISIBLE);
        }

        if(player.isPlaying()){
            player.stop();
        }
        currentsong=location;
        try {
            player.reset();
            player.setDataSource(currentsong.getFileDescriptor(),currentsong.getStartOffset(),currentsong.getLength());
            name.setText(songnames[songs.indexOf(currentsong)].replace(".mp3","").replace("(","").replace(")",""));
            player.prepare();

            long totalDuration = player.getDuration();
            endTimer.setText(milliSecondsToTimer(totalDuration));
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
        this.coverImage=songCover;this.name=songname;
    }

    public void stop(){
        player.reset();
        playbutton.setImageResource(R.drawable.pause);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(MainActivity.currentSong==-1) {
            MainActivity.currentSong = 0;
            currentsong = songs.get(0);
            name.setText(songnames[0].replace(".mp3","").replace("(","").replace(")",""));
            fwdBtn.setEnabled(true);
            fwdBtn.setVisibility(View.VISIBLE);
            prevBtn.setVisibility(View.INVISIBLE);
            prevBtn.setEnabled(false);
        }
        else{
            currentsong=songs.get(MainActivity.currentSong);
            name.setText(songnames[MainActivity.currentSong].replace(".mp3","").replace("(","").replace(")",""));

        }
    }
    void seek(){
        if(player.isPlaying()){
        int length = player.getCurrentPosition()*100;
        seeker.setProgress(length/player.getDuration());}
    }

    private Runnable mUpdatetimer=new Runnable() {
        @Override
        public void run() {
            if(player.isPlaying())
            {
                long currentDur=player.getCurrentPosition();
                String current=milliSecondsToTimer(currentDur);
                startTimer.setText(current);
                startTimer.postDelayed(this,1000);
            }
            else{
                startTimer.removeCallbacks(this);
            }
        }
    };

    public void setSeeker(SeekBar bar,final TextView textView) {
        this.seeker=bar;
        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser){
                    float done=progress/100;
                    boolean playing=player.isPlaying();
                    player.pause();
                    player.seekTo((player.getDuration()*progress)/100);
                    long currentDur=player.getCurrentPosition();
                    String current=milliSecondsToTimer(currentDur);
                    textView.setText(current);
                    if(playing)
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


    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

    // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
    // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

    // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        }   else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

    // return timer string
        return finalTimerString;
    }

}

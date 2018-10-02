package com.songapp.demoapp.songs;


import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.songapp.demoapp.R;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongFragment extends Fragment {
    View mView;
    //card details
    ImageView playbutton,prevbutton,nextbutton;
    SeekBar bar;
    TextView songname;
    CircleImageView songCover;

    MyPlayer player;
    //list details
    ArrayList<SongData> arrayList;
    ArrayList<AssetFileDescriptor> songList;
    ListView listView;
    SongAdapter adapter;
    private boolean playing=false;
    private String[] songlist=new String[20];
    private Bitmap icon;
    private View.OnClickListener listener=new View.OnClickListener() {


        @TargetApi(Build.VERSION_CODES.N)
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {
            if(v==prevbutton){
                player.prev();
            }else{
                player.next();
            }
        }
    };

//player details

    public SongFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_song, container, false);
        handlePlayer();
        handleList();
        player.setSeeker(bar);
        return mView;
    }

    private void handlePlayer() {
        player=new MyPlayer();
        listView=mView.findViewById(R.id.mustList);
        playbutton=mView.findViewById(R.id.btnPlay);
        prevbutton=mView.findViewById(R.id.prevBtn);
        nextbutton=mView.findViewById(R.id.nextBtn);
        bar=mView.findViewById(R.id.musicseek);
        prevbutton.setOnClickListener(listener);
        nextbutton.setOnClickListener(listener);
        player.setControls(playbutton);
        songCover=mView.findViewById(R.id.singleSongCover);
        songname=mView.findViewById(R.id.songName);
        player.setViews(songCover,songname);
    }

    private void handleList() {
        arrayList=new ArrayList<SongData>();
        songList=new ArrayList<AssetFileDescriptor>();
        AssetManager asm = getActivity().getBaseContext().getAssets();
        icon = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.imgsample);
        try{
            songlist=asm.list("audio");
            for (String i : songlist) {
                AssetFileDescriptor afd = getActivity().getBaseContext().getAssets().openFd("audio/"+i);
                arrayList.add(new SongData(i,afd,icon));
                songList.add(afd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        player.setSongList(songList,songlist);
        adapter=new SongAdapter(getContext(),R.layout.single_music_item,arrayList,player);
        listView.setAdapter(adapter);
    }
}

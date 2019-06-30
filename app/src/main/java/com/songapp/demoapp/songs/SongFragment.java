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

import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongFragment extends Fragment {
    View mView;
    //card details
    ImageView playbutton,prevbutton,nextbutton;
    SeekBar bar;
    TextView songname,startTime,endTime;
    public static CircleImageView songCover;


    //list details
    ArrayList<SongData> arrayList;
    ArrayList<AssetFileDescriptor> songList;
    ListView listView;
    SongAdapter adapter;
    private boolean playing=false;

    private String[] songlist=new String[20];
    public static int[] songimageId={R.drawable.s1,R.drawable.s2,R.drawable.imgsample,R.drawable.s4,R.drawable.s5,R.drawable.s6,R.drawable.imgsample,R.drawable.imgsample,R.drawable.j2};


    private Bitmap icon;
    private View.OnClickListener listener=new View.OnClickListener() {


        @TargetApi(Build.VERSION_CODES.N)
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {
            if(v==prevbutton){
                MainActivity.player.prev();
            }else{
                MainActivity.player.next();
            }
        }
    };

//player details

    public SongFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_song, container, false);
        handlePlayer();

        MainActivity.player.setFwdBtn(nextbutton);
        MainActivity.player.setPrevBtn(prevbutton);
        MainActivity.player.setSeeker(bar,startTime);
        MainActivity.player.setStartTimer(startTime);
        MainActivity.player.setCoverImage(songCover);

        MainActivity.player.reload();

        handleList();






        return mView;
    }

    private void handlePlayer() {
        listView=mView.findViewById(R.id.mustList);
        playbutton=mView.findViewById(R.id.btnPlay);
        prevbutton=mView.findViewById(R.id.prevBtn);
        nextbutton=mView.findViewById(R.id.nextBtn);
        bar=mView.findViewById(R.id.musicseek);
        prevbutton.setOnClickListener(listener);
        nextbutton.setOnClickListener(listener);
        MainActivity.player.setControls(playbutton);
        songCover=mView.findViewById(R.id.updownArrow);
        songname=mView.findViewById(R.id.songName);
        MainActivity.player.setViews(songCover,songname);
        startTime=mView.findViewById(R.id.songStartTimer);
        endTime=mView.findViewById(R.id.songEndTime);
        MainActivity.player.setEndTimer(endTime);
    }

    private void handleList() {
        arrayList=new ArrayList<SongData>();
        songList=new ArrayList<AssetFileDescriptor>();
        AssetManager asm = getActivity().getBaseContext().getAssets();

        int[] songimageId={R.drawable.s1,R.drawable.s2,R.drawable.s4,R.drawable.s5,R.drawable.s6,R.drawable.imgsample,R.drawable.imgsample,R.drawable.j2};

        try{
            songlist=asm.list("audio");
            int j=0;
            for (String i : songlist) {
                AssetFileDescriptor afd = getActivity().getBaseContext().getAssets().openFd("audio/"+i);
                icon = BitmapFactory.decodeResource(getContext().getResources(),songimageId[j++]);
                arrayList.add(new SongData(i,afd,icon));
                songList.add(afd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //Sorting Logic
        ArrayList<Wrapper> wrappers =  sorting(arrayList,songList,songlist);
        arrayList.clear();
        songList.clear();
        for(int i=0;i<wrappers.size();i++)
        {
            arrayList.add(wrappers.get(i).songs);
            songList.add(wrappers.get(i).assets);
            songlist[i] = wrappers.get(i).songTitle;
        }

        MainActivity.player.setSongList(songList,songlist);
        adapter=new SongAdapter(getContext(),R.layout.single_music_item,arrayList, MainActivity.player,songCover);
        listView.setAdapter(adapter);
    }

    class Wrapper{
        SongData songs;
        AssetFileDescriptor assets;
        String songTitle;
        public Wrapper( SongData songs,AssetFileDescriptor assets,String title){
            this.songs = songs;
            this.assets = assets;
            this.songTitle = title;
        }
    }

    class SortByName implements Comparator<Wrapper>{

        @Override
        public int compare(Wrapper o1, Wrapper o2) {
            String order[] = {"Morning","Joyful","Sorrowful","Glorious","Luminous","Evening","Divine"};
            int a = 0,b=0;
            for(int i=0;i<order.length;i++){
                if(o1.songs.getTitle().toLowerCase().contains(order[i].toLowerCase())){
                    a=i;
                    break;
                }
            }
            for(int i=0;i<order.length;i++){
                if(o2.songs.getTitle().toLowerCase().contains(order[i].toLowerCase())){
                    b=i;
                    break;
                }
            }
            return a - b;
        }
    }

    public ArrayList<Wrapper> sorting(ArrayList<SongData> songs, ArrayList<AssetFileDescriptor> arrays,String songnames[]){
        ArrayList<Wrapper> wrapper = new ArrayList<>() ;
        for(int i=0;i<songs.size();i++){
            Wrapper temp = new Wrapper(songs.get(i),arrays.get(i),songnames[i]);
            wrapper.add(temp);
        }
        Collections.sort(wrapper,new SortByName());
        return wrapper;
    }

}

package com.songapp.demoapp.songs;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.songapp.demoapp.R;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongAdapter extends ArrayAdapter<SongData> {
    MyPlayer player;
    List<SongData> textList;
    Context context;
    int resourse;

    public SongAdapter(Context c, int r, List<SongData> list, MyPlayer player)
    {
        super(c,r,list);
        context=c;
        resourse=r;
        textList=list;
        this.player=player;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(resourse,null,false);
        TextView title = view.findViewById(R.id.singleSongTitle);
        CircleImageView imgView = view.findViewById(R.id.singleSongCover);
        ImageView btn=view.findViewById(R.id.singlePlayBtn);
        final SongData data = textList.get(position);
        title.setText(data.getTitle());
        imgView.setImageBitmap(data.getCover());
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                v.toString();
                AssetFileDescriptor temp = textList.get(position).getLocation();
                player.setsong(temp);
            }
        });
        return view;
    }
}

package com.songapp.demoapp.songs;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;

import java.io.FileDescriptor;

public class SongData {

    private  AssetFileDescriptor filedescriptor;
    String title;
    Bitmap cover;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AssetFileDescriptor getLocation() {
        return filedescriptor;
    }

    public void setFile(AssetFileDescriptor location) {
        this.filedescriptor = location;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public SongData(String title, AssetFileDescriptor fd, Bitmap cover) {

        this.title = title;
        this.filedescriptor = fd;
        this.cover = cover;
    }

    public SongData(){

    }

}

package com.songapp.demoapp.texts;

import android.graphics.Bitmap;

public class TextData {

    boolean isKannada;

    public boolean isKannada() {
        return isKannada;
    }

    public void setKannada(boolean kannada) {
        isKannada = kannada;
    }

    String title,desc;
    Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public TextData(String title, String desc, Bitmap image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public TextData(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public TextData(){

    }
}

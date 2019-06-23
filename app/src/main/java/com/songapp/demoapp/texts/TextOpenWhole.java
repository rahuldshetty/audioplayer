package com.songapp.demoapp.texts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

public class TextOpenWhole extends AppCompatActivity {


    TextView textView;
    Toolbar mtoolbar;
    String extraTexts[];
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_open_whole);

        mtoolbar=findViewById(R.id.mtoolbar35);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        pdfView=findViewById(R.id.pdfView);
        int selected = MainActivity.selectGroup - 9;
        String path="";
        if(selected==0)
            path="morning.pdf";
        else if(selected==1)
            path="evening.pdf";
        else
            path="general.pdf";

        pdfView.fromAsset(path)
                .enableSwipe(true)
                .enableAntialiasing(true)
                .swipeHorizontal(false)
                .enableDoubletap(false)
                .defaultPage(0)
                .load();


            Toast.makeText(this, "Scroll to view...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent act = new Intent(TextOpenWhole.this,MainActivity.class);
        act.putExtra("CLASS",2);
        startActivity(act);
        finish();
    }
}

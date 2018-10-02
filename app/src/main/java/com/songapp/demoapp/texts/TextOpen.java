package com.songapp.demoapp.texts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.songapp.demoapp.R;

public class TextOpen extends AppCompatActivity {

    private Toolbar mtoolbar;

    private Intent intent;

    private TextView mtitle,mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_open);

        mtoolbar=findViewById(R.id.mtoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        mtitle=findViewById(R.id.opentitle);
        mDesc=findViewById(R.id.opendesc);

        intent=getIntent();
        String title = intent.getStringExtra("TITLE");
        String desc = intent.getStringExtra("DESC");

        mtitle.setText(title);
        mDesc.setText(desc);


    }
}

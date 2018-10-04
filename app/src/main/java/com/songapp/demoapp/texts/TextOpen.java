package com.songapp.demoapp.texts;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TextOpen extends AppCompatActivity {

    private Toolbar mtoolbar;

    private Intent intent;

    private TextView mtitle,mDesc;

    private Button nextB;

    ProgressBar progressBar;

    int line_Counter = 0;
    String formatLines[];
    String weekLines[];

    String format,weekdata;
    ArrayList<String> texts;

    AssetManager assestm;

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
        nextB=findViewById(R.id.nextBeadBtn);
        texts=new ArrayList<String>();

        progressBar=findViewById(R.id.progressBar);

        assestm=getAssets();
        try {
            format = getString(assestm.open("format"));
            formatLines=format.split("\\r?\\n");
            for(int i=1;i<=8;i++)
            {
                texts.add( getString( assestm.open("groups/"+  i  ) ) );
            }
            weekdata=getString(assestm.open("weekdays/"+"g"+MainActivity.selectGroup));
            weekLines=weekdata.split("\\r?\\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(MainActivity.selectGroup==1)
            mtitle.setText("Joyful Mysteries");
        else if(MainActivity.selectGroup==2)
            mtitle.setText("Sorrowful Mysteries");
        else if(MainActivity.selectGroup==3)
            mtitle.setText("Glorious Mysteries");
        else if(MainActivity.selectGroup==4)
            mtitle.setText("Luminous Mysteries");


        loadString();

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(line_Counter==60) {
                    nextB.setEnabled(false);
                    nextB.setVisibility(View.INVISIBLE);
                }
                else
                {
                    line_Counter++;
                    progressBar.setProgress(progressBar.getProgress()+1);
                    loadString();

                }
            }
        });

    }

    String getString(InputStream i) throws IOException{
        Scanner s = new Scanner(i).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
    }

    private void  loadString(){
        String con="";

        String parts[]=formatLines[line_Counter].split("\\s+");



        for(String part:parts){
            if(!part.startsWith("M")){
                con= con + texts.get(Integer.parseInt(part) - 1) + "\n" ;
            }
            else{
                con=con + weekLines[ Integer.parseInt(String.valueOf(part.charAt(1))) - 1 ]+"\n";
            }
        }

        mDesc.setText(con);


    }





}

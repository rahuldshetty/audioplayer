package com.songapp.demoapp.texts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.songapp.demoapp.R;

import java.util.List;

public class TextAdapter extends ArrayAdapter<TextData> {
    List<TextData> textList;
    Context context;
    int resourse;

    public TextAdapter(Context c,int r,List<TextData> list)
    {
        super(c,r,list);
        context=c;
        resourse=r;
        textList=list;
    }


    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view = layoutInflater.inflate(resourse,null,false);

        TextView title = view.findViewById(R.id.text_single_title);
        TextView desc = view.findViewById(R.id.text_single_Desc);

        final TextData data = textList.get(position);

        title.setText(data.getTitle());
        desc.setText(data.getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openText=new Intent(context.getApplicationContext(),TextOpen.class);
                openText.putExtra("TITLE",data.getTitle());
                openText.putExtra("DESC",data.getDesc());
                context.startActivity(openText);
            }
        });

        return view;
    }
}

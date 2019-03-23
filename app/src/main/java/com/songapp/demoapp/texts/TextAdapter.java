package com.songapp.demoapp.texts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TextAdapter extends ArrayAdapter<TextData> {
    ArrayList<TextData> textList;
    Context context;
    int resourse;

    public TextAdapter(Context c,int r,ArrayList<TextData> list)
    {
        super(c,r,list);
        context=c;
        resourse=r;
        textList=list;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View view = layoutInflater.inflate(resourse,null,false);

        TextView title = view.findViewById(R.id.menutitle);
        TextView desc = view.findViewById(R.id.menudesc);
        ImageView img=view.findViewById(R.id.menuimg);

        final TextData data = textList.get(position);

        title.setText(data.getTitle());
        desc.setText(data.getDesc());
        img.setImageBitmap(data.getImage());

        if(data.isKannada())
        {
            Typeface type=ResourcesCompat.getFont(view.getContext(),R.font.nudi01ebold);
            title.setTypeface(type);
            Typeface type2=ResourcesCompat.getFont(view.getContext(),R.font.nudi);
            desc.setTypeface(type2);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            desc.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openText;

                MainActivity.selectGroup=position+1;

                if(MainActivity.selectGroup<9)
                     openText=new Intent(context.getApplicationContext(),TextOpen.class);
                else
                    openText=new Intent(context.getApplicationContext(),TextOpenWhole.class);

                context.startActivity(openText);
            }
        });

        return view;
    }
}

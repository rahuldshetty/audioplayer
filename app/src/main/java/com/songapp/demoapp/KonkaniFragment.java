package com.songapp.demoapp;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.songapp.demoapp.texts.TextAdapter;
import com.songapp.demoapp.texts.TextData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class KonkaniFragment extends Fragment {


    View mView;

    ArrayList<TextData> arrayList;

    ListView listView;

    TextAdapter adapter;

    private String[] list;




    public KonkaniFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_texts, container, false);


        String[] titles={"Joyful Mystery","Sorrowful Mystery","Glorious Mystery","Luminous Mystery","¸ÀAvÉÆ¸ÁZÉ «Ä¸ÉÛgï","zÀÄBTZÉ «Ä¸ÉÛgï","D£ÀAzÁZÉ «Ä¸ÉÛgï","GeÁéqÁZÉ «Ä¸ÉÛgï","¸ÀPÁ½Aa ¨sÉmÉÆ«","¸ÁAeÉa ªÀiÁVÚ","ªÀÄÄ¼Á«A ªÀiÁVÚA"};
        String[] desc={"Monday-Saturday","Tuesday-Friday","Wednesday-Sunday","Thursday","¸ÉÆªÀiÁgÁ D¤ ¸À£ÁégÁ","ªÀÄAUÁîgÁ D¤ ¸ÀÄPÁægÁ","§ÄzÁégÁ D¤ DaiÀiÁÛgÁ","¨Éæ¸ÁÛgÁ","","",""};
        int[] images={R.drawable.j1,R.drawable.j2,R.drawable.j3,R.drawable.j4,R.drawable.j1,R.drawable.j2,R.drawable.j3,R.drawable.j4,R.drawable.j9,R.drawable.j10,R.drawable.j11};


        arrayList=new ArrayList<TextData>();
        listView=mView.findViewById(R.id.textLists);

        for(int i=4;i< titles.length;i++)
        {
            TextData temp=new TextData();
            temp.setDesc(desc[i]);
            temp.setTitle(titles[i]);
            temp.setImage(BitmapFactory.decodeResource( getResources(), images[i] ) );
            if(i<4)
            {
                temp.setKannada(false);
            }
            else
                temp.setKannada(true);
            arrayList.add(temp);
        }



        adapter=new TextAdapter(getContext(),R.layout.menusingletext,arrayList);

        listView.setAdapter(adapter);


        return mView;
    }

    String getString(InputStream i) throws IOException {
        Scanner s = new Scanner(i).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
    }

}

package com.songapp.demoapp.texts;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.songapp.demoapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextsFragment extends Fragment {


    View mView;

    ArrayList<TextData> arrayList;

    ListView listView;

    TextAdapter adapter;

    AssetManager assestm;
    private String[] list;


    public TextsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_texts, container, false);

        arrayList=new ArrayList<TextData>();
        listView=mView.findViewById(R.id.textLists);

        assestm=getContext().getAssets();
        try {
            list = assestm.list("texts");
            for(String i : list){
                arrayList.add(new TextData(i,getString(assestm.open("texts/"+i))));
            }
        } catch (Exception e) {
            Toast.makeText(getContext(),"Cannot Load Texts",Toast.LENGTH_SHORT).show();
        }
        adapter=new TextAdapter(getContext(),R.layout.text_single,arrayList);

        listView.setAdapter(adapter);

        return mView;
    }

    String getString(InputStream i) throws IOException{
        Scanner s = new Scanner(i).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
    }
}

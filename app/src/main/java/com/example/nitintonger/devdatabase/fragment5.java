package com.example.nitintonger.devdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nitin Tonger on 17-09-2016.
 */
public class fragment5 extends Fragment {
    public fragment5()
    {

    }
    DatabaseHelper db;
    //OldEntry oldEntry;
    Cursor res;
    String total="";
    TextView textView4;
    Button button,button10;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.entries, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  textView4=(TextView)getActivity().findViewById(R.id.textView4);
        db=new DatabaseHelper(getContext());
        //oldEntry=new OldEntry();
        fragmenttwo fragmenttwo=new fragmenttwo();
        //total=fragmenttwo.syncdata(getContext());
        //textView4.setText(total);

    }
}

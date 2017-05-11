package com.example.nitintonger.devdatabase;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by Nitin Tonger on 19-09-2016.
 */
public class CustomOnItemListner implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      //  ((TextView) adapterView.getChildAt(0)).setTextColor(Color.GRAY);
     //   ((TextView) adapterView.getChildAt(1)).setTextColor(Color.GRAY);
        //((TextView) adapterView.getChildAt()).setTextColor(Color.GRAY);
       // ((TextView) adapterView.getChildAt(0)).setTextSize(5);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

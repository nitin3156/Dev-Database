package com.example.nitintonger.devdatabase;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin Tonger on 17-09-2016.
 */
public class fragment3 extends Fragment {
    public fragment3()
    {

    }
    Button buttonsubmit;
    DatabaseHelper db;
    EditText editText10,editText13,editText14,editText15,editText16,editText12,editText11,editText17;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_update_entry, container, false);
    }

    //RadioGroup radioGroup1;
    //RadioButton radioButton2;
    Spinner spinner1;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner1=(Spinner)getActivity().findViewById(R.id.payspinner1);
        List<String> list = new ArrayList<String>();
        list.add("Payment Status" );
        list.add("Received");
        list.add("Due");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        addListenerOnSpinnerItemSelection();
        buttonsubmit=(Button)getActivity().findViewById(R.id.button4);
        editText10=(EditText)getActivity().findViewById(R.id.editText10);
        editText11=(EditText)getActivity().findViewById(R.id.editText11);
        editText12=(EditText)getActivity().findViewById(R.id.editText12);
        editText13=(EditText)getActivity().findViewById(R.id.editText13);
        //radioGroup1=(RadioGroup)getActivity().findViewById(R.id.radiogroup1);
        editText14=(EditText)getActivity().findViewById(R.id.editText14);
        editText15=(EditText)getActivity().findViewById(R.id.editText15);
        editText16=(EditText)getActivity().findViewById(R.id.editText16);
        editText17=(EditText)getActivity().findViewById(R.id.editText17);
        db=new DatabaseHelper(getContext());
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getdata();
                if(res.getCount() == 0) {
                    // show message
                    //showMessage("Error","Nothing found");

                    return;
                }
                else if(editText10.length()==0)
                    editText10.setError("Can't be Empty!!!");

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if(editText10.getText().toString().equals(res.getString(0)))
                    {

                        editText11.setText(res.getString(1));
                        editText12.setText(res.getString(2));
                        editText13.setText(res.getString(3));
                        editText14.setText(res.getString(4));
                        editText15.setText(res.getString(5));
                        editText16.setText(res.getString(6));
                        editText17.setText(res.getString(7));

                        //radioButton2.setText(res.getString(8));
                    }
                    else
                    {
                        int a=10;
                    }
                }
                res.close();
                // Show all data
                //showMessage("Data",buffer.toString());

            }
        });
        buttonsubmit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                //int selected1=radioGroup1.getCheckedRadioButtonId();
                String d = String.valueOf(spinner1.getSelectedItem());
                //radioButton2=(RadioButton)getActivity().findViewById(selected1);
                if (d.equals("Payment Status")) {
                    showwelcome1();
                } else {


                    boolean done = db.updateData(editText10.getText().toString(), editText11.getText().toString(), editText12.getText().toString(), editText13.getText().toString()
                            , editText14.getText().toString(), editText15.getText().toString(), editText16.getText().toString(), editText17.getText().toString(), d);
                    if (done == true)
                        Toast.makeText(getContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Data Not Updated", Toast.LENGTH_SHORT).show();
                    editText10.setText("");
                    editText13.setText("");
                    editText14.setText("");
                    editText15.setText("");
                    editText16.setText("");
                    editText12.setText("");
                    editText11.setText("");
                    editText17.setText("");



                }
                return true;
            }
        });
    }
    public void addListenerOnSpinnerItemSelection(){

        spinner1.setOnItemSelectedListener(new CustomOnItemListner());
    }
    public void showwelcome1()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("Choose the payment current status");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.show();
    }

}


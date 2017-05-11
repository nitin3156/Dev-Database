package com.example.nitintonger.devdatabase;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fragmentone extends Fragment  {
    public fragmentone()
    {

    }


    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

    }
    EditText editText3, editText4, editText5, editText6,editText7,editText8,editText9;
    Button b1;
    TextView textView;
    DatabaseHelper mydb;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_entry, container, false);

    }
    RadioGroup radioGroup;
    RadioButton radioButton1;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinner=(Spinner)getActivity().findViewById(R.id.payspinner);
        List<String> list = new ArrayList<String>();
        list.add("Payment Status" );
        list.add("Received");
        list.add("Due");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        addListenerOnSpinnerItemSelection();



        editText3 = (EditText)getActivity().findViewById(R.id.editText3);
        //radioGroup=(RadioGroup)getActivity().findViewById(R.id.radiogroup);

        editText7 = (EditText)getActivity().findViewById(R.id.editText7);
        editText8 = (EditText)getActivity().findViewById(R.id.editText8);
        editText9 = (EditText)getActivity().findViewById(R.id.editText9);
        editText4 = (EditText)getActivity().findViewById(R.id.editText4);
        editText5 = (EditText)getActivity().findViewById(R.id.editText5);
        editText6 = (EditText)getActivity().findViewById(R.id.editText6);
        b1 = (Button)getActivity().findViewById(R.id.button414);
        mydb = new DatabaseHelper(getContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // int selectedid=radioGroup.getCheckedRadioButtonId();
                String c = String.valueOf(spinner.getSelectedItem());
                //  radioButton1=(RadioButton)getActivity().findViewById(selectedid);
                if (c.equals("Payment Status")) {
                    showwelcome("Choose the payment current status");
                }
                else if(editText5.length()==0 | editText8.length()==0 |editText9.length()==0)
                {
                    showwelcome("Some value is needed for 'Fare' , 'Driver , 'Diesel'");
                }
                else
                {

                    boolean rp = mydb.insertdata(editText3.getText().toString(), editText4.getText().toString(), editText5.getText().toString(), editText6.getText().toString(), editText7.getText().toString(), editText8.getText().toString(), editText9.getText().toString(), c);
                    if (rp == true)
                        Toast.makeText(getContext(), "Data Entered", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Data Not Entered", Toast.LENGTH_SHORT).show();
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
                  //  editText6.setText("");
                    editText7.setText("");
                    editText8.setText("");
                    editText9.setText("");


                }
            }
        });
    }

    public void addListenerOnSpinnerItemSelection(){

        spinner.setOnItemSelectedListener(new CustomOnItemListner());
    }

    public void showwelcome(String mess)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage(mess);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.show();
    }
}

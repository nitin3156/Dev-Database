package com.example.nitintonger.devdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nitin Tonger on 17-09-2016.
 */
public class fragment4 extends Fragment {
    public fragment4()
    {

    }
    DatabaseHelper db;
    EditText editText18;
    Button del;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_delete_entry, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText18=(EditText)getActivity().findViewById(R.id.editText18);
        db= new DatabaseHelper(getContext());
        del=(Button)getActivity().findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Integer deletedRows =db.deleteData(editText18.getText().toString());
                if(deletedRows>0)
                    Toast.makeText(getContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Data Not Deleted",Toast.LENGTH_SHORT).show();
                editText18.setText("");
            }
        });
    }
}

package com.example.nitintonger.devdatabase;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nitin Tonger on 17-09-2016.
 */
public class fragmenttwo extends Fragment{
    public fragmenttwo()
    {

    }
    public static int total=0,save=0;
    DatabaseHelper databaseHelper;
    Button button;
    TextView textView;
    char a;
    int tot=0;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.old_entry, container, false);
    }
    ProgressDialog progressDialog;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //button=(Button)getActivity().findViewById(R.id.button10);
        textView=(TextView)getActivity().findViewById(R.id.textView11);
        databaseHelper=new DatabaseHelper(getContext());
        progressDialog=new ProgressDialog(getContext());

        runningable();

      /*  //button.setOnClickListener(new View.OnClickListener() {
            @Override
           // public void onClick(final View v) {

                Cursor res=databaseHelper.getdata();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    //return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id---------->"+ res.getString(0)+"\n");
                    buffer.append("From-------->"+ res.getString(1)+"\n");
                    buffer.append("To---------->"+ res.getString(2)+"\n");
                    buffer.append("FARE-------->"+ res.getString(3)+"\n");
                    buffer.append("DATE-------->"+ res.getString(4)+"\n");
                    buffer.append("Vehicle No-->"+ res.getString(5)+"\n");
                    buffer.append("Driver------>"+ res.getString(6)+"\n");
                    buffer.append("Diesel------>"+ res.getString(7)+"\n\n\n");
                    save=Integer.valueOf(res.getString(3));
                    total=total+save;
                    buffer.append("Total till now :"+ total+"\n\n\n");
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Total",String.valueOf(total));
                    editor.commit();


                }
                res.close();
                 textView.setText(buffer.toString());
                // Show all data
                //showMessage("Data",buffer.toString());
                total=0;*/
    }
    // });
    //}
    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void showwelcome()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Note");
        builder.setMessage("Welcome To Dev Database");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.show();
    }

    public  String syncdata( Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("Total", null);
    }
    public void runningable()
    {

        Cursor res=databaseHelper.getdata();
        if(res.getCount() == 0) {
            // show message
            //showMessage("Error","Nothing found");
                textView.setText("Nothing is stored!!!");
            return;
        }
        ArrayList<Integer> mStrings = new ArrayList<Integer>();
        ArrayList<Integer> drStrings=new ArrayList<Integer>();
        ArrayList<Integer> diStrings=new ArrayList<Integer>();
        ArrayList<Integer> current=new ArrayList<Integer>();
        ArrayList<Integer> duebal=new ArrayList<>();
        int drsave=0,dtotal=0,disave=0,ditotal=0,chargec=0,savi=0;
        int du1=0,du2=0;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month=month+1;
        String sub;
        progressDialog.setMessage("Please wait your data is loading...");
        progressDialog.show();
        while (res.moveToNext())
        {
            sub=res.getString(4).substring(3,5);
            int cm=Integer.valueOf(sub);
            if(cm==month)
            {
                savi=Integer.valueOf(res.getString(3));
                chargec=chargec+savi;
                current.add(chargec);
            }
            else
            {
                chargec=chargec+0;
                current.add(chargec);
            }
            save=Integer.valueOf(res.getString(3));
            total=total+save;
            drsave=Integer.valueOf(res.getString(6));
            dtotal=dtotal+drsave;
            disave=Integer.valueOf(res.getString(7));
            ditotal=ditotal+disave;

            mStrings.add(total);
            drStrings.add(dtotal);
            diStrings.add(ditotal);
            if(res.getString(8).equals("Due"))
            {
                du1=du1+save;
            }
            duebal.add(du1);

        }
        int i=mStrings.size();

        while (res.moveToPrevious()) {

        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {

        }
        while (res.moveToPrevious()) {

            buffer.append("Id:"+ res.getString(0)+"\n");
            buffer.append("From:"+ res.getString(1)+"\n");
            buffer.append("To:"+ res.getString(2)+"\n");
            buffer.append("Fare:"+ res.getString(3)+"\n");
            buffer.append("Date:"+ res.getString(4)+"\n");
            buffer.append("Vehicle No:"+ res.getString(5)+"\n");
            buffer.append("Driver:"+ res.getString(6)+"\n");
            buffer.append("Diesel:"+ res.getString(7)+"\n");
            buffer.append("Payment:"+ res.getString(8)+"\n\n\n");
            save=Integer.valueOf(res.getString(3));
            total=total+save;

            sub=res.getString(4).substring(3,5);
            int cm=Integer.valueOf(sub);
            if(cm==month)
            {
                savi=Integer.valueOf(res.getString(3));
                chargec=chargec+savi;
                current.add(chargec);
            }
            else
            {
                chargec=chargec+0;
                current.add(chargec);
            }
            save=Integer.valueOf(res.getString(3));
            total=total+save;
            drsave=Integer.valueOf(res.getString(6));
            dtotal=dtotal+drsave;
            disave=Integer.valueOf(res.getString(7));
            ditotal=ditotal+disave;

            mStrings.add(total);
            drStrings.add(dtotal);
            diStrings.add(ditotal);
            if(res.getString(8).equals("Due"))
            {
                du1=du1+save;
            }
            duebal.add(du1);

           // List<Integer> mStrings = new ArrayList<Integer>();
            //buffer.append("This month:"+tot+"\n");
           // mStrings.add(total);
            int ca=mStrings.get(i-1);
            int dr=drStrings.get(i-1);
            int di=diStrings.get(i-1);
            int cur=current.get(i-1);
            int dbal=duebal.get(i-1);
            buffer.append("___________________________________________________ "+"\n");
            buffer.append("Total till now :"+Integer.valueOf(ca)+"\n");
            buffer.append("Total driver charges :"+Integer.valueOf(dr)+"\n");
            buffer.append("Total diesel charges :"+Integer.valueOf(di)+"\n");
            buffer.append("Total save :"+(ca-dr-di)+"\n");
            buffer.append("This month total:"+cur+"\n");
            buffer.append("Total amount due:"+dbal+"\n");
            buffer.append("___________________________________________________ "+"\n\n\n\n\n\n\n");

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Total",String.valueOf(total));
            editor.commit();
            i=i-1;

        }
        res.close();
        progressDialog.dismiss();
        textView.setText(buffer.toString());

        // Show all data
        //showMessage("Data",buffer.toString());
        total=0;
        tot=0;
    }
}

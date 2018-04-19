package com.reviracio.gui_03;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class RateList extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter<String> adapter;
    ArrayList<RateModel> dane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ArrayList<RateModel> dane2 = new ArrayList<RateModel>();

        dane = new ArrayList<RateModel>();
        Bundle container = getIntent().getExtras();
        final int source = container.getInt("countOfMarks");


        //if (savedInstanceState == null) {
            for (int i = 0; i < source; i++) {
                dane.add(new RateModel("nazwa\n", 2));
            }

        if(savedInstanceState != null) {
            dane = (ArrayList<RateModel>) savedInstanceState.getSerializable("marksList");



            }


        final ListView markList = (ListView) findViewById(R.id.listView1);
        RateAdapter adapter = new RateAdapter(this, dane);
        markList.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.bRates);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                for (int i = 0; i < source; i++) {
                    sum += dane.get(i).getRate();
                }
                String kot = "";

                double average;
                average = (double) sum / (double) source;
                double roundAverage = (double) Math.round(average * 100d) / 100d;


                kot = String.valueOf(roundAverage);
                Toast.makeText(RateList.this, "toastText: " + kot, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent();
                intent.putExtra("average", kot);
                setResult(RESULT_OK, intent);
                finish();


            }
        });


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RateList.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("marksList", dane);
        super.onSaveInstanceState(outState);
    }


}



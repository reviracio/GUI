package com.reviracio.gui_03;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText numberRate;
    EditText name;
    EditText surname;
    TextView averageString;
    Button rate;
    Button superButton;
    Button niePoszlo;

    boolean numberRateFlag=false;
    boolean nameFlag=false;
    boolean surnameFlag=false;

    double average=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("focus", "Create");

        numberRate = (EditText) findViewById(R.id.etNumberRate);
        name = (EditText) findViewById(R.id.etName);
        surname = (EditText) findViewById(R.id.etSurname);
        rate = (Button) findViewById(R.id.bRate);
        averageString = (TextView) findViewById(R.id.tvAverage);
        //averageString.setVisibility(View.VISIBLE);

        checkState(name, getString(R.string.tsLackName));
        checkState(surname, getString(R.string.tsLackSurname));
        checkRateState(numberRate);


        Button superButton= (Button) findViewById(R.id.bSuper);
        superButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Gratulacje", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button niePoszlo = (Button) findViewById(R.id.bTymRazem);
        niePoszlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Wysyłam podanie o o zaliczenie warunkowe", Toast.LENGTH_SHORT).show();
                finish();

            }
        });






        Button button = (Button) findViewById(R.id.bRate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this, RateList.class);
                int count = Integer.parseInt(((EditText) findViewById(R.id.etNumberRate)).getText().toString());
                intent.putExtra("countOfMarks", count);
                startActivityForResult(intent, 2);

            }
        });


    }

    private void CheckStateButton(){
        if(!name.getText().toString().isEmpty()){
            nameFlag=true;
        }else {
            nameFlag=false;
        }
        if(!surname.getText().toString().isEmpty()){
            surnameFlag=true;
        }else{
            surnameFlag=false;
        }
        if (!numberRate.getText().toString().isEmpty() && Integer.parseInt(numberRate.getText().toString()) >= 5 && Integer.parseInt(numberRate.getText().toString()) <= 15) {
            numberRateFlag=true;
        }else{
            numberRateFlag=false;
        }
        if(nameFlag==true && surnameFlag==true && numberRateFlag==true /*&& averageString.getText().toString().isEmpty()*/){
            rate.setVisibility(View.VISIBLE);
        }else{
            rate.setVisibility(View.GONE);
        }

    }


    private void checkState(final EditText editTextName, final String toastText) {
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && editTextName.getText().toString().isEmpty()) {
                    Log.i("focus", "!hasFocus");
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                }
                CheckStateButton();
            }
        });
    }

    private void checkRateState(final EditText editTextName) {

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editTextName.getText().toString().isEmpty()) {
                    Log.i("focus", "!hasFocus");
                    Toast.makeText(MainActivity.this, getString(R.string.tsLackNumberRate), Toast.LENGTH_SHORT).show();
                }
                if (!editTextName.getText().toString().isEmpty() && ((Integer.parseInt(editTextName.getText().toString()) < 5) || (Integer.parseInt(editTextName.getText().toString()) > 15))) {
                    Toast.makeText(MainActivity.this, getString(R.string.tsWrongNumberRate), Toast.LENGTH_SHORT).show();
                    Log.i("focus", "!qwertyuiop");
                }
                CheckStateButton();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("SaveNumberRate", numberRate.getText().toString());
        outState.putString("SaveName", name.getText().toString());
        outState.putString("SaveSurname", surname.getText().toString());
        outState.putString("SaveAverage", averageString.getText().toString());
        outState.putDouble("aver", average);
        //outState.put
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        name.setText(savedInstanceState.getString("SaveName"));
        surname.setText(savedInstanceState.getString("SaveSurname"));
        numberRate.setText(savedInstanceState.getString("SaveNumberRate"));
        if(!savedInstanceState.getString("SaveAverage").isEmpty()){
            averageString.setVisibility(View.VISIBLE);
            averageString.setText(savedInstanceState.getString("SaveAverage"));

        }
        average=savedInstanceState.getDouble("aver");
        avString();



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String returnString = data.getStringExtra("average");

                averageString.setVisibility(View.VISIBLE);
                averageString.setText("Twoja średnia: " + returnString);
                double myAver = Double.parseDouble(returnString);
                average=myAver;
                if(myAver<3.0){
                    Button niePoszlo= (Button) findViewById(R.id.bTymRazem);
                    niePoszlo.setVisibility(View.VISIBLE);
                }else{
                    Button superButton= (Button) findViewById(R.id.bSuper);
                    superButton.setVisibility(View.VISIBLE);
                }



            }
        }
    }

    public void avString(){
        Log.i("focus", "!avr: "+average);
        if(average!=0){
            if(average<3.0){
                Button niePoszlo= (Button) findViewById(R.id.bTymRazem);
                niePoszlo.setVisibility(View.VISIBLE);
                rate.setVisibility(View.GONE);
            }else{
                Button superButton= (Button) findViewById(R.id.bSuper);
                rate.setVisibility(View.GONE);
                superButton.setVisibility(View.VISIBLE);
            }
        }

    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }


}

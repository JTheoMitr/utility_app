package com.example.firstapp;

import static com.example.firstapp.R.layout.activity_calculator;
import static com.example.firstapp.R.layout.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    Student john;  //a ref variable is created on the stack memory
    public static  String TAG = MainActivity.class.getSimpleName();
    //"MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflation
        john = new Student("John Theo",123,3445); //this will create the object on heap memory

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.login_button:
                startHome();
                break;
            case R.id.btnAlarm:
                createAlarm("midnight",00,49);
                break;
            case R.id.calculator_button:
                startCalculator();
                break;
        }
        // startHome();
        // create button to go to recycler

    }



    public void createAlarm(String message, int hour, int minutes) {
        //Util myUtil = new Util();
        //Util.add(10,20);
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void startHome() {
//        String value = nameEditText.getText().toString();
        Intent hIntent = new Intent(this, HomeActivity.class); //explicit intent
//        hIntent.putExtra("namekey",value);
        startActivityForResult(hIntent,123); //step 1
        Log.i(TAG, "starting homeactivity");
    }

    private void startCalculator() {

        Intent hIntent = new Intent(this, CalculatorActivity.class); //explicit intent
        startActivityForResult(hIntent,123);
        Log.i(TAG, "starting calculator activity");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //dataIntent
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult");

        if(requestCode == 123){
            String phno =  data.getExtras().getString("phonenum");
            TextView tvMain = findViewById(R.id.tvMain);
            tvMain.setText(phno);
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus){
            Toast.makeText(this, "got focus", Toast.LENGTH_SHORT).show();
            Log.i(TAG,"has focus");
        }
        else {
            Toast.makeText(this, R.string.enter_pw, Toast.LENGTH_SHORT).show();
            Log.i(TAG,"lost focus");

        }
    }




}
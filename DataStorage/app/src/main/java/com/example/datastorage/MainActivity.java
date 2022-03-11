package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView number, black,red,blue,green,count;
    private Button btw;

    int mCount = 0;
    int mCurrentColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        black = findViewById(R.id.black);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        count = findViewById(R.id.count);
        btw = findViewById(R.id.btw);

        black.setOnClickListener(this);
        red.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        btw.setOnClickListener(this);
        count.setOnClickListener(this);

        SharedPreferences mypref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mCount = mypref.getInt("count",1);
        mCurrentColor = mypref.getInt("color",mCurrentColor);
        number.setBackgroundColor(mCurrentColor);
        number.setText(Integer.toString(mCount));
        btw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypref.edit().clear().apply();
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mCount+=1;
                    number.setText(Integer.toString(mCount));

            }
        });


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.black:
                mCurrentColor = Color.BLACK;
                number.setBackgroundColor(mCurrentColor);
                break;
            case R.id.red:
                mCurrentColor = Color.RED;
                number.setBackgroundColor(mCurrentColor);
                break;
            case R.id.blue:
                mCurrentColor = Color.BLUE;
                number.setBackgroundColor(mCurrentColor);
                break;
            case R.id.green:
                mCurrentColor = Color.GREEN;
                number.setBackgroundColor(mCurrentColor);
                break;





        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mypref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myedit = mypref.edit();
        myedit.putInt("count",mCount);
        myedit.putInt("color",mCurrentColor);
        myedit.apply();
    }
}
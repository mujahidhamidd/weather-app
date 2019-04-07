package com.weather;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class add_city extends AppCompatActivity {

    SQLiteDatabase db;
    DbHelper mDbHelper;
    EditText city;
    EditText temprature;


    Button buttonsave;

    Spinner type_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        mDbHelper = new DbHelper(this);
        db = mDbHelper.getWritableDatabase();
//
        city = (EditText) findViewById(R.id.city);
        temprature = (EditText) findViewById(R.id.Temprature);
        buttonsave=findViewById(R.id.buttonsave);







        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // insert into database
                ContentValues cv = new ContentValues();
                cv.put(mDbHelper.NAME, city.getText().toString());
                cv.put(mDbHelper.temprature, temprature.getText().toString()+"°C");


                db.insert(mDbHelper.CITY_TABLE_NAME, null, cv);


                Toast.makeText(add_city.this, "Saved Successfully", Toast.LENGTH_SHORT).show();




            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }



}

package com.weather;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class details extends AppCompatActivity {

    SQLiteDatabase db;
    DbHelper dbHelper;
    EditText name, temprature;

    TextView city_textview,showupdate;

    Button update;

    LinearLayout updatelayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final long id = getIntent().getExtras().getLong(getString(R.string.row_id));

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        name = findViewById(R.id.name);
        temprature = findViewById(R.id.temprature);

        update = findViewById(R.id.update);

        showupdate = findViewById(R.id.show_update);
        city_textview = findViewById(R.id.city_textview);


        updatelayout = findViewById(R.id.updatelayout);
















        Cursor cursor = db.rawQuery("select * from " + dbHelper.CITY_TABLE_NAME + " where " + dbHelper.ID + "=" + id, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {


                this.setTitle(cursor.getString(cursor.getColumnIndex(dbHelper.NAME)));
                city_textview.setText(cursor.getString(cursor.getColumnIndex(dbHelper.NAME))+":" +cursor.getString(cursor.getColumnIndex(dbHelper.temprature)));
                name.setText(cursor.getString(cursor.getColumnIndex(dbHelper.NAME)));
                temprature.setText(cursor.getString(cursor.getColumnIndex(dbHelper.temprature)));





            }
            cursor.close();

        }

//
          update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put(DbHelper.NAME, name.getText().toString());
                cv.put(DbHelper.temprature, temprature.getText().toString()+"°C");



                db.update("city", cv, "_id=" + id, null);

                Toast.makeText(details.this, "updated ", Toast.LENGTH_SHORT).show();



            }
        });



        showupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                updatelayout.setVisibility(View.VISIBLE);




            }
        });
    }



    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }



}

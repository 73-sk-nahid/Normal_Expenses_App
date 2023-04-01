package com.example.clp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tv;
    Button previous, next;
    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tv = findViewById(R.id.tv);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);

        db = openOrCreateDatabase("CLP31", MODE_PRIVATE, null);
        final Cursor c =db.rawQuery("select * from expenses", null);
        c.moveToFirst();
        tv.setText(c.getString(c.getColumnIndex("expense")));

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToPrevious();
                    tv.setText(c.getString(c.getColumnIndex("expense")));
                }
                catch (Exception e) {
                    Toast.makeText(Display.this, "First Record", Toast.LENGTH_SHORT).show();
                }
               
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToNext();
                    tv.setText(c.getString(c.getColumnIndex("expense")));
                }
                catch (Exception e)
                {
                    Toast.makeText(Display.this, "Last Record", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }
}
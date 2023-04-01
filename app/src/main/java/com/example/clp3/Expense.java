package com.example.clp3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Expense extends AppCompatActivity {
EditText et;
Button view, ok;
    SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        et = findViewById(R.id.et);
        view = findViewById(R.id.view);
        ok = findViewById(R.id.enteret);
        db = openOrCreateDatabase("CLP31", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS expenses(expense TEXT);");
ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String store = et.getText().toString();
        db.execSQL("INSERT INTO expenses VALUES('"+store+"');");
        Toast.makeText(Expense.this, "Expense added: "+store, Toast.LENGTH_SHORT).show();
        et.setText("");
    }
});
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display.class));
            }
        });
    }
}
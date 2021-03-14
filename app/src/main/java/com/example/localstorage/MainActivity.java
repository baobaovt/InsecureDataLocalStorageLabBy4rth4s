package com.example.localstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.SharedPreferences);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.txt);
        TextView rTextView = findViewById(R.id.textview);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnNext = findViewById(R.id.btnNext);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("YourText", editText.getText().toString()).apply();
                rTextView.setText("SAVED");

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(in);
            }
        });
    }
}
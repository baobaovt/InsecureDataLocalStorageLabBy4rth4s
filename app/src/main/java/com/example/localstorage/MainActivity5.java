package com.example.localstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity5 extends AppCompatActivity {

    private String fileName ="baonguyen.txt";
    private String filepath = "MyInternalStorage";
    File myInternalFile;
    String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setTitle("InternalStorage");
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File dir = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile =new File(dir,fileName);
        EditText edt = findViewById(R.id.txt5);
        TextView textView = findViewById(R.id.textview5);
        Button submit = findViewById(R.id.btnSend5);
        Button show = findViewById(R.id.btnShow5);
        Button next = findViewById(R.id.btnNext5);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = new FileOutputStream(myInternalFile);
                    fos.write(edt.getText().toString().getBytes());
                    fos.close();
                    Toast.makeText(MainActivity5.this,"SAVED",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = new FileInputStream(myInternalFile);
                    DataInputStream inp = new DataInputStream(fis);
                    BufferedReader buff = new BufferedReader(new InputStreamReader(inp));
                    String strLine;
                    while ((strLine= buff.readLine())!= null){
                        data += strLine;
                    }
                    inp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.setText(myInternalFile.getAbsolutePath());

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity5.this, MainActivity6.class);
                startActivity(in);

            }
        });
    }


}
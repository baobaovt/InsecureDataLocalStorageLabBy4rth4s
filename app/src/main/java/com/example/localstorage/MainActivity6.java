package com.example.localstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity6 extends AppCompatActivity {
    private String fileName ="baonguyen.txt";
    private String filepath = "MyExternalStorage";
    File myExternalFile;
    String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setTitle("ExternalStorage");
        EditText edt = findViewById(R.id.txt6);
        TextView textView = findViewById(R.id.textview6);
        Button submit = findViewById(R.id.btnSend6);
        Button show = findViewById(R.id.btnShow6);
        Button next = findViewById(R.id.btnNext6);
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            submit.setEnabled(false);
        } else myExternalFile = new File(getExternalFilesDir(filepath), fileName);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(edt.getText().toString().getBytes());
                    fos.close();
                    Toast.makeText(MainActivity6.this,"SAVED",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
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
                textView.setText(myExternalFile.getAbsolutePath());

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity6.this, MainActivity7.class);
                startActivity(in);
            }
        });

    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
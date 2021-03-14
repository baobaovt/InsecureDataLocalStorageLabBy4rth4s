package com.example.localstorage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle(R.string.SQLite);
        EditText editText = findViewById(R.id.txt2);
        EditText editText1 = findViewById(R.id.pwd2);
        TextView rTextView = findViewById(R.id.textview2);
        Button btnInsert = findViewById(R.id.insert2);
        Button btnNext = findViewById(R.id.btnNext2);
        Button btnFind = findViewById(R.id.find2);
        Button btnFind2 = findViewById(R.id.findName2);
        dbhelper db = new dbhelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edt = editText.getText().toString();
                String edt2 = editText1.getText().toString();
                Boolean checkInsert = db.AddUser(edt,edt2);
                if(checkInsert == true){
                    Toast.makeText(MainActivity2.this,"Inserted",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity2.this,"Failed",Toast.LENGTH_LONG).show();

                }

            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.Search();
                if(res.getCount()== 0){
                    Toast.makeText(MainActivity2.this,"No Result",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("password :" + res.getString(1) + "\n\n");


                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle("Result");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });
        btnFind2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edt = editText.getText().toString();
                Cursor res = db.FindName(edt);
                if(res.getCount()==0){
                    Toast.makeText(MainActivity2.this,"No Result",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :" + res.getString(0) + "\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle("Result");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(in);
            }
        });



    }
}
package com.example.localstorage;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localstorage.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity4 extends AppCompatActivity {

    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main4);
        setTitle(R.string.RealmDatabase);

        EditText edt = findViewById(R.id.name4);
        EditText edt2 = findViewById(R.id.age4);
        Button insert = findViewById(R.id.insert4);
        Button update = findViewById(R.id.btnShow4);
        Button next = findViewById(R.id.btnNext4);
        TextView textView = findViewById(R.id.textview4);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt.getText().toString();
                String age = edt2.getText().toString();
                Realm.init(getApplicationContext());
                Realm realm = Realm.getInstance(new RealmConfiguration.Builder()
                        .name("baonguyen2.realm")
                        .build());
                realm.beginTransaction();
                UserTestRealm user = realm.createObject(UserTestRealm.class);
                user.setName(name);
                user.setAge(age);
                realm.commitTransaction();
                Toast.makeText(MainActivity4.this,"Success",Toast.LENGTH_LONG).show();


            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm.init(getApplicationContext());
                Realm realm = Realm.getInstance(new RealmConfiguration.Builder()
                        .name("baonguyen2.realm")
                        .build());
                List<UserTestRealm> users= realm.where(UserTestRealm.class).findAll();
                textView.setText("");
                for(int i=0;i<users.size();i++){
                    textView.append(" Name : "+users.get(i).getName()+" Age : "+users.get(i).getAge()+" \n");
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(in);
            }
        });



    }
}
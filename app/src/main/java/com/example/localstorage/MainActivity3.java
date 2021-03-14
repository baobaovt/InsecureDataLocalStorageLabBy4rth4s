package com.example.localstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {

    private DatabaseReference mFirebaseDB;
    private FirebaseDatabase mFirebaseInstance;


    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle(R.string.FireBaseRealTimeDatabase);

        FirebaseApp.initializeApp(MainActivity3.this);
        Button btnSend = findViewById(R.id.btnSend3);
        Button btnNext = findViewById(R.id.btnNext3);
        EditText edt = findViewById(R.id.txt3);
        EditText edt2= findViewById(R.id.email3);


         mFirebaseInstance = FirebaseDatabase.getInstance();
         mFirebaseDB = mFirebaseInstance.getReference("users");


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt.getText().toString();
                String email = edt2.getText().toString();

                if(TextUtils.isEmpty(userId)){
                    createUser(name,email);
                }else
                    updateUser(name,email);


            }
        });
        toggleButton();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(in);
            }
        });

    }

    private void createUser(String name, String email) {

        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDB.push().getKey();
        }

        UserTestFRB user = new UserTestFRB(name, email);

        mFirebaseDB.child(userId).setValue(user);

        addUserChangeListener();
    }
    private void addUserChangeListener() {
        mFirebaseDB.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                EditText edt = findViewById(R.id.txt3);
                EditText edt2= findViewById(R.id.email3);
                TextView txtView = findViewById(R.id.textview3);
                UserTestFRB user = dataSnapshot.getValue(UserTestFRB.class);

                if (user == null) {
                    Log.e("UserNull", "User data is null!");
                    return;
                }

                Log.e("success!!!", "User data is changed!" + user.name + ", " + user.email);

                txtView.setText(user.name + ", " + user.email);

                edt2.setText("");
                edt.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Log.e("failed", "Failed to read user", error.toException());
            }
        });
    }
    private void toggleButton() {
        Button btnSend = findViewById(R.id.btnSend3);
        if (TextUtils.isEmpty(userId)) {
            btnSend.setText("Save");
        } else {
            btnSend.setText("Update");
        }
    }
    private void updateUser(String name, String email) {
        if (!TextUtils.isEmpty(name))
            mFirebaseDB.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDB.child(userId).child("email").setValue(email);
    }


}
package com.example.localstorage;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserTestFRB  {
    public  String name;
    public String email;

    public UserTestFRB(){
    }

    public UserTestFRB(String name, String email){
        this.name = name;
        this.email = email;
    }
}

package com.example.localstorage;

import io.realm.Realm;
import io.realm.RealmObject;

public class UserTestRealm extends RealmObject {

    private String name;
    private String age;
    private int ID;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

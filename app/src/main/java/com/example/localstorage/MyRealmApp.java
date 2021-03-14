package com.example.localstorage;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyRealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

    }
}

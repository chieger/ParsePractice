package com.example.charleshieger.parsepractice;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Message.class);

        Parse.initialize(this, "38gxBWoTm8MgBdaLaoBSAlH5b2GXn3lTOeDJP6zJ", "9MQ8Dumly4WNM0wWTRHHixc9Ga0G2DJoZzxtmN1L");

        // Test creation of object
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */
    }
}



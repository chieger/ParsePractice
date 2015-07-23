package com.example.charleshieger.parsepractice;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Message")
public class Message extends ParseObject{

    // Ensure that your subclass has a public default constructor
    public Message() {
        super();
    }

    // Add a constructor that contains core properties
    public Message(String body) {
        super();
        setBody(body);
    }

    // Use getString and others to access fields
    public String getBody() {
        return getString("body");
    }

    // Use put to modify field values
    public void setBody(String value) {
        put("body", value);
    }

    // Get the user for this item
    public ParseUser getUser() {
        return getParseUser("owner");
    }

    // Associate each item with a user
    public void setOwner(ParseUser user) {
        put("owner" , user);
    }
}

package com.example.charleshieger.parsepractice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends ActionBarActivity {

    // define variable for message field
    EditText etMessageField;
    private ArrayList<Message> messages;
    private ChatMeassagesAdaptor aMessages;
    private ParseQuery<Message> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages = new ArrayList<>();
        aMessages = new ChatMeassagesAdaptor(this, messages);
        // link variable to messege field ID
        etMessageField = (EditText) findViewById(R.id.etMessageField);

        ListView lvMessages = (ListView) findViewById(R.id.lvMessages);
        lvMessages.setAdapter(aMessages);


        updateMessages();
    }

    public void updateMessages() {


        // Define the class we want to query
        query = ParseQuery.getQuery(Message.class);

        // Define the query conditions
        //query.whereEqualTo("owner", ParseUser.getCurrentUser());

        // Execute the find asyncronously
        query.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> list, ParseException e) {
                if (e == null) {
                    // access the array of results here
                    messages = (ArrayList<Message>) list;
                    aMessages.notifyDataSetChanged();
                    // test to see what we got
                    //String firstMessage = list.get(1).getBody();
                    //Toast.makeText(ChatActivity.this,firstMessage, Toast.LENGTH_LONG).show();
                } else {
                    // something went wrong
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    public void onSend(View v) {
        // get message content from messege field as a string and store it
        String messageContent = etMessageField.getText().toString();

        Message message = new Message(messageContent);
        message.setOwner(ParseUser.getCurrentUser());
        message.saveInBackground();
        etMessageField.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

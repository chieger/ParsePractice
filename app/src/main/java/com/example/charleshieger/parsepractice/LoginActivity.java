package com.example.charleshieger.parsepractice;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;


public class LoginActivity extends ActionBarActivity {

    // define variables for email and password fields
    EditText etUsernameField;
    EditText etPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Link email and password variables to their IDs
        etUsernameField = (EditText) findViewById(R.id.etUsernameField);
        etPasswordField = (EditText) findViewById(R.id.etPasswordField);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void onSignIn(View v) {

        // Get the username and password and store in variable
        String username = etUsernameField.getText().toString();
        String password = etPasswordField.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, com.parse.ParseException e) {
                if (e == null) {
                    // Awesome! Your in!!!
                    Intent i = new Intent(LoginActivity.this, ChatActivity.class);
                    startActivity(i);
                } else {
                    // show a toast with the problem
                    Toast error = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    error.setGravity(Gravity.TOP, 0, 80);
                    error.show();
                }
            }
        });

    }

    public void onSignUp(View v) {

        // Get the username and password and store in variable
        String username = etUsernameField.getText().toString();
        String password = etPasswordField.getText().toString();

        // Create ParseUser
        ParseUser user = new ParseUser();

        // Set core properties
        user.setUsername(username);
        user.setPassword(password);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    // Awesome! Your in!!!
                    Intent i = new Intent(LoginActivity.this, ChatActivity.class);
                    startActivity(i);
                } else {
                    // SignUp didn't succeed. Look at ParseException to figure out the problem
                    Toast error = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    error.setGravity(Gravity.TOP, 0, 80);
                    error.show();

                }
            }
        });

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

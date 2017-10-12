package com.example.jaykang.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.duration;

public class LoginActivity extends Activity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    protected static final String FILE_NAME  =  "com.example.app";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*for saving data to a file on your device and returning object for reading and writing data */
        final SharedPreferences prefs = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        final EditText editText = (EditText) findViewById(R.id.editText);

        editText.setText(prefs.getString("DefaultEmail", "domaingmail.com"));
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editor.putString("DefaultEmail", editText.getText().toString());
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                editor.commit();
                startActivity(intent);
                /*when all your values have been set to save them to the file .*/

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()" );
    }

}

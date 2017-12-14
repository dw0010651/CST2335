package com.example.jaykang.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected Button button;
    protected Button start_button;
    protected Button btn_weather;
    protected static final String ACTIVITY_NAME = "StartActivity";
    protected int MESSAGE_REQUST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME,"In onCreate()" );
            button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                        startActivityForResult(intent, MESSAGE_REQUST_CODE); //intent, requestCode

            }
        });

        start_button = (Button)findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME, "User clicked Start chart");
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });

        btn_weather = (Button) findViewById(R.id.btn_weather);
        btn_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.i(ACTIVITY_NAME, getString(R.string.LogMesStartChat));
                Intent intent = new Intent(StartActivity.this, WeatherForecast1.class);
                startActivity(intent);

            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);

        if (requestCode == MESSAGE_REQUST_CODE) {
            Log.i(ACTIVITY_NAME, getString(R.string.toast_alt2));
        }

        //String messagePassed = data.getStringExtra("data");

        if (responseCode == Activity.RESULT_OK) {
            Log.i(ACTIVITY_NAME, getString(R.string.toast_alt2));
        }

        CharSequence text = getString(R.string.toast_alt1);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

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

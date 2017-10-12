package com.example.jaykang.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class ChatWindow extends Activity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    protected ListView listView;
    protected EditText editText;
    protected Button send;
    protected ArrayList<String> messageStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = (ListView)findViewById(R.id.listViewChat);



        editText = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send);
        messageStore = new ArrayList<String>();

        final ChatAdaper messageAdaper = new ChatAdaper(this);
        listView.setAdapter(messageAdaper);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageStore.add(editText.getText().toString());
//                messageAdaper.notifyDataSetChanged();
                editText.setText("");
                Log.i(ACTIVITY_NAME,"They clicked on send");
            }
        });
    }




    private class ChatAdaper extends ArrayAdapter<String> {

        public ChatAdaper(Context context) {
            super(context, 0);
        }


        public int getCount() {
            return messageStore.size();
        }

        public String getItem(int position) {
            return messageStore.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;


        }
    }
}

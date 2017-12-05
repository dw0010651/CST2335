package com.example.jaykang.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class ChatWindow extends Activity {
    static final String ACTIVITY_NAME = "ChatWindow";
    ListView listView;
    EditText editText;
    Button send;
    ArrayList<String> messageStore;
    SQLiteDatabase db;
    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = (ListView) findViewById(R.id.listViewChat);
        editText = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send);
        messageStore = new ArrayList<String>();
        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        try {
            ChatDatabaseHelper cdHelp = new ChatDatabaseHelper(this);
            db = cdHelp.getWritableDatabase();


            String sql = "SELECT * FROM " + cdHelp.TABLE_NAME;
            Cursor cursor = db.rawQuery(sql, null);

            cursor.moveToFirst();


            while (!cursor.isAfterLast()) {

                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
                String text = cursor.getString(cursor.getColumnIndex(cdHelp.KEY_MESSAGE));
                messageStore.add(text);
                listView.setAdapter(messageAdapter);
                cursor.moveToNext();

            }

            Log.i(ACTIVITY_NAME, "Cursor's column count=" + cursor.getColumnCount());

            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, cursor.getColumnName(i));

            }


            final ContentValues content = new ContentValues();


            send.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View view) {
                            String editMsg = editText.getText().toString();
                            messageStore.add(editMsg);
                            content.put("message", editMsg);
                            db.insert(ChatDatabaseHelper.TABLE_NAME, null, content);
                            messageAdapter.notifyDataSetChanged();
                            editText.setText("");
                            Log.i(ACTIVITY_NAME, "They clicked on send");
                        }
                    });
        } catch (Exception e){
            e.getStackTrace();

        }
    }

    public void onDestroy() {

        Log.i(ACTIVITY_NAME, "In onDestroy()");
        super.onDestroy();
        db.close();

    }


    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context context) {
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

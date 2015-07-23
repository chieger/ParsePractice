package com.example.charleshieger.parsepractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatMeassagesAdaptor extends ArrayAdapter<Message> {

        public ChatMeassagesAdaptor(Context context, List<Message> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);
        tvMessage.setText(message.getBody());
        return convertView;
    }
}

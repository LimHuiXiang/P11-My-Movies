package com.example.democustomcontactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Contact> contactList;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> objects){
        super (context,resource,objects);
        parent_context = context;
        layout_id = resource;
        contactList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object - Get
        LayoutInflater inflater = (LayoutInflater)
        parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row - Read
        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvCode = rowView.findViewById(R.id.textViewCountryCode);
        TextView tvNum = rowView.findViewById(R.id.textViewPhoneNum);
        ImageView ivGender = rowView.findViewById(R.id.imageViewGender);
        Contact currentItem = contactList.get(position);
        tvName.setText(currentItem.getName());
        tvCode.setText("+" + currentItem.getCountryCode());
        tvNum.setText(currentItem.getPhoneNum() + "");

        if (currentItem.getName().equalsIgnoreCase("Ken")){
            ivGender.setImageResource(R.drawable.male);
            String imageUrl ="https://commons.wikimedia.org/wiki/File:Male_Blue.png";
            Picasso.with(parent_context).load(imageUrl).into(ivGender);
        }
        if (currentItem.getName().equalsIgnoreCase("Mary")){
            ivGender.setImageResource(R.drawable.female);
        }

        return rowView;

    }




    }

package com.scravlon.ubhaknight_spring2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class itemAdapter extends ArrayAdapter<Items> {

    private final List<Items> itemList;
    private final Context context;

    public itemAdapter(@NonNull Context context, List<Items> objects) {
        super(context, 0, objects);
        this.context = context;
        this.itemList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem ==null){
            listItem = LayoutInflater.from(context).inflate(R.layout.add_item_adapter,parent,false);
        }
        Items item = itemList.get(position);
        TextView txt1 = listItem.findViewById(R.id.text_name);
        TextView txt2 = listItem.findViewById(R.id.text_remark);
        TextView txt3 = listItem.findViewById(R.id.text_amount);
        txt1.setText(item.getItemName());
        txt2.setText(item.getRemark());
        txt3.setText(String.valueOf(item.getAmount()));

        return listItem;
    }

}

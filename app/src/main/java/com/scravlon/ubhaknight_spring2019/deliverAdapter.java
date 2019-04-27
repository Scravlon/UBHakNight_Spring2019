package com.scravlon.ubhaknight_spring2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class deliverAdapter extends ArrayAdapter<requestItem> {
    private final List<requestItem> itemList;
    private final Context context;

    public deliverAdapter(@NonNull Context context, List<requestItem> objects) {
        super(context, 0, objects);
        this.context = context;
        this.itemList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem ==null){
            listItem = LayoutInflater.from(context).inflate(R.layout.deliver_adapter,parent,false);
        }
        requestItem rq = itemList.get(position);
        List<Items> alItem = rq.listItem;
        String items = "";
        for(Items i : alItem){
            items+= i.getItemName() + " - "+ i.getRemark() + " - " + i.getAmount() +"\n";
        }

        TextView txt1 = listItem.findViewById(R.id.text_name);
        TextView txt2 = listItem.findViewById(R.id.text_item);
        txt1.setText(rq.requestUser);
        txt2.setText(items);

        return listItem;
    }
}

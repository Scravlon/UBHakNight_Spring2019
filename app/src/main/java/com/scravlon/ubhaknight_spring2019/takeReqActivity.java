package com.scravlon.ubhaknight_spring2019;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class takeReqActivity extends AppCompatActivity {

    List<requestItem> list_Item; //databse list
    deliverAdapter adapter;
    ListView lw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_req);
        getList();
        lw = findViewById(R.id.listview);
        adapter = new deliverAdapter(this,list_Item);
        lw.setAdapter(adapter);


    }

    private boolean getList(){
        SharedPreferences sharedPreferences = getSharedPreferences("reqItems", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("item_list", null);
        Type type = new TypeToken<ArrayList<requestItem>>() {
        }.getType();
        list_Item = gson.fromJson(json, type);
        if (list_Item == null) {
            list_Item = new ArrayList<>();
            Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();

            return false;
        }
        Toast.makeText(this, "Something is here", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void updateUserList() {
        SharedPreferences sharedPreferences = getSharedPreferences("reqItems", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list_Item);
        editor.putString("item_list", json);
        editor.apply();
    }

}

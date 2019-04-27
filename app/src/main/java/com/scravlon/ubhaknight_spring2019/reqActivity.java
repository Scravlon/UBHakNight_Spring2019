package com.scravlon.ubhaknight_spring2019;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class reqActivity extends AppCompatActivity {

    List<requestItem> list_Item; //databse list
    List<Items> newItem;        //new Added request
    itemAdapter adapter;
    ListView listView;
    Button but_add;
    Button but_inc;
    Button but_dec;
    Button but_done;
    EditText et_name;
    EditText et_amount;
    EditText et_remark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);

        getList();
        requestItem rq = new requestItem("kokhaoyo");
        initView();

        newItem = new ArrayList<>();
        adapter = new itemAdapter(this,newItem);
        listView.setAdapter(adapter);

        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String remark = et_remark.getText().toString();
                int amount = Integer.valueOf(et_amount.getText().toString());

                if(name.equals("")){
                    Toast.makeText(reqActivity.this, "Name or amount cannot be empty!", Toast.LENGTH_SHORT).show();
                } else{
                    Items item = new Items(name,remark,amount);
                    newItem.add(item);
                    et_name.setText("");
                    et_remark.setText("");
                    et_amount.setText("1");

                    adapter.notifyDataSetChanged();
                }
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.list_item);
        but_add = findViewById(R.id.but_addItem);
        but_inc = findViewById(R.id.but_inc);
        but_dec = findViewById(R.id.but_dec);
        but_done = findViewById(R.id.but_finish);
        et_amount = findViewById(R.id.et_amount);
        et_name = findViewById(R.id.et_itemName);
        et_remark = findViewById(R.id.et_remark);
    }

    private boolean getList(){
        SharedPreferences sharedPreferences = getSharedPreferences("reqItems", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("item_list", null);
        Type type = new TypeToken<ArrayList<requestItem>>() {
        }.getType();
        list_Item = gson.fromJson(json, type);
        if (list_Item == null) {
            return false;
        }
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

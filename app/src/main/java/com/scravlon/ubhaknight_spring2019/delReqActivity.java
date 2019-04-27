package com.scravlon.ubhaknight_spring2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class delReqActivity extends AppCompatActivity {

    String loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_req);
        Button but_deliver = findViewById(R.id.but_deliver);
        Button but_request = findViewById(R.id.but_request);
        getExtra();
       // Log.d("tester","asdsa");
        but_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),delActivity.class);
                startActivity(intent);
            }
        });

        but_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),reqActivity.class);
                intent.putExtra("loginUser", loginUser);
                startActivity(intent);

            }
        });
    }

    private void getExtra(){
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
// get data via the key
        String value1 = extras.getString("loginUser");
        if (value1 != null) {
            loginUser = value1;
            Log.d("tester",value1);
        }
    }


}

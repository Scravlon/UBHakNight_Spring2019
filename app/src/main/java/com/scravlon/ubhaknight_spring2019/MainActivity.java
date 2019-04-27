package com.scravlon.ubhaknight_spring2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 99;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            /*For simplicity, force user number and password as followings*/
            final String loginNumber = "kokhaoyo";
            final String password = "kokhaoyo";
            final String loginName = "edwinhun";
            final String loginPassword = "edwinhun";


            final EditText editnumber = findViewById(R.id.edit_number);
        final EditText editpass = findViewById(R.id.edit_password);
        Button butLogin = findViewById(R.id.but_login);
        requestLocationService();
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernumber = editnumber.getText().toString();
                String userpass = editpass.getText().toString();
                if((usernumber.equals(loginNumber) && userpass.equals(password)) || (usernumber.equals(loginName) && userpass.equals(loginPassword))){
                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    changeIntent();
                } else{
                    Toast.makeText(MainActivity.this, "Wrong credential!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    /**
     * Request the location service from the user, required Android a6.0 and higher
     *
     * Reference: https://developer.android.com/training/permissions/requesting
     */
    private void requestLocationService() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_COARSE_LOCATION);
            }
        } else {
        }

    }

    /**
     * Handle the Location request
     * @param requestCode: Code of request
     * @param permissions: permission
     * @param grantResults: result
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                return;
            }
        }
    }

    /**
     * Update the activity to the sub Main activity
     */
    private void changeIntent(){
        Intent intent = new Intent(this,delReqActivity.class);
        startActivity(intent);
        finish();
    }

}

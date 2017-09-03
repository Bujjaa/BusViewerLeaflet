package com.example.android.busviewerleaflet;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.Manifest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkPermissions();




    }

    private void checkPermission(String [] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 1);
            }
        }
    }

    public void gotoMaps(View view){
        String[] permissions = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};
        checkPermission(permissions);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void logout(View view){
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            //mLoginActivity.signOut();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void leaflet(View view){
        String[] permissions = new String[] {Manifest.permission.INTERNET};
        checkPermission(permissions);

            Intent intent = new Intent(this, LeafletActivity.class);
            startActivity(intent);
       /* Uri uri = Uri.parse("http://www.bujjaa.bplaced.net");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
*/


    }
    public void checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permissions = new String[]{Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION};
            boolean flag = false;
            for (int i = 0; i < permissions.length; i++) {
                if (checkSelfPermission(permissions[i]) == PackageManager.PERMISSION_DENIED) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                requestPermissions(permissions, 1);
            }

        }
    }


}

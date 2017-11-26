package com.example.android.busviewerleaflet;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;

import com.example.android.busviewerleaflet.Fragments.AboutFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private long backPressedTime = 0;
    ListView list;
    CustomAdapter adapter;
    public  MainActivity CustomListView = null;
    public ArrayList<ListModel> customListViewValuesArr = new ArrayList<ListModel>();
    String[] a = new String[50];
    ArrayList<String> alist = new ArrayList<>();
    ArrayList<String> blist = new ArrayList<>();
    ArrayList<String> clist = new ArrayList<>();

    private ArrayAdapter<String> mAdapter;
    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkPermissions();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Buslinien");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> results, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < results.size(); i++) {
                        String [] a = new String[results.size()];
                        String [] b = new String[results.size()];
                        String [] c = new String[results.size()];

                        a[i] = (results.get(i).getString("Buslinie"));
                        b[i] = (results.get(i).getString("Start"));
                        c[i] = (results.get(i).getString("Ende"));
                        alist.add(a[i]);
                        blist.add(b[i]);
                        clist.add(c[i]);
                        Log.d("Main","Arraylist b"+blist.get(i));


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                mCustomAdapter.notifyDataSetChanged();

            }
        });




        

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new AboutFragment());
        fragmentTransaction.commit();
        */

        ListView listView = (ListView) findViewById(R.id.list);
        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,blist);
        mCustomAdapter = new CustomAdapter(this,alist,blist,clist);
        listView.setAdapter(mCustomAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ux1) {
            // Handle the camera action
            String message = "ux1";
            String[] permissions = new String[] {Manifest.permission.INTERNET};
            checkPermission(permissions);
            Intent intent = new Intent(this, LeafletActivity.class);
            intent.putExtra("Buslinie", message );
            startActivity(intent);
        } else if(id == R.id.nav_ux2) {
            String message = "ux2";
            String[] permissions = new String[] {Manifest.permission.INTERNET};
            checkPermission(permissions);
            Intent intent = new Intent(this, LeafletActivity.class);
            intent.putExtra("Buslinie", message );
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void checkPermission(String [] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 1);
            }
        }
    }




    public void leaflet(View view){
        String[] permissions = new String[] {Manifest.permission.INTERNET};
        checkPermission(permissions);
/*
        int id = view.getId();
        if (id == R.id.imageUX1) {
            String message = "ux1";
            Intent intent = new Intent(this, LeafletActivity.class);
            intent.putExtra("Buslinie", message );
            startActivity(intent);
        } else if (id == R.id.imageUX2){
            String message = "ux2";
            Intent intent = new Intent(this, LeafletActivity.class);
            intent.putExtra("Buslinie", message );
            startActivity(intent);
        }

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
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (t - backPressedTime > 2000) {    // 2 secs
                backPressedTime = t;
                Toast.makeText(this, "Press back again to close the app",
                        Toast.LENGTH_SHORT).show();
            } else {    // this guy is serious
                // clean up#
                finish();
                System.exit(0);
            }
        }
    }


    //used by adapter
    public void onItemClick(int mPosition) {

    }
}

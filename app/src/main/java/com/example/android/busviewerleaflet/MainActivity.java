package com.example.android.busviewerleaflet;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.busviewerleaflet.Fragments.ListFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListFragment.Callback, NavigationView.OnNavigationItemSelectedListener {

    private long backPressedTime = 0;
    ListView list;
    CustomAdapter adapter;
    public MainActivity CustomListView = null;
    public ArrayList<ListModel> customListViewValuesArr = new ArrayList<ListModel>();
    String[] a = new String[50];
    ArrayList<String> alist = new ArrayList<>();
    ArrayList<String> blist = new ArrayList<>();
    ArrayList<String> clist = new ArrayList<>();

    private ArrayAdapter<String> mAdapter;
    private CustomAdapter mCustomAdapter;
    private FragmentTransaction fragmentTransaction;
    private FusedLocationProviderClient mFusedLocationClient;
    String locationGPS;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new ListFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

/*
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
              //  mCustomAdapter.notifyDataSetChanged();

            }
        });




        



/*

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,blist);
        mCustomAdapter = new CustomAdapter(this,alist,blist,clist);
        recyclerView.setAdapter(mCustomAdapter);
        */
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Main position test", ""+alist.get(position));
            }
        });
*/

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    locationGPS = ""+location.getLatitude()+location.getLongitude();
                }

            }
        });

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



    //used by adapter
    public void onItemClick(int mPosition) {

    }

    @Override
    public void onListItemSelected(String string) {
        Bundle bundle = new Bundle();
        bundle.putString("Buslinie",string);
        bundle.putString("GPS",locationGPS);
        Leafletfragment lf = new Leafletfragment();
        lf.setArguments(bundle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, lf);
        fragmentTransaction.addToBackStack("webview");
        fragmentTransaction.commit();

    }
}

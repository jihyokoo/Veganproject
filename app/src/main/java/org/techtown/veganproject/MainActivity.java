package org.techtown.veganproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import org.techtown.veganproject.ui.map.FavoriteAdapter;
import org.techtown.veganproject.ui.map.mapFragment;

public class MainActivity extends AppCompatActivity {

    private String fName;
    private double lat, lon;
    public static final int REQUEST_CODE_BOOKMARK = 101;


    private AppBarConfiguration mAppBarConfiguration;
    private Object mapFragment;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






       /* marker_name_nav = new NavArgument.Builder().build();
        marker_lat_nav = new NavArgument.Builder().build();
        marker_lon_nav = new NavArgument.Builder().build();*/



       /* Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){

            //즐겨찾기에서 넘어 좌표
            fName = bundle.getString("Name");
            lat = bundle.getDouble("Lat");
            lon = bundle.getDouble("Lon");

            Log.d("이름", intent.getExtras().getString("Name"));
            Log.d("경도", String.valueOf(intent.getExtras().getDouble("Lat")));
            Log.d("위도", String.valueOf(intent.getExtras().getDouble("Lon")));

            mapFragment fragment = new mapFragment();
            bundle.putString("Name",fName);
            bundle.putDouble("Lat",lat);
            bundle.putDouble("Lon",lon);
            fragment.setArguments(bundle);

        }*/



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_diary, R.id.nav_map,
                R.id.nav_barcode, R.id.nav_recipe, R.id.nav_setting)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    String marker_name;
    Double marker_lat;
    Double marker_lon;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_BOOKMARK||resultCode==RESULT_OK){

            marker_name = data.getStringExtra("marker_name");
            marker_lat = data.getDoubleExtra("marker_lat",0);
            marker_lon = data.getDoubleExtra("marker_lon",0);

            /*sharedPreferences = getSharedPreferences("s_file",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("marker_name",marker_name);
            editor.commit();*/


            Log.d("마커이름받기 메인 액티비티", String.valueOf(marker_name));
            Log.d("마커위도받기 메인 액티비티", String.valueOf(marker_lat));
            Log.d("마커경도받기 메인 액티비티", String.valueOf(marker_lon));






            // Intent intent = new Intent(getApplicationContext(),mapFragment.class);

            //intent.putExtra("marker_name",marker_name);

            //  Log.d("인텐트에는 뭐가 있나용_메인 액티비티", String.valueOf(intent));

        }
        else{
            Log.d("데이터가 전달되지 않음","");
        }


    }

    public void getData(Intent data)
    {

    }

}
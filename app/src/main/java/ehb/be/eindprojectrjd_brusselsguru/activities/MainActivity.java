package ehb.be.eindprojectrjd_brusselsguru.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ehb.be.eindprojectrjd_brusselsguru.R;
import ehb.be.eindprojectrjd_brusselsguru.fragments.ListFragment;
import ehb.be.eindprojectrjd_brusselsguru.fragments.POIMapFragment;
import ehb.be.eindprojectrjd_brusselsguru.model.Museum;
import ehb.be.eindprojectrjd_brusselsguru.util.GetMuseumHandler;
import ehb.be.eindprojectrjd_brusselsguru.util.GetVilloHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnInfoWindowClickListener {

    private DrawerLayout menuDrawerLayout;
    private NavigationView navView;
    private Toolbar toolBar;
    private GetMuseumHandler handler;
    private ArrayList<Museum> museumList;

    private GetVilloHandler villoHandler;

    private ListFragment listFragment;
    private boolean isMap = true;//dit is om laten naar mijn map terug te keren


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_container, POIMapFragment.newInstance())
                .commit();

        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, menuDrawerLayout, toolBar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        menuDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

    /*    museumList = new ArrayList<Museum>();
        handler = new GetMuseumHandler(museumList);
        downloadData();

        Toast.makeText(getApplicationContext(),"size of museum list " + museumList.size(),Toast.LENGTH_LONG).show();
*/
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, POIMapFragment.newInstance())
                .commit();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(!sp.getBoolean("data_downloaded", false)) {
            villoHandler = new GetVilloHandler(getApplicationContext());
            downloadVilloData();
        }
    }

    private void downloadVilloData() {
        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.jcdecaux.com/vls/v1/stations?apiKey=6d5071ed0d0b3b68462ad73df43fd9e5479b03d6&contract=Bruxelles-Capitale")
                        .get()
                        .build();

                Response response = client.newCall(request).execute();

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("villo_data", response.body().string());
                msg.setData(data);
                villoHandler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        backGroundThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    // mijn knop switch laten werken tussen map/lijst
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mi_switch) {

            isMap = !isMap;

            if(isMap) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, POIMapFragment.newInstance()).commit();
            }
            else{
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, ListFragment.newInstance()).commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

   /*     DetailsFragment detailsFragment = DetailsFragment.newInstance((Show) marker.getTag());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, detailsFragment)
                .addToBackStack("back")
                .commit();
*/
    }


}

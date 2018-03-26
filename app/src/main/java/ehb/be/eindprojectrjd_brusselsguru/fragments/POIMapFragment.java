package ehb.be.eindprojectrjd_brusselsguru.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;

import ehb.be.eindprojectrjd_brusselsguru.R;
import ehb.be.eindprojectrjd_brusselsguru.model.Museum;
import ehb.be.eindprojectrjd_brusselsguru.model.POIDatabase;
import ehb.be.eindprojectrjd_brusselsguru.model.Villo;

import static java.security.AccessController.getContext;


/**
 * Created by dajian on 19/03/18.
 */

public class POIMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapView;
    private ArrayList<Museum> museumList;
    private ArrayList<Villo> villoList;




    public static POIMapFragment newInstance(){
        return new POIMapFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.map_fragment, container, false);

        mapView = rootView.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        museumList = new ArrayList<>();
        museumList.addAll(POIDatabase.getInstance(getContext()).getMuseumDao().getAllMuseum());



        mapView = rootView.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        villoList = new ArrayList<>();
        villoList.addAll(POIDatabase.getInstance(getContext()).getVilloDAO().getAllVillo());



        return rootView;
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMuseumMarkers();

        LatLng coordCam = new LatLng(50.856055, 4.348970);

        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) getContext());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordCam));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.7f ) );

        }

    private void addMuseumMarkers() {

        for (Museum e : museumList) {

            Marker eMarker = mMap.addMarker(new MarkerOptions()
                    .position(e.getCoord())
                    .title(e.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }

        for (Villo v : villoList) {

            Marker eMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(v.getLat(), v.getLng()))
                    .title(v.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        }
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}


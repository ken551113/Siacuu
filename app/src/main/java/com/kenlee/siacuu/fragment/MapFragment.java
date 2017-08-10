package com.kenlee.siacuu.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kenlee.siacuu.model.Local;
import com.kenlee.siacuu.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;



/**
 * A simple {@link Fragment} subclass.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback,EasyPermissions.PermissionCallbacks{

    private GoogleMap mMap;
    private FirebaseDatabase database;

    private String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        initMap();

        return v;
    }

    @AfterPermissionGranted(112)
    public void initMap(){
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            Toast.makeText(getActivity(), "定位開啟", Toast.LENGTH_LONG).show();
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else {
            EasyPermissions.requestPermissions(this, "定位需要GPS權限",
                    112, perms);
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json);
        mMap.setMapStyle(style);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(24.1402601,120.6818181) , 16.0f) );
        loadMarker();
    }

    public void loadMarker(){
        DatabaseReference Local = database.getReference("Local");
        Local.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                mMap.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshots) {
                    com.kenlee.siacuu.model.Local local = dataSnapshot1.getValue(Local.class);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(local.getLatitude(), local.getLongitude())).title(local.getName()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);


    }
}

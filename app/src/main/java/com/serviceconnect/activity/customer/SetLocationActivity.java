package com.serviceconnect.activity.customer;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.serviceconnect.R;


import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SetLocationActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    ImageView iv_back, iv_fetchLocation, iv_cancel;
    TextView tv_title, tv_selectLocation;
    EditText ed_currentLocation;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Geocoder geocoder;
    List<Address> addresses;
    Dialog addMoreDetailsDialog;
    Dialog addOtherLocation;
    String currentLocationAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_set_location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();
        initViews();
    }

    private void initViews() {
        iv_back = findViewById(R.id.ivBack);
        tv_title = findViewById(R.id.tv_title);
        iv_fetchLocation = findViewById(R.id.iv_fetchLocation);
        ed_currentLocation = findViewById(R.id.ed_currentLocation);
        tv_selectLocation = findViewById(R.id.tv_selectLocation);
        iv_cancel = findViewById(R.id.iv_cancel);

        tv_title.setText("Set Location");
        iv_back.setOnClickListener(this);
        iv_fetchLocation.setOnClickListener(this);
        tv_selectLocation.setOnClickListener(this);
        iv_cancel.setOnClickListener(this);
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    geocoder=new Geocoder(SetLocationActivity.this, Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(currentLocation.getLatitude() , currentLocation.getLongitude(), 1);
                        currentLocationAddress = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName();

                        Log.e("<<TAG>>", "address index=" + currentLocationAddress);
                        Log.e("<<TAG>>", "city index=" + city);
                        Log.e("<<TAG>>", "state index=" + state);
                        Log.e("<<TAG>>", "country index=" + country);
                        Log.e("<<TAG>>", "postalCode index=" + postalCode);
                        Log.e("<<TAG>>", "knownName index=" + knownName);
                        ed_currentLocation.setText(currentLocationAddress);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(SetLocationActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_point_icon))
                .title("I am here!");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50));
        googleMap.addMarker(markerOptions);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_fetchLocation:
                fetchLocation();
                break;
            case R.id.tv_selectLocation:
                openAddMoreDetailsDialog();
                break;
            case R.id.iv_cancel:
                if(!currentLocationAddress.equals("")){
                    ed_currentLocation.setText("");
                }
                break;
        }
    }

    private void openAddMoreDetailsDialog() {
        addMoreDetailsDialog = new Dialog(this);
        addMoreDetailsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addMoreDetailsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addMoreDetailsDialog.setContentView(R.layout.customer_dialog_add_more_details);
        addMoreDetailsDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addMoreDetailsDialog.show();

        TextView tv_done = addMoreDetailsDialog.findViewById(R.id.tv_done);
        TextView tv_address = addMoreDetailsDialog.findViewById(R.id.tv_address);
        EditText tv_address_with_suite_appartment = addMoreDetailsDialog.findViewById(R.id.tv_address_with_suite_appartment);

        tv_address.setText(currentLocationAddress);

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoreDetailsDialog.dismiss();
                openDialogOtherLocation();
            }
        });


    }

    private void openDialogOtherLocation() {
        addOtherLocation = new Dialog(this);
        addOtherLocation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addOtherLocation.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addOtherLocation.setContentView(R.layout.customer_dialog_other_location);
        addOtherLocation.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addOtherLocation.show();


        Button tv_no = addOtherLocation.findViewById(R.id.tv_no);
        Button tv_yes = addOtherLocation.findViewById(R.id.tv_yes);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOtherLocation.dismiss();

            }
        });

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOtherLocation.dismiss();
            }
        });

    }


}
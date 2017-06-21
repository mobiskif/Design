package mobiskif.healthy;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by user on 02.06.2017.
 */

public class GoogleMaps implements OnMapReadyCallback {
    public GoogleMap mMap;
    private LocationManager locationManager;
    Activity activity;
    int Zoom = 13;

    public GoogleMaps(Activity a) {
        activity = a;
        MapView mv = (MapView) activity.findViewById(R.id.mapView);
        mv.onCreate(null);
        mv.getMapAsync(this);
        //mv.setAlpha((float) 1.00);
        locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
    }

    public LatLng getLocationFromAddress(String strAddress) {
        double lat = 0.0;
        double lng = 0.0;

        Geocoder coder = new Geocoder(activity);

        try {
            List<Address> address;
            address = coder.getFromLocationName(strAddress, 5);
            Address location = address.get(0);
            lat = location.getLatitude();
            lng = location.getLongitude();
        } catch (Exception e) {
        }

        //moveTo(lat,lng);
        LatLng mapCenter = new LatLng(lat, lng);
        return mapCenter;
    }

    void moveTo(double lat, double lng) {
        LatLng mapCenter = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, Zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        L.d("mapReady", this);
        mMap = googleMap;
        //((MapsActivity) activity).prepareMap();
/*
        // Flat markers will rotate when the map is rotated, and change perspective when the map is tilted.
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                .position(mapCenter)
                .flat(true)
                .rotation(245));
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(mapCenter)
                .zoom(18)
                .bearing(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, Zoom));
*/
    }

    public void resume() {
        L.d("resume", this);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
    }

    public void pause() {
        L.d("pause", this);
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            L.d("locationChanged " + location.getLatitude() + " = " + location.getLongitude(), this);
            LatLng mapCenter = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                    .position(mapCenter)
            );
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, Zoom));
        }

        @Override
        public void onProviderDisabled(String provider) {
            //checkEnabled();
        }

        @Override
        public void onProviderEnabled(String provider) {
            //checkEnabled();
            //showLocation(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (provider.equals(LocationManager.GPS_PROVIDER)) {
                L.d(String.valueOf(status), this);
            } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                L.d(String.valueOf(status), this);
            }
        }
    };

}

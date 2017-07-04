package mobiskif.healthy;

import android.app.Activity;
import android.content.Context;
import android.database.MatrixCursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.Adapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class BaseMapView extends MapView implements OnMapReadyCallback {
    public GoogleMap mMap;
    private LocationManager locationManager;
    //Activity activity;
    int Zoom = 13;
    Context context;
    Geocoder coder;

    public BaseMapView(Context context) {
        super(context);
    }

    public BaseMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if ( !isInEditMode() ) {
            this.context = context;
            coder = new Geocoder(context);
            onCreate(null);
            getMapAsync(this);
        }
    }

    public BaseMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void refresh() {
        //locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                return new ActionAdapter( (Activity) o[0], "GetOrgList");
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                prepareMap((Adapter)o);
            }
        };
        at.execute(context);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        refresh();
    }

    void prepareMap(Adapter adapter) {
        LatLng mapCenter = new LatLng(59.857, 30.204);
        int nrows = adapter.getCount();
        if (nrows > 5) nrows = 5;
        for (int i = 0; i < nrows; i++) {
            MatrixCursor item = (MatrixCursor) adapter.getItem(i);
            mapCenter = getLocationFromAddress(item.getString(3));
            //L.d(mapCenter.latitude+" "+mapCenter.longitude,this);
            mMap.addMarker(new MarkerOptions().position(mapCenter).title(item.getString(1)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, Zoom));
        onResume();
    }

    public LatLng getLocationFromAddress(String strAddress) {
        double lat = 0.0;
        double lng = 0.0;
        try {
            List<Address> address;
            address = coder.getFromLocationName(strAddress, 5);
            Address location = address.get(0);
            lat = location.getLatitude();
            lng = location.getLongitude();
        } catch (Exception e) {}
        //moveTo(lat,lng);
        LatLng mapCenter = new LatLng(lat, lng);
        return mapCenter;
    }

    void moveTo(double lat, double lng) {
        LatLng mapCenter = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, Zoom));
    }
}

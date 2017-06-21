package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity implements AdapterView.OnItemClickListener {
    public GoogleMaps googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity);
        googleMaps = new GoogleMaps(this);
        new MapsAsyncTask().execute(this);
        ((ListView) findViewById(R.id.listView)).setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleMaps.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleMaps.pause();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        L.d(parent.getClass().getName(), this);
        Cursor item = (Cursor) parent.getAdapter().getItem(position);
        intent.putExtra("_ID", item.getString(0));
        intent.putExtra("value1", item.getString(1));
        intent.putExtra("value2", item.getString(2));
        intent.putExtra("value3", item.getString(3));
        intent.putExtra("action", getIntent().getStringExtra("action"));
        setResult(RESULT_OK, intent);
        finish();
    }

    void prepareMap(DataAdapter adapter) {
        GoogleMap mMap = googleMaps.mMap;
        LatLng mapCenter = new LatLng(59.857, 30.204);

        int nrows = adapter.getCount();
        if (nrows > 50) nrows = 50;
        for (int i = 0; i < nrows; i++) {
            MatrixCursor item = (MatrixCursor) adapter.getItem(i);
            mapCenter = googleMaps.getLocationFromAddress(item.getString(3));
            mMap.addMarker(new MarkerOptions().position(mapCenter).title(item.getString(1)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 12));
        ((MapView) findViewById(R.id.mapView)).onResume();
    }

}

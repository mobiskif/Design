package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {"Иван", "Марья", "Петр", "Антон", "Дарья", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей", "Марья", "Петр", "Антон", "Дарья"};
        ListView lvMain = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Switch sw = (Switch) findViewById(R.id.switch1);
        ((TextView) findViewById(R.id.editText1)).setEnabled(sw.isChecked());
        ((TextView) findViewById(R.id.editText2)).setEnabled(sw.isChecked());
        ((TextView) findViewById(R.id.editText3)).setEnabled(sw.isChecked());
        ((TextView) findViewById(R.id.editText4)).setEnabled(sw.isChecked());

        if (v.getId()==R.id.button3) {
            ((MapView)findViewById(R.id.mapView)).setVisibility(View.VISIBLE);
            ((ListView)findViewById(R.id.listView)).setVisibility(View.GONE);
            Intent intent = new Intent();
            intent.putExtra("action", "GetOrgList");
            startActivityForResult(new Intent(this, MapsActivity.class),0);
        }
        if (v.getId()==R.id.button2) {
            ((MapView)findViewById(R.id.mapView)).setVisibility(View.GONE);
            ((ListView)findViewById(R.id.listView)).setVisibility(View.VISIBLE);
        }
    }
}

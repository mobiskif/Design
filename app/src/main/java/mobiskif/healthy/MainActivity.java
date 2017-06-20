package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = { "Иван", "Марья", "Петр", "Антон", "Дарья", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };
        ListView lvMain = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lvMain.setAdapter(adapter);
    }
}

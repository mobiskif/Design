package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {"Иван", "Марья", "Петр", "Антон", "Дарья", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей"};
        ListView lvMain = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        TextView tv;
        boolean en = (((Switch) findViewById(R.id.switch1)).isChecked()) ? true : false;
        tv = (TextView) findViewById(R.id.editText1);
        tv.setEnabled(en);
        tv = (TextView) findViewById(R.id.editText2);
        tv.setEnabled(en);
        tv = (TextView) findViewById(R.id.editText3);
        tv.setEnabled(en);
        tv = (TextView) findViewById(R.id.editText4);
        tv.setEnabled(en);
    }
}

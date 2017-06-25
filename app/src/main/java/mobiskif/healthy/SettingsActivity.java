package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends Activity implements View.OnClickListener {
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        controller = new Controller(this);

        Patient patient = new Patient(this);
        ((TextView)findViewById(R.id.editName)).setText(patient.getName());
        ((TextView)findViewById(R.id.editSurname)).setText(patient.getSurname());
        ((TextView)findViewById(R.id.editSecondname)).setText(patient.getSecondname());
        ((TextView)findViewById(R.id.editBirstdate)).setText(patient.getBirstdate());
    }

    @Override
    public void onClick(View v) {
        controller.onClick(v);
    }
}

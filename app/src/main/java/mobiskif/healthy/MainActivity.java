package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends Activity implements View.OnClickListener {
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patient = new Patient(this);
        prepareSpinners();
    }

    @Override
    public void onClick(View v) {
        //controller.onClick(v);
        switch (v.getId()) {
            case R.id.btnNext: startActivity(new Intent(this, TicketActivity.class)); break;
            case R.id.btnSettings: startActivity(new Intent(this, SettingsActivity.class)); break;
        }
    }

    public void prepareSpinners() {
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerDistrict) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerLPU) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerSpesiality) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerDoctor) );
    }
}

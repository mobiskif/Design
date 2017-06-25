package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends Activity implements View.OnClickListener {
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Patient patient = new Patient(this);
        //patient.setLPUid("27","Кожвен");
        //patient.setIdPat("452529");
        //patient.setDoctorID("11", "Иванов");
        controller = new Controller(this);

        ((BaseSpinnerView) findViewById(R.id.spinnerDistrict)).setAction("GetDistrictList");
        ((BaseSpinnerView) findViewById(R.id.spinnerLPU)).setAction("GetLPUList");
        ((BaseSpinnerView) findViewById(R.id.spinnerSpesiality)).setAction("GetSpesialityList");
        ((Spinner) findViewById(R.id.spinnerDistrict)).setOnItemSelectedListener(controller);
        ((Spinner) findViewById(R.id.spinnerLPU)).setOnItemSelectedListener(controller);
        ((Spinner) findViewById(R.id.spinnerSpesiality)).setOnItemSelectedListener(controller);

    }

    @Override
    public void onClick(View v) {
        controller.onClick(v);
    }
}

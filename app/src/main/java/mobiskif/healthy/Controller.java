package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Controller implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    String _ID, value, action;
    Patient patient;

    public Controller(Activity a) {
        ((Spinner) a.findViewById(R.id.spinnerDistrict)).setOnItemSelectedListener(this);
        ((Spinner) a.findViewById(R.id.spinnerLPU)).setOnItemSelectedListener(this);
        ((Spinner) a.findViewById(R.id.spinnerSpesiality)).setOnItemSelectedListener(this);
        Patient patient = new Patient(a);
        //patient.setLPUid("27","Кожвен");
        //patient.setIdPat("452529");
        //patient.setDoctorID("11", "Иванов");
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity) v.getContext();
        L.d(v.toString()+" "+v.getId());
        if (v.getId()==R.id.btnNext) {
            a.startActivity(new Intent(a, TicketActivity.class));
            //a.finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Cursor item = (Cursor) parent.getAdapter().getItem(position);
        _ID = item.getString(0);
        value = item.getString(1);
        switch (view.getId()) {
            case R.id.spinnerDistrict: patient.setDistrictID(_ID, value);
            case R.id.spinnerLPU: patient.setLPUid(_ID, value);
            case R.id.spinnerSpesiality: patient.setSpesialityID(_ID, value);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

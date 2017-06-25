package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class Controller implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    String _ID, value, action;
    Patient patient;

    public Controller(Activity a) {
        patient = new Patient(a);
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity) v.getContext();
        L.d(v.toString()+" "+v.getId());
        switch (v.getId()) {
            case R.id.btnNext: a.startActivity(new Intent(a, TicketActivity.class)); break;
            case R.id.btnSettings: a.startActivity(new Intent(a, SettingsActivity.class)); break;
            case R.id.btnSave:
                patient.setName( ((TextView)a.findViewById(R.id.editName)).getText().toString() );
                patient.setSurname( ((TextView)a.findViewById(R.id.editSurname)).getText().toString() );
                patient.setSecondname( ((TextView)a.findViewById(R.id.editSecondname)).getText().toString() );
                patient.setBirstdate( ((TextView)a.findViewById(R.id.editBirstdate)).getText().toString() );
                Intent intent = new Intent();
                intent.putExtra("action", "setPatient");
                a.setResult(RESULT_OK, intent);
                Toast.makeText(a, "Saved ...", Toast.LENGTH_SHORT).show();
                a.finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        Cursor item = (Cursor) parent.getAdapter().getItem(position);
        _ID = item.getString(0);
        value = item.getString(1);
        switch (v.getId()) {
            case R.id.spinnerDistrict: patient.setDistrictID(_ID, value);
            case R.id.spinnerLPU: patient.setLPUid(_ID, value);
            case R.id.spinnerSpesiality: patient.setSpesialityID(_ID, value);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

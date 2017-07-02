package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity implements View.OnClickListener {
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        patient = new Patient(this);
        prepareViews();
    }

    @Override
    public void onClick(View v) {
        //Activity a = (Activity) v.getContext();
        switch (v.getId()) {
            case R.id.btnSave:
                patient.setName( ((TextView)findViewById(R.id.editName)).getText().toString() );
                patient.setSurname( ((TextView)findViewById(R.id.editSurname)).getText().toString() );
                patient.setSecondname( ((TextView)findViewById(R.id.editSecondname)).getText().toString() );
                patient.setBirstdate( ((TextView)findViewById(R.id.editBirstdate)).getText().toString() );
                Intent intent = new Intent();
                intent.putExtra("action", "setPatient");
                setResult(RESULT_OK, intent);
                Toast.makeText(this, "Saved ...", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    public void prepareViews() {
        ((TextView)findViewById(R.id.editName)).setText(patient.getName());
        ((TextView)findViewById(R.id.editSurname)).setText(patient.getSurname());
        ((TextView)findViewById(R.id.editSecondname)).setText(patient.getSecondname());
        ((TextView)findViewById(R.id.editBirstdate)).setText(patient.getBirstdate());
    }
}

package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //try {sleep(1500);} catch (InterruptedException e) {}
        if ( !isConfigured() ) startActivityForResult(new Intent(this, SettingsActivity.class),0);
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean isConfigured() {
        Patient patient = new Patient(this);
        if (patient.getBirstdate().length()>3) return true;
        else return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}

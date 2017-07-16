package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends Activity implements View.OnClickListener {
    private Patient patient;
    private GoogleSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patient = new Patient(this);
        prepareSpinners();

        tts = new GoogleSpeech(this, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext: startActivity(new Intent(this, TicketActivity.class)); break;
            case R.id.btnSettings: startActivity(new Intent(this, SettingsActivity.class)); break;
            case R.id.btnVoice: GoogleVoice.listen(this); break;
            case R.id.btnTTS: tts.speak("Пошла муха на базар", TextToSpeech.QUEUE_ADD, null, "1"); break;
        }
    }

    public void prepareSpinners() {
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerDistrict) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerLPU) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerSpesiality) );
        patient.prepareSpinner( (Spinner)findViewById(R.id.spinnerDoctor) );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==666) onVoice(data.getExtras().getString("query"));
    }

    public void onVoice(String command) {
        String action = null;
        String value = null;
        L.d("Фраза = " + command);
        if (command.contains("настройки")) {
            action = "Settings";
        } else if (command.contains("район")) {
            action = "GetDistrictList";
            String[] words = command.split(" ");
            for (String word : words) if (!word.contains("район")) value = word;
        } else if (command.contains("поликлиника") || command.contains("больница")) {
            action = "GetLPUList";
            String[] words = command.split(" ");
            for (String word : words) if (!word.contains("поликлиника") && !word.contains("больница") ) value = word;
        } else if (command.contains("специальность")) {
            action = "GetSpesialityList";
            String[] words = command.split(" ");
            for (String word : words) if (!word.contains("специальность")) value = word;
        } else if (command.contains("доктор")) {
            action = "GetDoctorList";
            String[] words = command.split(" ");
            for (String word : words) if (!word.contains("доктор")) value = word;
        } else if (command.contains("талончик")) {
            action = "GetAvaibleAppointments";
            String[] words = command.split(" ");
            for (String word : words) if (!word.contains("талончик")) value = word;
        }
        L.d("Команда = " + action + " " + value);
        //doAction(action, value);
    }


}

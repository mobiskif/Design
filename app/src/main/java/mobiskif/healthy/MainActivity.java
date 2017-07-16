package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Spinner;

import java.io.FileDescriptor;
import java.io.FileInputStream;

public class MainActivity extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {
    private Patient patient;
    TextToSpeech speechEngine;
    String speechString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patient = new Patient(this);
        prepareSpinners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext: startActivity(new Intent(this, TicketActivity.class)); break;
            case R.id.btnSettings: startActivity(new Intent(this, SettingsActivity.class)); break;
            case R.id.btnVoice: GoogleSpeech.listen(this); break;
            case R.id.btnTTS: selectFile("*/*"); break;
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
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==666) {
            L.d(data.getExtras().getString("query"),this);
        }
        else if (requestCode == 7 && data != null) {
            //Uri uri = data.getData();
            try {
                FileDescriptor fd = this
                        .getContentResolver()
                        .openFileDescriptor(data.getData(), "r")
                        .getFileDescriptor();
                FileInputStream fis = new FileInputStream(fd);
                StringBuffer fileContent = new StringBuffer("");
                byte[] buffer = new byte[1024];
                int n;
                while ((n = fis.read(buffer)) != -1) fileContent.append(new String(buffer, 0, n));
                fis.close();

                speechString = fileContent.toString();
                L.d(speechString, this);
                Intent checkIntent = new Intent();
                checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivity(checkIntent);

                if (TextToSpeech.Engine.CHECK_VOICE_DATA_PASS ==1 ) {

                    speechEngine = new TextToSpeech(this, this);
                } else {
                    Intent installIntent = new Intent();
                    installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
                }
            }
            catch (Exception e) {
                L.d("Не могу говорить: "+e.toString(),this);
            }
        }
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

    @Override
    public void onInit(int status) {
        speechEngine.speak(speechString, TextToSpeech.QUEUE_ADD, null, "1");
    }

    private void selectFile(String type) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(type);
        startActivityForResult(intent, 7);
    }
}

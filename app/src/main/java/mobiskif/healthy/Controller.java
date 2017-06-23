package mobiskif.healthy;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Controller implements View.OnClickListener {
    Activity activity;

    public Controller(Activity a) {
        activity = a;
    }

    public void onTouch(int vid) {
        String action=null;
        L.d(vid,this);
        switch (vid) {
            case R.id.button3 :
                action = "GetDistrictList";
                BaseListView baseListView = (BaseListView) activity.findViewById(R.id.myList);
                baseListView.exec(activity,action);
                break;
            //case R.id.viewLPU : action = "GetLPUList"; break;
            //case R.id.viewSpesiality : action = "GetSpesialityList"; break;
            //case R.id.viewDoctor : action = "GetDoctorList"; break;
            //case R.id.viewAppointments : action = "GetAvaibleAppointments"; break;
            //case R.id.imageView : action = "GetOrgList"; break;
            //case R.id.imageView : activity.startActivityForResult(new Intent(activity, ChatActivity.class), 0); break;
        }
        //if (action!=null) doAction(action, null);
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
        doAction(action, value);
    }

    public void onResult(Intent data) {
        String action = data.getStringExtra("action");
        String value1 = data.getStringExtra("value1");
        String value2 = data.getStringExtra("value2");
        String value3 = data.getStringExtra("value3");
        String _ID = data.getStringExtra("_ID");
        L.d("onResult() "+action,this);

        Patient patient = new Patient(activity);
        if (action!=null) {
            switch (action) {
                case "GetDistrictList":
                    patient.setDistrictID(_ID, value1);
                    break;
                case "GetLPUList":
                    patient.setLPUid(_ID, value1);
                    doAction("CheckPatient", "just_no_tnull");
                    break;
                case "GetSpesialityList":
                    patient.setSpesialityID(_ID, value1);
                    break;
                case "GetDoctorList":
                    patient.setDoctorID(_ID, value1);
                    break;
                case "GetAvaibleAppointments":
                    doAction("SetAppointment", value3);
                    break;
                case "CheckPatient":
                    patient.setIdPat(_ID);
                    break;
                case "GetOrgList":
                    patient.setLPUid(_ID, value1);
                    doAction("CheckPatient", "just_no_tnull");
                    break;
                case "Settings":
                    patient.setIdPat("");
                    break;
                case "SetAppointment":
                    patient.setAppointment(_ID, value1 + "\n" + value2);
                    break;
            }
            //((MainActivity)activity).speechString = value1;
            Intent checkIntent = new Intent();
            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            ((MainActivity) activity).startActivity(checkIntent);

            if (TextToSpeech.Engine.CHECK_VOICE_DATA_PASS ==1 ) {
                //((MainActivity) activity).speechEngine = new TextToSpeech((MainActivity) activity, (MainActivity) activity);
            } else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                ((MainActivity) activity).startActivity(installIntent);
            }
            //((MainActivity) activity).refreshViews();
        }
    }

    public void doAction(String action, String v) {
        Class clas=null;
        switch (action) {
            case "GetOrgList": clas = MapsActivity.class; break;
            //case "Settings": clas = SettingsActivity.class; break;
            //case "SetAppointment": clas = ConfirmActivity.class; break;
            default: clas = MapsActivity.class;
        }
        Intent intent = new Intent(activity, clas);
        intent.putExtra("value", v);
        intent.putExtra("action", action);
        activity.startActivityForResult(intent, 0);
    }

    @Override
    public void onClick(View v) {
        Activity a = (Activity)v.getContext();
        Switch sw = (Switch) a.findViewById(R.id.switch1);
        ((TextView) a.findViewById(R.id.editText1)).setEnabled(sw.isChecked());
        ((TextView) a.findViewById(R.id.editText2)).setEnabled(sw.isChecked());
        ((TextView) a.findViewById(R.id.editText3)).setEnabled(sw.isChecked());
        ((TextView) a.findViewById(R.id.editText4)).setEnabled(sw.isChecked());
        Patient patient = new Patient(a);

        /*
        if (v.getId()==R.id.button3) {
            ((MapView)a.findViewById(R.id.mapView)).setVisibility(View.VISIBLE);
            ((ListView)a.findViewById(R.id.listView)).setVisibility(View.GONE);
            Intent intent = new Intent();
            intent.putExtra("action", "GetOrgList");
            a.startActivityForResult(new Intent(a, MapsActivity.class),0);
        }
        if (v.getId()==R.id.button2) {
            ((MapView)a.findViewById(R.id.mapView)).setVisibility(View.GONE);
            ((ListView)a.findViewById(R.id.listView)).setVisibility(View.VISIBLE);
        }
        */
        onTouch(v.getId());
    }
}

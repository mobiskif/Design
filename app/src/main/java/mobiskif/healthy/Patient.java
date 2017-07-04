package mobiskif.healthy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class Patient implements AdapterView.OnItemSelectedListener {
    SharedPreferences settings;
    Activity activity;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        L.d(parent.getId()+" "+position + " "+ view ,this);
        MatrixCursor item = (MatrixCursor) parent.getAdapter().getItem(position);

        switch (parent.getId()) {
            case R.id.spinnerDistrict:
                setVal("GetDistrictList_SpinnerPosition", position);
                setVal("GetDistrictList_ID", item.getInt(0));
                //setVal("GetLPUList_SpinnerPosition", 0);
                prepareSpinner((Spinner)activity.findViewById(R.id.spinnerLPU));
                break;
            case R.id.spinnerLPU:
                setVal("GetLPUList_SpinnerPosition", position);
                setVal("GetLPUList_ID", item.getInt(0));
                //setVal("GetSpesialityList_SpinnerPosition", 0);
                checkPatient();
                prepareSpinner((Spinner)activity.findViewById(R.id.spinnerSpesiality));
                break;
            case R.id.spinnerSpesiality:
                setVal("GetSpesialityList_SpinnerPosition", position);
                setVal("GetSpesialityList_ID", item.getInt(0));
                prepareSpinner((Spinner)activity.findViewById(R.id.spinnerDoctor));
                break;
            case R.id.spinnerDoctor:
                setVal("GetDoctorList_SpinnerPosition", position);
                setVal("GetDoctorList_ID", item.getString(0));
                prepareSpinner((Spinner)activity.findViewById(R.id.spinnerDoctor));
                break;
        }
    }

    private void checkPatient() {
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                return new ActionAdapter(activity, "CheckPatient");
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Adapter adapter = (Adapter) o;
                MatrixCursor mc = (MatrixCursor) adapter.getItem(0);
                setVal("idPat", mc.getInt(0));
            }
        };
        at.execute(activity);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void prepareSpinner(final Spinner ds) {
        ds.setOnItemSelectedListener(this);
        String action = null;
        switch (ds.getId()) {
            case R.id.spinnerDistrict: action="GetDistrictList"; break;
            case R.id.spinnerLPU: action="GetLPUList"; break;
            case R.id.spinnerSpesiality: action="GetSpesialityList"; break;
            case R.id.spinnerDoctor: action="GetDoctorList"; break;
        }

        final String finalAction = action;
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                return new ActionAdapter(activity, finalAction);
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                SpinnerAdapter adapter = (SpinnerAdapter) o;
                ds.setAdapter(adapter);
                int position = getVal(finalAction+"_SpinnerPosition");
                if (position<adapter.getCount()) ds.setSelection(position);
            }
        };
        at.execute(activity);
       // ds.setPrompt("Jopa");
    }

    public int getVal(String key) {
        SharedPreferences settings = activity.getSharedPreferences("n3", 0);
        return Integer.valueOf(settings.getString(key,"0"));
    }

    public void setVal(String key, int val) {
        SharedPreferences settings = activity.getSharedPreferences("n3", 0);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString(key, String.valueOf(val));
        ed.apply();
    }
    public void setVal(String key, String val) {
        SharedPreferences settings = activity.getSharedPreferences("n3", 0);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString(key, val);
        ed.apply();
    }

    public Patient(Activity a) {
        activity = a;
        settings = activity.getSharedPreferences("n3", 0);
    }

    public void setName(String s) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("Name", s);
        ed.putString("idDistrict", null);
        ed.putString("District_Value", null);
        ed.putString("idPat", null);
        ed.putString("LPU_Value", null);
        ed.putString("idLPU", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idSpesiality", null);
        ed.putString("Doctor_Value", null);
        ed.putString("idDoc", null);
        ed.apply();
    }

    public void setSurname(String s) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("Surname", s);
        ed.putString("idDistrict", null);
        ed.putString("District_Value", null);
        ed.putString("idPat", null);
        ed.putString("LPU_Value", null);
        ed.putString("idLPU", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idSpesiality", null);
        ed.putString("Doctor_Value", null);
        ed.putString("idDoc", null);
        ed.apply();
    }

    public void setSecondname(String s) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("Secondname", s);
        ed.putString("idDistrict", null);
        ed.putString("District_Value", null);
        ed.putString("idPat", null);
        ed.putString("LPU_Value", null);
        ed.putString("idLPU", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idSpesiality", null);
        ed.putString("Doctor_Value", null);
        ed.putString("idDoc", null);
        ed.apply();
    }

    public void setBirstdate(String s) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("Birstdate", s);
        ed.putString("idDistrict", null);
        ed.putString("District_Value", null);
        ed.putString("idPat", null);
        ed.putString("LPU_Value", null);
        ed.putString("idLPU", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idSpesiality", null);
        ed.putString("Doctor_Value", null);
        ed.putString("idDoc", null);
        ed.apply();
    }

    public void setIdPat(String s) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idPat", s);
        ed.apply();
    }

    public void setDistrictID(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idDistrict", s);
        ed.putString("District_Value", value);
        ed.putString("idLPU", null);
        ed.putString("LPU_Value", null);
        ed.putString("idSpesiality", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idDoc", null);
        ed.putString("Doctor_Value", null);
        ed.apply();
    }

    public void setLPUid(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idLPU", s);
        ed.putString("LPU_Value", value);
        ed.putString("idSpesiality", null);
        ed.putString("Spesiality_Value", null);
        ed.putString("idDoc", null);
        ed.putString("Doctor_Value", null);
        ed.apply();
    }

    public void setSpesialityID(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idSpesiality", s);
        ed.putString("Spesiality_Value", value);
        ed.putString("idDoc", null);
        ed.putString("Doctor_Value", null);
        ed.apply();
    }

    public void setDoctorID(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idDoc", s);
        ed.putString("Doctor_Value", value);
        ed.apply();
    }

    public String getName() {
        return settings.getString("Name","");
    }

    public String getSurname() {
        return settings.getString("Surname","");
    }

    public String getSecondname() {
        return settings.getString("Secondname","");
    }

    public String getBirstdate() {
        return settings.getString("Birstdate","");
    }

    public String getIdPat() { //меняется автоматически (в каждой поликлинике свой)
        return settings.getString("idPat","");
    }

    public String getDistrictID() { //меняется автоматически (в каждой поликлинике свой)
        //return settings.getString("idDistrict","");
        return settings.getString("GetDistrictList_ID","");
    }

    public String getLPUid() { //меняется автоматически (в каждой поликлинике свой)
        //return settings.getString("idLPU","");
        return settings.getString("GetLPUList_ID","");
    }

    public String getSpesialityID() { //меняется автоматически (в каждой поликлинике свой)
        //return settings.getString("idSpesiality","");
        return settings.getString("GetSpesialityList_ID","");
    }

    public String getDoctorID() { //меняется автоматически (в каждой поликлинике свой)
        //return settings.getString("idDoc","");
        return settings.getString("GetDoctorList_ID","");
    }

    public void setAppointment(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idAppointment", s);
        ed.putString("Appointment_Value", value);
        ed.apply();
    }
}

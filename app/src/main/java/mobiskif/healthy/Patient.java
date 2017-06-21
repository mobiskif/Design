package mobiskif.healthy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mobis on 10.06.2017.
 */

public class Patient {
    SharedPreferences settings;
    Context context;

    public Patient(Context c) {
        context = c;
        settings = context.getSharedPreferences("n3", 0);
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
        return settings.getString("idDistrict","");
    }

    public String getLPUid() { //меняется автоматически (в каждой поликлинике свой)
        return settings.getString("idLPU","");
    }

    public String getSpesialityID() { //меняется автоматически (в каждой поликлинике свой)
        return settings.getString("idSpesiality","");
    }

    public String getDoctorID() { //меняется автоматически (в каждой поликлинике свой)
        return settings.getString("idDoc","");
    }

    public Context getContext() {
        return context;
    }

    public void setAppointment(String s, String value) {
        SharedPreferences.Editor ed = settings.edit();
        ed.putString("idAppointment", s);
        ed.putString("Appointment_Value", value);
        ed.apply();
    }
}

package mobiskif.healthy;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TicketActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        setAction("GetAvaibleAppointments");
    }

    @Override
    public void onClick(View v) {

    }

    public void setAction(final String action) {
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                return new ActionAdapter((Activity) o[0], action);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                ((ListView)findViewById(R.id.timeList)).setAdapter((ListAdapter) o);
            }
        };
        at.execute(this);
    }
}

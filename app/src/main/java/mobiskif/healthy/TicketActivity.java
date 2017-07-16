package mobiskif.healthy;

import android.app.Activity;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TicketActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        setAction("GetAvaibleAppointments");
        ((ListView)findViewById(R.id.timeList)).setOnItemClickListener(this);
    }

    public void setAction(final String action) {
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                return new ActionAdapter((Activity) o[0], action);
            }

            @Override
            protected void onPostExecute(Object o) {
                //super.onPostExecute(o);
                ((ListView)findViewById(R.id.timeList)).setAdapter((ListAdapter) o);
            }
        };
        at.execute(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MatrixCursor mc = (MatrixCursor) ((ListView) adapterView).getAdapter().getItem(i);
        //L.d(mc.getString(1)+" "+mc.getString(2),this );
    }
}

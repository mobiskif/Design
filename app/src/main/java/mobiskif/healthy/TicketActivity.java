package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;

public class TicketActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        ((BaseListView) findViewById(R.id.timeList)).setAction("GetAvaibleAppointments");
    }
}

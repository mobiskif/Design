package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TicketActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        //((ListView) findViewById(R.id.timeList)).setAction("GetAvaibleAppointments");
    }

    @Override
    public void onClick(View v) {

    }
}

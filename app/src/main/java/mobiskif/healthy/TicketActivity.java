package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TicketActivity extends Activity implements View.OnClickListener {
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        ((BaseListView) findViewById(R.id.timeList)).setAction("GetAvaibleAppointments");
        controller = new Controller(this);
    }

    @Override
    public void onClick(View v) {
        controller.onClick(v);
    }
}

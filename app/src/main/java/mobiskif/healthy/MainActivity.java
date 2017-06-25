package mobiskif.healthy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BaseSpinnerView) findViewById(R.id.spinnerDistrict)).setAction("GetDistrictList");
        ((BaseSpinnerView) findViewById(R.id.spinnerLPU)).setAction("GetLPUList");
        ((BaseSpinnerView) findViewById(R.id.spinnerSpesiality)).setAction("GetSpesialityList");
        controller = new Controller(this);
    }

    @Override
    public void onClick(View v) {
        controller.onClick(v);
    }
}

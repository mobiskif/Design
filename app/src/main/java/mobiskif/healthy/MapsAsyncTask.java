package mobiskif.healthy;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

class MapsAsyncTask extends AsyncTask<Activity, Integer, View> {//Params,Progress,Result
    DataAdapter adapter;
    Activity activity;

    @Override
    protected View doInBackground(Activity... params) {
        activity = params[0];
        Uri uri = Uri.parse("content://com.n3/" + activity.getIntent().getStringExtra("action"));
        Cursor cursor = new DataProviderSOAP(activity).query(uri, null, null, null, null);
        adapter = new DataAdapter(activity, cursor);
        cursor.close();
        return (ListView) activity.findViewById(R.id.listView);
    }

    @Override
    protected void onPostExecute(View result) {
        super.onPostExecute(result);
        ((ListView)result).setAdapter(adapter);
        ((MapsActivity)activity).prepareMap(adapter);
    }


}

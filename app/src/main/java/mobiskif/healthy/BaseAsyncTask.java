package mobiskif.healthy;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ListView;

/**
 * Created by mobis on 22.06.2017.
 */

public class BaseAsyncTask extends AsyncTask {
    ListView listView;
    String action;

    public BaseAsyncTask(ListView lv) {
        listView = lv;
    }

    public BaseAsyncTask(ListView lv, String a) {
        listView = lv;
        action = a;
    }

    @Override
    protected Object doInBackground(Object... o) {
        Uri uri = Uri.parse("content://com.n3/" + action);
        Cursor cursor = new DataProviderSOAP((Activity) o[0]).query(uri, null, null, null, null);
        DataAdapter adapter = new DataAdapter((Activity) o[0], cursor);
        cursor.close();
        return adapter;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        listView.setAdapter((DataAdapter)o);
    }
}

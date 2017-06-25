package mobiskif.healthy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by mobis on 22.06.2017.
 */

public class BaseSpinnerView extends Spinner {
    String action;
    Context context;

    public BaseSpinnerView(Context context) {
        super(context);
        this.context=context;
    }

    public BaseSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public BaseSpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void setAction(String s) {
        action = s;
        refresh();
    }

    public void refresh() {
        AsyncTask at = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] o) {
                Uri uri = Uri.parse("content://com.n3/" + action);
                Cursor cursor = new DataProviderSOAP((Activity) o[0]).query(uri, null, null, null, null);
                DataAdapter adapter = new DataAdapter((Activity) o[0], cursor);
                cursor.close();
                return adapter;
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                setAdapter((DataAdapter)o);
            }
        };
        at.execute(context);
    }

}

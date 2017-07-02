package mobiskif.healthy;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by mobis on 22.06.2017.
 */

public class BaseListView extends ListView {
    Context context;

    public BaseListView(Context context) {
        super(context);
        this.context=context;
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
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
                setAdapter((ListAdapter)o);
            }
        };
        at.execute(context);
    }
}

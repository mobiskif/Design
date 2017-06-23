package mobiskif.healthy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by mobis on 22.06.2017.
 */

public class BaseListView extends ListView {

    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        new BaseAsyncTask(this).execute(context);
    }

    public void exec(Context context, String action) {
        new BaseAsyncTask(this, action).execute(context);
    }

}

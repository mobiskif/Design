package mobiskif.healthy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ActionAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Cursor cursor;

    public ActionAdapter(Activity activity, String action) {
        Uri uri = Uri.parse("content://com.n3/" + action);
        cursor = new CursorSOAP(activity).query(uri, null, null, null, null);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        cursor.moveToPosition(position);
        return cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup group) {
        cursor.moveToPosition(position);

        View view = (convertView==null) ? inflater.inflate(R.layout.cursor_items, group, false) : convertView;

        ((TextView) view.findViewById(R.id.textView1)).setText(cursor.getString(0));
        ((TextView) view.findViewById(R.id.textView2)).setText(cursor.getString(1));
        ((TextView) view.findViewById(R.id.textView3)).setText(cursor.getString(2));
        //L.d(cursor.getString(0)+ " "+cursor.getString(1)+" "+cursor.getString(2), this);

        return view;
    }
}

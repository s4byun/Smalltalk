package home.smalltalk.utils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import home.smalltalk.R;

public class ListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ListAdapter(Context context, String[] values) {
        super(context, R.layout.listrow, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listrow, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        textView.setText(values[position]);

        return rowView;
    }
}
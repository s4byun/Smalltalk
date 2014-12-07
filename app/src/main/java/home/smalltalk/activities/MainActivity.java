package home.smalltalk.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import home.smalltalk.R;
import home.smalltalk.utils.ExtendedNumberPicker;
import home.smalltalk.utils.ListAdapter;

public class MainActivity extends Activity {


    public ImageView image;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionbar = getActionBar();
        actionbar.hide();

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                          Custom search bar                              */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        editText = (EditText) findViewById(R.id.search);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String selection = v.getText().toString();
                    search(selection);
                    return true;
                }

                return false;
            }
        });

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                          Search Button                                  */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        final LinearLayout button = (LinearLayout) findViewById(R.id.buttonPanel);
        image = (ImageView) findViewById(R.id.goButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = editText.getText().toString();
                if(selection.length() == 0) {
                    search("nothing");
                } else {
                    search(selection);
                }
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    image.setImageResource(R.drawable.clickedbutton);
                }
                else {
                    image.setImageResource(R.drawable.gobutton);
                }
                return false;
            }
        });

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                              List View                                  */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        final ListView listView = (ListView) findViewById(R.id.listview);
        final String[] values = new String[]{"U.S.", "World", "Entertainment",
                "Science", "Travel", "Music", "Health", "Politics", "Economics",
                "Colleges", "Bravo"};
        final ListAdapter adapter = new ListAdapter(this, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                search(item);
            }

        });


    }
// End of onCreate()


    public void search(String selection) {
        if(selection.length() == 0) {
            selection = "a";
        }
        final Intent i = new Intent(MainActivity.this, MyActivity.class);
        i.putExtra("id", selection);
        startActivity(i);
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
    }
}

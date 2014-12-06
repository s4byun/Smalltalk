package home.smalltalk.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Field;

import home.smalltalk.R;
import home.smalltalk.utils.ExtendedNumberPicker;

public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener {
    String selection = "U.S.";
    String[] values = {"U.S.", "World", "Entertainment", "Science", "Travel",
    "Music", "Health", "Politics", "Economics", "Colleges", "Bravo"};

    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ActionBar actionbar = getActionBar();
        actionbar.hide();

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                          Custom search bar                              */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        EditText editText = (EditText) findViewById(R.id.search);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    selection = v.getText().toString();
                    search();
                    return true;
                }

                return false;
            }
        });


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                          Number Picker                                  */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        NumberPicker np = (ExtendedNumberPicker) findViewById(R.id.numberpicker);

        np.setMaxValue(values.length - 1);
        np.setMinValue(0);
        np.setDisplayedValues(values);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        set_numberpicker_text_colour(np);

        np.setOnValueChangedListener(this);


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*                          Search Button                                  */
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        final LinearLayout button = (LinearLayout) findViewById(R.id.buttonPanel);
        image = (ImageView) findViewById(R.id.goButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
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
    }
// End of onCreate()



    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        selection = values[newVal];
        search();
    }

    public void search() {
        final Intent i = new Intent(MainActivity.this, MyActivity.class);
        i.putExtra("id", selection);
        startActivity(i);
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
    }


    private void set_numberpicker_text_colour(NumberPicker number_picker){
        final int count = number_picker.getChildCount();
        final int color = getResources().getColor(R.color.primary);

        for(int i = 0; i < count; i++){
            View child = number_picker.getChildAt(i);

            try{
                Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelpaint_field.setAccessible(true);

                ((Paint)wheelpaint_field.get(number_picker)).setColor(color);
                ((EditText)child).setTextColor(color);
                number_picker.invalidate();
            }
            catch(NoSuchFieldException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalAccessException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalArgumentException e){
                Log.w("setNumberPickerTextColor", e);
            }
        }
    }
}

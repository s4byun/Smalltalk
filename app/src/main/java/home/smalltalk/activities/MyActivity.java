package home.smalltalk.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import home.smalltalk.R;
import home.smalltalk.adapters.CoursesAdapter;
import home.smalltalk.models.Course;
import home.smalltalk.utils.ReadLocalJSON;


public class MyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        String s = getIntent().getExtras().getString("id");
        final ArrayList<Course> courses;
        ReadLocalJSON readLocalJSON = new ReadLocalJSON();
        readLocalJSON.setS(s);

        courses = readLocalJSON.getCourses(this);

        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CoursesAdapter(courses, R.layout.row, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}

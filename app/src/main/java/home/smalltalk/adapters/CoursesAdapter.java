package home.smalltalk.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import home.smalltalk.R;
import home.smalltalk.models.Course;
import home.smalltalk.activities.WebViewActivity;

public class CoursesAdapter  extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private static ArrayList<Course> courses;
    private int itemLayout;
    private static Activity thisActivity;
    public  CoursesAdapter(ArrayList<Course> data,  int itemLayout, Activity c){
        courses = data;
        this.itemLayout = itemLayout;
        thisActivity = c;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView image;
        public TextView name;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            image = (TextView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        @Override
        public void onClick(View view) {
            String s = Integer.toString(Integer.parseInt((String) image.getText()) + 1);
            image.setText(s);
            Intent intent = new Intent(thisActivity,WebViewActivity.class);
            intent.putExtra("URL", courses.get(getPosition()).getUrl());
            intent.putExtra("TITLE", courses.get(getPosition()).getName());
            thisActivity.startActivity(intent);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup  parent, int i) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Course course = courses.get(position);
        viewHolder.image.setText(course.getId());
        viewHolder.name.setText(course.getName());
        viewHolder.description.setText(course.getDescription());
        viewHolder.itemView.setTag(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

}

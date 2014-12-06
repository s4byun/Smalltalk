package home.smalltalk.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import home.smalltalk.models.Course;

public class ReadLocalJSON {
    private ArrayList<Course> courses = new ArrayList<Course>();
    private String s;
    String[] titles, urls, descriptions;

    public ReadLocalJSON() {}

    public void setS(String s) {
        this.s = s;
    }

    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            // Load Articles //
            String[] strings = new String[4]; // title, source, url, description
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://104.131.73.123/bing_basic.php");
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                        1);
                nameValuePairs.add(new BasicNameValuePair("query", s));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));

                for (int l = 0; l < 4; l++) {
                    strings[l] = rd.readLine();
                }

                titles = strings[0].split("\t");
                urls = strings[2].split("\t");
                descriptions = strings[3].split("\t");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
    public ArrayList<Course> getCourses(Context c){
        AsyncTask<Void, Void, Void> task = new PrefetchData().execute();

        try {
            task.get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] array = new int[titles.length];

        for (int i=0; i<titles.length; i++) {
            Random rn = new Random();
            int n = rn.nextInt(100);

            array[i] = n;
        }

        java.util.Arrays.sort(array);

        for (int index = 0; index < titles.length; index++) {
            Course course = new Course();

            course.setId(String.valueOf(array[titles.length - index - 1]));
            course.setName(titles[index]);
            course.setDescription(descriptions[index]);
            course.setUrl(urls[index]);
            course.setIndex(index);
            courses.add(course);
        }

        return courses;
    }
}

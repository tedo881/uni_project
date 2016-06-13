package app.uni.my.myapplication;

/**
 * Created by Ted on 30.12.2015.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class GetSubjects extends AsyncTask<String, Void, String> {
public String group;

    private Context context;
    public static String id;
    public String examResponse;
    public GetSubjects(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        group = arg0[0];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            link  = "http://192.168.56.1/getsubjects.php";
          link  += "?group=" + URLEncoder.encode(group, "UTF-8");

       //   link = "http://localhost/conn.php";
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
          //  result = bufferedReader.readLine();
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }

            result=sb.toString();





            ////////////
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }


    @Override
    protected void onPostExecute(String result) {
      //  Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        String jsonStr = result;

        if (jsonStr != null) {
            try {


                        JSONObject   jsonObj = new JSONObject(jsonStr);

                        JSONArray  jsonArray = jsonObj.optJSONArray("subject");

                        ArrayList<Exam> ExamList = new ArrayList<Exam>();
                        DBHandlerSubjects db = new DBHandlerSubjects(context);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            Subject subject = new Subject();
                            subject.setId(c.optString("id").toString());
                            subject.setGroup(c.optString("group").toString());
                            subject.setProfessor(c.optString("professor").toString());
                            subject.setDay(c.optString("day").toString());
                            subject.setAudience(c.optString("audience").toString());
                            subject.setType(c.optString("type").toString());
                            subject.setBegin(c.optString("begin").toString());
                            subject.setEnd(c.optString("end").toString());
                            subject.setName(c.optString("name").toString());
                            db.addsubject(subject,i);

                        }





                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                    }




        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }











    }



}
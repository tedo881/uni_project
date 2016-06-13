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
public class LogIn extends AsyncTask<String, Void, String> {
public String userName;
    public  String passWord;
    private Context context;
    public static String id;
    public String examResponse;
    public  String result;
    public LogIn(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        userName = arg0[0];
         passWord = arg0[1];


        String link;
        String data;
        BufferedReader bufferedReader;


        try {
            link  = "http://192.168.56.1/mycon.php";
          link  += "?username=" + URLEncoder.encode(userName, "UTF-8");
         link += "&password=" + URLEncoder.encode(passWord, "UTF-8");
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result=sb.toString();

            /////////////


            Response response;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://exams.sangu.ge/examschedule.api?user_id=" + userName)
                    .post(null)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "4a5f4fc6-8d18-d075-f923-a57f930b6e82")
                    .build();

            response = client.newCall(request).execute();
           examResponse= response.body().string();




            ////////////
            return result;
        } catch (Exception e) {
            return result;
        }
    }


    @Override
    protected void onPostExecute(String result) {

        String jsonStr = result;

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("login");
                if (query_result.equals("yes")) {


                    JSONArray jsonArray = jsonObj.optJSONArray("student");

                    JSONObject jsonObject = jsonArray.getJSONObject(0);


                    Student student= new Student();

                    student.setId(jsonObject.optString("user_id").toString());
                    student.setName(jsonObject.optString("name").toString());
                    student.setSurname(jsonObject.optString("surname").toString());
                    student.setAddress(jsonObject.optString("address").toString());
                    student.setBirth(jsonObject.optString("birth").toString());
                    student.setFakulteti(jsonObject.optString("fakulteti").toString());
                    student.setPhone(jsonObject.optString("phone").toString());
                    student.setSqesi(jsonObject.optString("sqesi").toString());
                    student.setEmail(jsonObject.optString("email").toString());
                    student.setPhoto(jsonObject.optString("photo").toString());
                    student.setSemester(jsonObject.optString("semester").toString());
                    student.setStatus(jsonObject.optString("status").toString());
                    student.setJgupi(jsonObject.optString("jgufi").toString());
                    id=jsonObject.optString("user_id").toString();

                    new GetSubjects(context).execute(jsonObject.optString("jgufi").toString());
                    new GetScores(context).execute(jsonObject.optString("jgufi").toString());

                    DBHandler db = new DBHandler(context);
                    db.addContact(student);
                    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("id", userName);
                    editor.putString("password", passWord);
                   editor.putBoolean("IsLogIn", true );

                   editor.commit();

                   // new GetExams(context,jsonObject.optString("user_id")).execute();



                    jsonStr = "{\"Exams\":" + examResponse + "}";

                    try {
                         jsonObj = new JSONObject(jsonStr);

                         jsonArray = jsonObj.optJSONArray("Exams");

                        ArrayList<Exam> ExamList = new ArrayList<Exam>();
                        db = new DBHandler(context);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            Exam exam = new Exam();
                            exam.setId(c.optString("id"));
                            exam.setFaculty(c.optString("faculty"));
                            exam.setSubject(c.optString("subject"));
                            exam.setDay(c.optString("day"));
                            exam.setSession_time(c.optString("session_time"));
                            exam.setHall(c.optString("hall"));
                            exam.setSeat(c.optString("seat"));
                            // ExamList.add(exam);
                            db.addexam(exam, i);
                            // Toast.makeText(context, "chaicera", Toast.LENGTH_SHORT).show();
                        }


                        //  for (int i=0; i<ExamList.size();i++){
                        //     db.addexam(ExamList.get(i),i);
                        //   }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                    }






                  context.startActivity(new Intent(context, HeaderActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

                } else if (query_result.equals("no")) {
                    Toast.makeText(context, "გთხოვთ სწორად შეიყვანოთ სახელი და პაროლი", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(context, "ვერ მოხდა ბაზასთან დაკავშირება", Toast.LENGTH_SHORT).show();
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
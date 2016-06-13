package app.uni.my.myapplication;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment {
    public static  DBHandler db;
    public static ArrayList<Exam> exams;

    public ExamFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_exam,
                container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.myLiayout);
        db = new DBHandler(getContext());
        exams = db.getExams();
        //   Toast.makeText(getContext(), exams.get(0).getFaculty(), Toast.LENGTH_SHORT).show();

        int s=exams.size();
       // Toast.makeText(getContext(),"sigrzea"+Integer.toString(s), Toast.LENGTH_SHORT).show();

        for(int j=0; j<exams.size(); j++){
            TextView myTextView = new TextView(getContext());
            myTextView.setText(Html.fromHtml("<b>საგანი : </b>" + exams.get(j).getSubject() + "<br>" + "<b>დღე : </b>" + exams.get(j).getDay() + "<br>"  + "<b>სეანსი : </b>" + exams.get(j).getSession_time() + "<br>"  + "<b>ადგილი : </b>" + exams.get(j).getSeat() + "<br>"  + "<b>დარბაზი : </b>" + exams.get(j).getHall()));

            myTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            myTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
           // myTextView.setBackgroundColor(Color.parseColor("#d9d9d9"));
            myTextView.setTextColor(Color.WHITE);
            myTextView.setBackgroundResource(R.drawable.custom_btn_orange2);
           // myTextView.setBackgroundResource(R.drawable.custom_border3);
          //  myTextView.setTextColor(Color.BLACK);
            TextView myTextViewspace = new TextView(getContext());
            myTextViewspace.setText("");
            myTextViewspace.setTextSize(8);
            myTextViewspace.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayout.addView(myTextViewspace);
            linearLayout.addView(myTextView);
        }

        return view;
    }


}

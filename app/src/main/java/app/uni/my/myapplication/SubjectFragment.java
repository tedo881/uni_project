package app.uni.my.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {
    public static ArrayList<Subject> subjects;
    public DBHandlerSubjects dvSubjects;
    public LinearLayout linearLayout;

    public SubjectFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        String[] week = {"ორშაბათი", "სამშაბათი", "ოთხშაბათი", "ხუთშაბათი", "პარასკევი", "შაბათი"};
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, week);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setAdapter(stringArrayAdapter);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear);
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-2;
        if(dayOfWeek==6) dayOfWeek=0;
      spinner.setSelection(dayOfWeek);

        dvSubjects = new DBHandlerSubjects(getContext());

        //  subjects = dvSubjects.getSubjects("0");

        // Toast.makeText(getContext(), Integer.toString(subjects.size()), Toast.LENGTH_SHORT).show();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:

                        subjects = dvSubjects.getSubjects("0");
                        setSubjects(subjects);

                        break;
                    case 1:
                        subjects = dvSubjects.getSubjects("1");
                        setSubjects(subjects);
                        break;
                    case 2:

                        subjects = dvSubjects.getSubjects("2");
                        setSubjects(subjects);
                        break;
                    case 3:

                        subjects = dvSubjects.getSubjects("3");
                        setSubjects(subjects);
                        break;
                    case 4:

                        subjects = dvSubjects.getSubjects("4");
                        setSubjects(subjects);
                        break;
                    case 5:

                        subjects = dvSubjects.getSubjects("5");
                        setSubjects(subjects);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

    public void setSubjects(ArrayList<Subject> subjects) {

        (linearLayout).removeAllViews();
        if (subjects.size() > 0) {
            for (int j = 0; j < subjects.size(); j++) {
                String type="";
                switch (subjects.get(j).getType()){
                    case "1": type="ლექცია";
                        break;
                    case "2": type="სემინარი";
                        break;
                }
                TextView myTextView = new TextView(getContext());
                myTextView.setText(Html.fromHtml("<b>საგანი : </b>" + subjects.get(j).getName() +  "<br>" + "<b>ლექტორი : </b>" + subjects.get(j).getProfessor() + "<br>" + "<b>ოთახი : </b>" + subjects.get(j).getAudience() + "<br>" + "<b>ლექცია/სემინარი : </b>" + type + "<br>"+ "<b>დაწყების დრო : </b>" + subjects.get(j).getBegin() + "<br>" + "<b>დამთავრების დრო: </b>" + subjects.get(j).getEnd()));
                myTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                myTextView.setTextColor(Color.WHITE);
                myTextView.setBackgroundResource(R.drawable.custom_btn_orange2);
                myTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
                // myTextView.setBackgroundResource(R.drawable.whitebutton);
                // myTextView.setBackgroundColor(Color.GRAY);
           //     myTextView.setBackgroundColor(Color.parseColor("#d9d9d9"));
               // myTextView.setTextColor(Color.BLACK);
                //myTextView.setTypeface(null, Typeface.BOLD);
                TextView myTextViewspace = new TextView(getContext());
                myTextViewspace.setText("");
                myTextViewspace.setTextSize(8);
                myTextViewspace.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
             //   myTextView.setBackgroundResource(R.drawable.custom_border3);
                linearLayout.addView(myTextViewspace);
                linearLayout.addView(myTextView);
            }
        }


    }
}

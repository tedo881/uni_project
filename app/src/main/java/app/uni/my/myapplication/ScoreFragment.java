package app.uni.my.myapplication;


import android.graphics.Color;
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
public class ScoreFragment extends Fragment {

    public static DBHandlerscores db;
    public static ArrayList<Score> scores;
     public LinearLayout linearLayout;
    public ScoreFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragmentcore,
                container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.myLiayout);
        db = new DBHandlerscores(getContext());
        scores=db.getScores();

        for(int j=0; j<scores.size(); j++){
            int sum=0;
            if(scores.get(j).getRep_ex()!=0)
                sum+=scores.get(j).getRep_ex();
            else  sum+=scores.get(j).getLast_ex();

            sum+= (scores.get(j).getFirst_act()+scores.get(j).getFirst_ex()+scores.get(j).getSec_act()+scores.get(j).getSec_ex() +scores.get(j).getThird_act()+scores.get(j).getLast_ex());
        String    res=Results(sum);

                    TextView myTextView = new TextView(getContext());
            myTextView.setText(Html.fromHtml("<b>საგანი : </b>" + scores.get(j).getName() + "<br>" + "<b>ლექტორი : </b>" + scores.get(j).getProfessor() + "<br>"  + "<b>აქტივობა 1: </b>" + scores.get(j).getFirst_act() + "<br>"  + "<b>შუალედური გამოცდა 1: </b>" + scores.get(j).getFirst_ex() + "<br>"
                    + "<b>აქტივობა 2: </b>" + scores.get(j).getSec_act()+"<br>"  + "<b>შუალედური გამოცდა 2: </b>" + scores.get(j).getSec_ex() + "<br>"
                    + "<b>აქტივობა 3: </b>" + scores.get(j).getThird_act()+"<br>"  + "<b>დასკვნითი გამოცდა: </b>" + scores.get(j).getLast_ex() + "<br>"
                    + "<b>განმეორებითი გამოცდა: </b>" + scores.get(j).getRep_ex()+"<br>"
                    + "საბოლოო შეფასება : " + sum+"<br>"  + "<b> შედეგი : </b>" + res));


            myTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            myTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);

            myTextView.setTextColor(Color.WHITE);
            myTextView.setBackgroundResource(R.drawable.custom_btn_orange2);
           // myTextView.setTextAppearance(getContext(), R.drawable.custom_btn_orange);

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

    public  String  Results(int sum){
        String res="";
        if(sum<=50) res="F";
        else if (sum<=60) res="E";
        else if (sum<=70) res="D";
        else if (sum<=80) res="C";
        else if (sum<=90) res="B";
        else res="A";

        return res;
    }


}

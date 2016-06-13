package app.uni.my.myapplication;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends Fragment {
    public static DBHandler db;
    Student student;
    private static TextView name;
    private static TextView facult;
    private static TextView semester;
    private static TextView birth;
    private static TextView sqesi;
    private static TextView misamarti;
    private static TextView number;
    private static TextView email;
    private static TextView status;

    public StudentFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info,
                container, false);
        db = new DBHandler(getContext());
        student = db.getContact();
        name = (TextView) view.findViewById(R.id.name);
        facult = (TextView) view.findViewById(R.id.faculty);
        semester = (TextView) view.findViewById(R.id.semester);
        birth = (TextView) view.findViewById(R.id.birth);
        sqesi = (TextView) view.findViewById(R.id.sqesi);
        misamarti = (TextView) view.findViewById(R.id.address);
        number= (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);
        status = (TextView) view.findViewById(R.id.status);

        String mystatus="0";

        switch (student.getStatus()){
            case "1": mystatus="აქტიური";
                status.setTextColor(Color.GREEN);
break;
            case "2": mystatus="შეჩერებული";
                status.setTextColor(Color.RED);

                break;
        }

String faculty="";
        switch (student.getFakulteti()){
            case "4": faculty="ეკონომიკისა და ბიზნესის მართვის ფაკულტეტი";
                break;
            case "7": faculty="ინფორმატიკის, მათემატიკისა და საბუნებისმეტყველო მეცნიერებათა ფაკულტეტი";
                 break;
            case "8": faculty="ჰუმანიტარულ მეცნიერებათა და სამართლის ფაკულტეტი";
                break;
            case "9": faculty="სოციალურ მეცნიერებათა ფაკულტეტი";
                break;

        }


        name.setText(student.getName()+'\n'+student.getSurname());
        name.setTextAppearance(getContext(), R.style.btnStyleOrange);
        facult.setText(faculty);
        semester.setText(student.getSemester());
        birth.setText(student.getBirth());
        sqesi.setText(student.getSqesi());
        misamarti.setText(student.getAddress());
        number.setText(student.getPhone());
        email.setText(student.getEmail());
        status.setText(mystatus);
        return view;



    }


}

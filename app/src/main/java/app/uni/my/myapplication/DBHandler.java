package app.uni.my.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.ArrayList;


/**
 * Created by tedo on 01.07.15.
 */
public class DBHandler extends SQLiteOpenHelper {


   public Context mycontext;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "AllStudentInfo";

    private static final String TABLE_STUDENT = "student";
    private static final String TABLE_EXAM = "exam";
    private static final String KEY_STUDENT_ID = "user_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_USERPASS = "user_pass";
    private static final String KEY_SQESI = "sqesi";
    private static final String KEY_PH_NO = "phone";
    private static final String KEY_FAKULTET = "fakulteti";
    private static final String KEY_BIRTH = "birth";
    private static final String KEY_JGUFI = "jgufi";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_STATUS = "status";
    // constructor

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EXAM_ID = "id_exam";
    private static final String KEY_FACULTY = "faculty";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_DAY = "day";
    private static final String KEY_SESSION_TIME = "session_time";
    private static final String KEY_HALL = "hall";
    private static final String KEY_SEAT = "seat";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mycontext=context;

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_STUDENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT,"
                + KEY_USERPASS + " TEXT,"
                + KEY_SQESI + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_FAKULTET + " TEXT,"
                + KEY_JGUFI + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PHOTO + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_SEMESTER + " TEXT,"
                + KEY_STATUS + " TEXT,"
                + KEY_BIRTH + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

        String CREATE_EXAMS_TABLE = "CREATE TABLE " + TABLE_EXAM + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EXAM_ID + " TEXT,"
                + KEY_FACULTY + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + KEY_DAY + " TEXT,"
                + KEY_SESSION_TIME + " TEXT,"
                + KEY_HALL + " TEXT,"
                + KEY_SEAT + " TEXT" + ")";
        db.execSQL(CREATE_EXAMS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        // Create tables again
        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    public void addContact(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, student.getUser_id());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_SURNAME, student.getSurname());
        values.put(KEY_USERPASS, student.getUser_pass());
        values.put(KEY_SQESI, student.getSqesi());
        values.put(KEY_PH_NO, student.getPhone());
        values.put(KEY_FAKULTET, student.getFakulteti());
        values.put(KEY_JGUFI, student.getJgupi());
        values.put(KEY_BIRTH, student.getBirth());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_SEMESTER, student.getSemester());
        values.put(KEY_PHOTO, student.getPhoto());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_STATUS, student.getStatus());

        db.insert(TABLE_STUDENT, null, values);
        db.close(); // Closing database connection
    }

    public Student getContact() {
        SQLiteDatabase db = this.getReadableDatabase();



        Cursor cursor = db.query(
                TABLE_STUDENT, // The table to query
                new String[]{KEY_STUDENT_ID, KEY_NAME, KEY_SURNAME, KEY_USERPASS, KEY_SQESI, KEY_PH_NO, KEY_FAKULTET, KEY_JGUFI, KEY_EMAIL, KEY_PHOTO,KEY_BIRTH, KEY_ADDRESS, KEY_SEMESTER,KEY_STATUS},
                KEY_STUDENT_ID + "=?", // The columns for the WHERE clause
                new String[]{String.valueOf(1)}, // The values for the WHERE clause
                null,  // don't group the rows
                null,  // don't filter by row groups
                null   // don't The sort order
        );


        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student();
        student.setId(cursor.getString(0));
        student.setName(cursor.getString(1));
        student.setSurname(cursor.getString(2));
        student.setUser_pass(cursor.getString(3));
        student.setSqesi(cursor.getString(4));
        student.setPhone(cursor.getString(5));
        student.setFakulteti(cursor.getString(6));
        student.setJgupi(cursor.getString(7));
        student.setEmail(cursor.getString(8));
        student.setPhoto(cursor.getString(9));
        student.setBirth(cursor.getString(10));
        student.setAddress(cursor.getString(11));
        student.setSemester(cursor.getString(12));
        student.setStatus(cursor.getString(13));
   //     Toast.makeText(mycontext,"gamoitana ", Toast.LENGTH_SHORT).show();
        cursor.close();
        db.close();

        return student;
    }
    public void addexams(ArrayList<Exam> ExamList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < ExamList.size(); i++) {
            Exam exam = new Exam();
            exam = ExamList.get(i);
            ContentValues values = new ContentValues();
     //       Toast.makeText(mycontext,"moxda chacera "+Integer.toString(i), Toast.LENGTH_SHORT).show();
            values.put(KEY_ID, i);
            values.put(KEY_EXAM_ID, exam.getId());
            values.put(KEY_FACULTY, exam.getFaculty());
            values.put(KEY_SUBJECT, exam.getSubject());
            values.put(KEY_DAY, exam.getDay());
            values.put(KEY_SESSION_TIME, exam.getSession_time());
            values.put(KEY_HALL, exam.getHall());
            values.put(KEY_SEAT, exam.getSeat());


            db.insert(TABLE_STUDENT, null, values);


        }
        db.close();
    }

    public ArrayList<Exam> getExams() {
        try {
            ArrayList<Exam> examList = new ArrayList<Exam>();
            String selectQuery = "SELECT * FROM " + TABLE_EXAM;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Exam exam = new Exam();
                    exam.setId(cursor.getString(1));
                    exam.setFaculty(cursor.getString(2));
                    exam.setSubject(cursor.getString(3));
                    exam.setDay(cursor.getString(4));
                    exam.setSession_time(cursor.getString(5));
                    exam.setHall(cursor.getString(6));
                    exam.setSeat(cursor.getString(7));
                    examList.add(exam);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return examList;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }
        return null;
    }

    public void addexam(Exam exam, int i) {
        SQLiteDatabase db = this.getReadableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_ID, i);
        values.put(KEY_EXAM_ID, exam.getId());
        values.put(KEY_FACULTY, exam.getFaculty());
        values.put(KEY_SUBJECT, exam.getSubject());
        values.put(KEY_DAY, exam.getDay());
        values.put(KEY_SESSION_TIME, exam.getSession_time());
        values.put(KEY_HALL, exam.getHall());
        values.put(KEY_SEAT, exam.getSeat());
        db.insert(TABLE_EXAM, null, values);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mycontext);
        db.close();
    }
}

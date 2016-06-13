package app.uni.my.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by tedo on 01.07.15.
 */
public class DBHandlerSubjects extends SQLiteOpenHelper {

    public Context mycontext;
     private static final int DB_VERSION = 2;
    private static final String DB_NAME = "Subject";
    private static final String TABLE_CONTACTS = "subject";
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT_ID = "subjectid";
    private static final String KEY_DAY = "day";
    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_AUDIENCE = "audience";
    private static final String KEY_TYPE = "type";
    private static final String KEY_BEGIN = "begin";
    private static final String KEY_END = "end";
    private static final String KEY_NAME = "name";
    public DBHandlerSubjects(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mycontext = context;

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SUBJECT_ID + " TEXT,"
                + KEY_DAY + " TEXT,"
                + KEY_PROFESSOR + " TEXT,"
                + KEY_AUDIENCE + " TEXT,"
                + KEY_TYPE + " TEXT,"
                + KEY_BEGIN + " TEXT,"
                + KEY_END + " TEXT,"
                + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }



    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public void addsubject(Subject subject, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, i);
        values.put(KEY_SUBJECT_ID, subject.getId());

        values.put(KEY_PROFESSOR, subject.getProfessor());
        values.put(KEY_DAY, subject.getDay());
        values.put(KEY_TYPE, subject.getType());
        values.put(KEY_AUDIENCE, subject.getAudience());
        values.put(KEY_BEGIN, subject.getBegin());
        values.put(KEY_END, subject.getEnd());
        values.put(KEY_NAME, subject.getName());
        db.insert(TABLE_CONTACTS, null, values);
    //Toast.makeText(mycontext, "chaicera", Toast.LENGTH_SHORT).show();
        db.close();
    }


    public ArrayList<Subject> getSubjects(String day) {
        try {

            ArrayList<Subject> subjectList = new ArrayList<Subject>();

            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS + " where day = "+day;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);


            if (cursor.moveToFirst()) {
                do {
                    Subject subject = new Subject();
                    subject.setId(cursor.getString(0));
                    subject.setDay(cursor.getString(1));
                    subject.setGroup(cursor.getString(2));
                    subject.setProfessor(cursor.getString(3));
                    subject.setAudience(cursor.getString(4));
                    subject.setType(cursor.getString(5));
                    subject.setBegin(cursor.getString(6));
                    subject.setEnd(cursor.getString(7));
                    subject.setName(cursor.getString(8));
                    subjectList.add(subject);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();

            return subjectList;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }
        return null;
    }


}

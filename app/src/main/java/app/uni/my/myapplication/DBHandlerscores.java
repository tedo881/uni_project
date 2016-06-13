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
public class DBHandlerscores extends SQLiteOpenHelper {
    public Context mycontext;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "scoreManager";
    private static final String TABLE_CONTACTS = "scores";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE_ID = "id_score";
    private static final String KEY_GROUP = "group_sc";
    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_NAME = "name_sc";
    private static final String KEY_FIRST_ACT = "first_act";
    private static final String KEY_THIRD_ACT = "third_act";
    private static final String KEY_SEC_ACT = "sec_act";
    private static final String KEY_FIRST_EX = "first_ex";
    private static final String KEY_THIRD_EX = "third_ex";
    private static final String KEY_LAST_EX = "last_ex";
    private static final String KEY_REP_EX = "rep_ex";

    public DBHandlerscores(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mycontext = context;

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SCORE_ID + " TEXT,"
                + KEY_PROFESSOR + " TEXT,"
                + KEY_GROUP + " TEXT,"
                + KEY_NAME + " TEXT,"

                + KEY_FIRST_ACT + " INTEGER,"
                + KEY_THIRD_ACT + " INTEGER,"
                + KEY_SEC_ACT + " INTEGER,"
                + KEY_FIRST_EX + " INTEGER,"
                + KEY_THIRD_EX + " INTEGER,"
                + KEY_LAST_EX + " INTEGER,"
                + KEY_REP_EX + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public void addscore(Score score, int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, i);
        values.put(KEY_SCORE_ID, score.getId());
        values.put(KEY_GROUP, score.getGroup());
        values.put(KEY_PROFESSOR, score.getProfessor());
        values.put(KEY_NAME, score.getName());
        values.put(KEY_FIRST_ACT, score.getFirst_act());
        values.put(KEY_THIRD_ACT, score.getThird_act());
        values.put(KEY_SEC_ACT, score.getSec_act());
        values.put(KEY_FIRST_EX, score.getFirst_ex());
        values.put(KEY_THIRD_EX, score.getThird_act());
        values.put(KEY_LAST_EX, score.getLast_ex());
        values.put(KEY_REP_EX, score.getRep_ex());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }


    public ArrayList<Score> getScores() {
        try {
            ArrayList<Score> scoreList = new ArrayList<Score>();
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Score score = new Score();
                    score.setId(cursor.getString(1));
                    score.setGroup(cursor.getString(3));
                    score.setProfessor(cursor.getString(2));
                    score.setName(cursor.getString(4));
                    score.setFirst_act(cursor.getInt(5));
                    score.setSec_act(cursor.getInt(6));
                    score.setThird_act(cursor.getInt(7));
                    score.setFirst_ex(cursor.getInt(8));
                    score.setSec_ex(cursor.getInt(9));
                    score.setLast_ex(cursor.getInt(10));
                    score.setRep_ex(cursor.getInt(11));

                    scoreList.add(score);
                } while (cursor.moveToNext());
            }


            cursor.close();
            db.close();

            return scoreList;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }

        return null;
    }


}

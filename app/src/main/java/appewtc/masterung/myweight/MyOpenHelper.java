package appewtc.masterung.myweight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 1/28/15 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    private static final String DATABASE_NAME = "my_weight.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table weightTABLE (_id integer primary key, "+" Date text, Weight double);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL(CREATE_TABLE);
    }   // onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }   // onUpgrade

}   // Main Class

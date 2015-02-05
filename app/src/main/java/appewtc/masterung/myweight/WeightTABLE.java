package appewtc.masterung.myweight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/28/15 AD.
 */
public class WeightTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite;
    private double douWeight;
    private String strLastDate;
    private boolean bolStatusDatabase;

    public static final String TABLE_WEIGHT = "weightTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_WEIGHT = "Weight";



    public WeightTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQLite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public boolean checkCursor() {

        Cursor objCursor = readSQLite.query(TABLE_WEIGHT, new String[] {COLUMN_ID, COLUMN_DATE, COLUMN_WEIGHT}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToLast();
        }

        return objCursor.isBeforeFirst();
    }   // checkCursor


    public String lastUpdata() {

        Cursor objCursor = readSQLite.query(TABLE_WEIGHT, new String[] {COLUMN_ID, COLUMN_DATE}, null, null, null, null, null);

        if (objCursor != null) {
            objCursor.moveToLast();
            strLastDate = objCursor.getString(objCursor.getColumnIndex(COLUMN_DATE));
        }

        return strLastDate;
    }   // lastUpdate

    public double currentWeight() {

        Cursor objCursor = readSQLite.query(TABLE_WEIGHT, new String[] {COLUMN_ID, COLUMN_DATE, COLUMN_WEIGHT}, null, null, null, null, null);

        if (objCursor != null) {

            objCursor.moveToLast();

            douWeight = objCursor.getDouble(objCursor.getColumnIndex(COLUMN_WEIGHT));

        }   // if

        return douWeight;
    }   // currentWeight

    //Add New Value to SQLite
    public long addNewValueToSQLite(String strDate, double douWeight) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_DATE, strDate);
        objContentValue.put(COLUMN_WEIGHT, douWeight);

        return writeSQLite.insert(TABLE_WEIGHT, null, objContentValue);
    }   // addNewValueToSQLite

}   // Main Class

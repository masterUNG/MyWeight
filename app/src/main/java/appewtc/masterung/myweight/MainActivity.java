package appewtc.masterung.myweight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private WeightTABLE objWeightTABLE;
    private TextView txtShowWeight;
    private double douWeight;
    private String strCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        txtShowWeight = (TextView) findViewById(R.id.txtShowWeight);



        //Connected Database
        connectedDatabase();

        //Tester Update
        testerUpdate();

        //Set CurrentWeight
        setCurrentWeight();

    }   // onCreate

    @Override
    protected void onRestart() {
        super.onRestart();
        setCurrentWeight();
    }

    private void setCurrentWeight() {

        douWeight = objWeightTABLE.currentWeight();
        txtShowWeight.setText(Double.toString(douWeight));

    }   // setCurrentWeight

    public void clickUpdate(View view) {

        String strLastUpdate = objWeightTABLE.lastUpdata();

        getCurrentDate();

        if (strCurrentDate.equals(strLastUpdate)) {

            confirmAlertDialog();

        } else {

            //Show Intent
        Intent objIntent = new Intent(MainActivity.this, FormRecordWeightActivty.class);
        startActivity(objIntent);

        }



    }   // clickUpdate

    private void confirmAlertDialog() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("Confirm Update");
        objAlert.setMessage("Have Value at Date = " + strCurrentDate + "\n" + "Are you Sure Update New Weight ?");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteValue();
                Intent objIntent = new Intent(MainActivity.this, FormRecordWeightActivty.class);
                startActivity(objIntent);
            }
        });
        objAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        objAlert.show();

    }   // confirmAlertDialog

    private void deleteValue() {

        SQLiteDatabase deleteSQLite = openOrCreateDatabase("my_weight.db", MODE_PRIVATE, null);
        Cursor objCursor = deleteSQLite.rawQuery("SELECT * FROM weightTABLE", null);
        objCursor.moveToLast();
        int intID = objCursor.getInt(objCursor.getColumnIndex("_id"));
        deleteSQLite.delete("weightTABLE", "_id" + "=" + intID, null);

    }   // deleteValue

    private void getCurrentDate() {

        DateFormat objDataFormat = new SimpleDateFormat("dd/MM/yy");
        Date objDate = new Date();
        strCurrentDate = objDataFormat.format(objDate);

        Log.d("weight", "Current Date = " + strCurrentDate);

    }   // getCurrentData

    private void testerUpdate() {
        objWeightTABLE.addNewValueToSQLite("testDate", 123.45);
    }   // testerUpdate

    private void connectedDatabase() {

        objWeightTABLE = new WeightTABLE(this);

    }   //  connectedDatabase


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class

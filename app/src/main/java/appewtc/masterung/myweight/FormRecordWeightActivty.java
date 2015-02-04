package appewtc.masterung.myweight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormRecordWeightActivty extends ActionBarActivity {

    //Explicit
    private TextView txtShowDate;
    private EditText edtWeight;
    private String strShowDate, strWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_record_weight_activty);

        //Bind Widget
        bindWidget();

        //get Time from Device
        getTimeFromDevice();

    }   // onCreate

    public void clickSave(View view) {

        strWeight = edtWeight.getText().toString().trim();
        if (strWeight.equals("")) {
            showAlert();
        } else {
            showLog();

            confirmData();

        }   // if



    }   // clickSave

    private void confirmData() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("Are You Sure ?");
        objAlert.setMessage("Date = " + strShowDate + "\n" + "Weight = " + strWeight + " Kg");
        objAlert.setCancelable(false);
        objAlert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                upDatatoSQLite();

            }
        });
        objAlert.show();

    }   // confirmData

    private void upDatatoSQLite() {

        WeightTABLE objWeight = new WeightTABLE(this);
        long inSertData = objWeight.addNewValueToSQLite(strShowDate, Double.parseDouble(strWeight));
        edtWeight.setText("");
        Toast.makeText(FormRecordWeightActivty.this, "Updata Finish", Toast.LENGTH_SHORT).show();

    }   // upDateSQLITE

    private void showLog() {
        Log.d("weight", "time = " + strShowDate);
        Log.d("weight", "weight = " + strWeight);
    }   // showLog

    private void showAlert() {

        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.danger);
        objAlert.setTitle("Have Space");
        objAlert.setMessage("Please Fill in the Blank");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }   // showAlert

    private void getTimeFromDevice() {

        DateFormat objDateFormat = new SimpleDateFormat("dd/MM/yy");
        Date objDate = new Date();
        strShowDate = objDateFormat.format(objDate);
        txtShowDate.setText(strShowDate);

    }   // getTimeFromDevice

    private void bindWidget() {
        txtShowDate = (TextView) findViewById(R.id.txtShowDate);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
    }   // bindWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_record_weight_activty, menu);
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

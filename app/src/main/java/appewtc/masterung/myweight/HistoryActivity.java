package appewtc.masterung.myweight;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class HistoryActivity extends ListActivity {

    private WeightTABLE objWeightTABLE;
    private SimpleCursorAdapter objSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_history);

        objWeightTABLE = new WeightTABLE(this);

        //Create Listview
        createListView();


    }   // onCreate

    private void createListView() {

        Cursor ListDate = objWeightTABLE.readAllData();
        String[] from = new String[]{WeightTABLE.COLUMN_DATE};
        int[] target = new int[]{R.id.txtListDate};
        objSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_history, ListDate, from, target);
        setListAdapter(objSimpleCursorAdapter);

    }   // createListView

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor objCursor = (Cursor) l.getItemAtPosition(position);
        String strDate = objCursor.getString(objCursor.getColumnIndex(WeightTABLE.COLUMN_DATE));
        double douWeight = objCursor.getDouble(objCursor.getColumnIndex(WeightTABLE.COLUMN_WEIGHT));

        //show Alert Dialog
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setIcon(R.drawable.icon_myaccount);
        objAlert.setTitle("You Click " + strDate);
        objAlert.setMessage("Your Weight = " + Double.toString(douWeight) + " Kg");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
}   // MainClass

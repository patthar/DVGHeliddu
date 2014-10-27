package com.dvgheliddu.view;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.dvgheliddu.connectors.TimePickerFragment;
import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.kagga.R;

/**
 * An activity representing a single KaggaHistory detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link KaggaHistoryListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link KaggaHistoryDetailFragment}.
 */
public class KaggaHistoryDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaggahistory_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            //test re-use code from here

            Kagga kaggaObj = Kagga.getInstance(getBaseContext());
            KaggaDeserializer k = kaggaObj.readKagga(getIntent().getIntExtra(KaggaHistoryDetailFragment.ARG_ITEM_ID,0));


            //ends here


            arguments.putInt(KaggaHistoryDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(KaggaHistoryDetailFragment.ARG_ITEM_ID, 0));
            KaggaHistoryDetailFragment fragment = new KaggaHistoryDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.kaggahistory_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kagga_detail, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, KaggaHistoryListActivity.class));
            return true;
        }
        else if( id == R.id.action_settings) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


            //initiate alarm dialog fragment here
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getFragmentManager(), "timePicker" );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

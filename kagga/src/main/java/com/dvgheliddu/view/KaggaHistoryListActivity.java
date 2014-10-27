package com.dvgheliddu.view;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;


import com.dvgheliddu.connectors.TimePickerFragment;
import com.dvgheliddu.kagga.R;

/**
 * An activity representing a list of KaggaHistories. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link KaggaHistoryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link KaggaHistoryListFragment} and the item details
 * (if present) is a {@link KaggaHistoryDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link KaggaHistoryListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class KaggaHistoryListActivity extends Activity
        implements KaggaHistoryListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaggahistory_list);

        if (findViewById(R.id.kaggahistory_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((KaggaHistoryListFragment) getFragmentManager()
                    .findFragmentById(R.id.kaggahistory_list))
                    .setActivateOnItemClick(true);
        }

        KaggaHistoryListFragment kaggaListFr = (KaggaHistoryListFragment)getFragmentManager().findFragmentById(R.id.kaggahistory_list);

        if(kaggaListFr == null) {
            kaggaListFr = new KaggaHistoryListFragment();
            kaggaListFr.getFragmentManager().beginTransaction()
                    .replace(R.id.kaggahistory_list, kaggaListFr).commit();
        }
        // TODO: If exposing deep links into your app, handle intents here.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kagga_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //initiate alarm dialog fragment here
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getFragmentManager(), "timePicker" );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method from {@link KaggaHistoryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(KaggaHistoryDetailFragment.ARG_ITEM_ID, id);
            KaggaHistoryDetailFragment fragment = new KaggaHistoryDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.kaggahistory_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.

            //new code here
            Intent detailIntent = new Intent(this, KaggaDetail.class);
            detailIntent.putExtra("koftd", id);
            startActivity(detailIntent);
            //

            //Intent detailIntent = new Intent(this, KaggaHistoryDetailActivity.class);
            //detailIntent.putExtra(KaggaHistoryDetailFragment.ARG_ITEM_ID, id);
            //startActivity(detailIntent);
        }
    }
}

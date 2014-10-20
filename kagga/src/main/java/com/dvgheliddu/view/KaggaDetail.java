package com.dvgheliddu.view;

import java.lang.reflect.Array;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.kagga.R;

public class KaggaDetail extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kagga_detail);
        onNewIntent(getIntent());

        //Kagga k = new Kagga(getBaseContext());
        //KaggaDeserializer thisKagga = k.readKagga(k.rollDice());
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), thisKagga);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public KaggaDeserializer getmKagga() {
            return mKagga;
        }

        public void setmKagga(KaggaDeserializer mKagga) {
            this.mKagga = mKagga;
        }

        KaggaDeserializer mKagga = null;

        public SectionsPagerAdapter(FragmentManager fm, KaggaDeserializer kagga) {

            super(fm);
            setmKagga(kagga);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return KaggaFragment.newInstance(position + 1, getmKagga());
                case 1:
                    return TransliterationFragment.newInstance(position + 1, getmKagga());
                case 2:
                    return TranslationFragment.newInstance(position + 1, getmKagga());
            }
            return null;
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras != null && extras.containsKey("koftd")) {
            Kagga kagga = new Kagga(getBaseContext());
            Integer num = (Integer) extras.get("koftd");
            if(mSectionsPagerAdapter == null) {
                mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), kagga.readKagga(num));
            }
        }
    }
}

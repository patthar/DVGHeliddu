package com.dvgheliddu.connectors;

/**
 * Created by ppatthar on 01/11/14.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.kagga.R;
import com.dvgheliddu.view.KaggaFragment;
import com.dvgheliddu.view.TranslationFragment;
import com.dvgheliddu.view.TransliterationFragment;

import java.util.Locale;



/**
 * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public KaggaDeserializer getmKagga() {
        return mKagga;
    }

    public void setmKagga(KaggaDeserializer mKagga) {
        this.mKagga = mKagga;
    }

    KaggaDeserializer mKagga = null;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    Context mContext = null;

    public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm, KaggaDeserializer kagga, Context ctx) {

        super(fm);
        setmKagga(kagga);
        setmContext(ctx);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
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
                return getmContext().getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return getmContext().getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return getmContext().getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }
}

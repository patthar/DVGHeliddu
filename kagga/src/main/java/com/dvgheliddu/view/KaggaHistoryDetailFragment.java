package com.dvgheliddu.view;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvgheliddu.connectors.SectionsPagerAdapter;
import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.data.KaggaHistory;
import com.dvgheliddu.kagga.R;

import com.dvgheliddu.view.dummy.DummyContent;

import java.util.Random;

/**
 * A fragment representing a single KaggaHistory detail screen.
 * This fragment is  contained in a {@link KaggaHistoryListActivity}
 */
public class KaggaHistoryDetailFragment extends android.support.v4.app.Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private KaggaDeserializer mItem;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KaggaHistoryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(( ARG_ITEM_ID))) {
            if(getArguments().getInt(ARG_ITEM_ID) != 0) {
                Kagga k = Kagga.getInstance(getActivity().getBaseContext());

                mItem = k.readKagga(getArguments().getInt(ARG_ITEM_ID));
            }
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), mItem, getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_kaggahistory_detail, container, false);
        mViewPager = (ViewPager) rootView.findViewById(R.id.kaggahistory_detail_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

       return rootView;
    }
}

package com.dvgheliddu.view;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.data.KaggaHistory;
import com.dvgheliddu.kagga.R;

import com.dvgheliddu.view.dummy.DummyContent;

/**
 * A fragment representing a single KaggaHistory detail screen.
 * This fragment is either contained in a {@link KaggaHistoryListActivity}
 * in two-pane mode (on tablets) or a {@link KaggaHistoryDetailActivity}
 * on handsets.
 */
public class KaggaHistoryDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private KaggaDeserializer mItem;

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
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            if(getArguments().getInt(ARG_ITEM_ID) != 0) {
                Kagga k = Kagga.getInstance(getActivity().getBaseContext());

                mItem = k.readKagga(getArguments().getInt(ARG_ITEM_ID));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kaggahistory_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.kaggahistory_detail)).setText(mItem.getKagga());
        }

        return rootView;
    }
}

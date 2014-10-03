package com.dvgheliddu.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.kagga.R;

/**
 * Created by ppatthar on 02/10/14.
 */
public class KaggaFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number1";
    private KaggaDeserializer mKagga = null;

    public KaggaFragment () {

    }

    public static KaggaFragment newInstance(int sectionNumber, KaggaDeserializer kagga) {

        KaggaFragment fragment = new KaggaFragment();
        fragment.mKagga = kagga;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kagga_detail, container, false);


        TextView kaggaTitle = (TextView) rootView.findViewById(R.id.section_label);
        kaggaTitle.setText(mKagga.getTitle());
        TextView kaggaContent = (TextView) rootView.findViewById(R.id.section_content);
        kaggaContent.setText(mKagga.getKagga());

        return rootView;
    }
}

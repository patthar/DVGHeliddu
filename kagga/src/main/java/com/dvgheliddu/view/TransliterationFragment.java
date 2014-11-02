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
 * Created by ppatthar on 28/09/14.
 */



public class TransliterationFragment extends android.support.v4.app.Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number2";
        private KaggaDeserializer mKagga = null;

        public TransliterationFragment () {

        }

        public static TransliterationFragment newInstance(int sectionNumber, KaggaDeserializer kagga) {
            TransliterationFragment fragment = new TransliterationFragment();
            fragment.mKagga = kagga;
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_kagga_detail, container, false);

            TextView kaggaTitle = (TextView) rootView.findViewById(R.id.section_label);
            kaggaTitle.setText(mKagga.getTitle());
            TextView kaggaTransliteration = (TextView) rootView.findViewById(R.id.section_content);
            kaggaTransliteration.setText(mKagga.getTransliteration());

            return rootView;
        }

}


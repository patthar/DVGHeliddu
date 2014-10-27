package com.dvgheliddu.data;

import android.content.Context;

import com.dvgheliddu.view.KaggaDetail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ppatthar on 27/10/14.
 */
public class KaggaHistory {

    public static ArrayList<KaggaTitle> KaggaTitles = new ArrayList<KaggaTitle>();

    public static ArrayList<KaggaTitle> getKaggaHistoryList(Context ctx) {
        if(KaggaTitles.isEmpty()) {
            Kagga kagga = Kagga.getInstance(ctx);
            if(kagga != null) {

                for (Integer id : kagga.getmKaggaRandomMap().keySet()) {
                    KaggaDeserializer k = kagga.readKagga(id);
                    if (k != null) {
                        KaggaTitles.add(new KaggaTitle(k.getId(), k.getTitle()));
                    }
                }
            }
        }
        return KaggaTitles;
    }

    public static class KaggaTitle {
        public int mId;
        public String mTitle;

        public KaggaTitle(int id, String title) {
            this.mId = id;
            this.mTitle = title;

        }

        @Override
        public String toString() {
            return (mId + " : " + mTitle);
        }
    }
}

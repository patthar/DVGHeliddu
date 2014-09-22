package com.dvgheliddu.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by ppatthar on 28/08/14.
 */
public class Kagga {

    public HashMap<Integer, Integer> getmKaggaRandomMap() {
        return mKaggaRandomMap;
    }
    Context mContext = null;

    public Kagga(Context ctx) {
        mContext = ctx;
    }

    public void setmKaggaRandomMap(HashMap<Integer, Integer> mKaggaRandomMap) {
        this.mKaggaRandomMap = mKaggaRandomMap;
    }

    public Integer rollDice() {
        //randomize and return the value
        Random rand = new Random();
        return rand.nextInt(KAGGA_TOTAL);
    }

    public void readKagga(Integer kaggaNum) {
        InputStream kaggaData;
        try {
            kaggaData = mContext.getAssets().open("kagga_data_split.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(kaggaData, "UTF-8"));
            StringBuilder jsonStr = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                jsonStr.append(line);
            }

            JSONObject allKaggaObj = new JSONObject(jsonStr.toString());
            JSONObject thisKagga = allKaggaObj.getJSONObject(kaggaNum.toString());

            if(thisKagga.getBoolean("content")) {

            }

        }
        catch(Exception er){
            Log.e("Kagga" , "Error Occured" + er.getStackTrace());
        }
        if(true) {

        }
    }

    private HashMap<Integer, Integer> mKaggaRandomMap;
    final int KAGGA_TOTAL = 944;
    private String mKagga = null;
    private String mTransliteration = null;
    private String mTranslation = null;
}

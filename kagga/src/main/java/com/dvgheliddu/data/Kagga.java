package com.dvgheliddu.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
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

    public KaggaDeserializer readKagga(Integer kaggaNum) {
        InputStream kaggaData;
        try {
            kaggaData = mContext.getAssets().open("kagga_data_split.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(kaggaData, "UTF-8"));
            String jsonStr = null;
            String line;

            while((line = reader.readLine()) != null) {
                jsonStr = line;
            }

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jArray = parser.parse(jsonStr).getAsJsonArray();

            HashMap<Integer,KaggaDeserializer> kaggaMap = new HashMap<Integer, KaggaDeserializer>();

            for(JsonElement obj: jArray) {
                KaggaDeserializer kagga = gson.fromJson(obj, KaggaDeserializer.class);
                kaggaMap.put(kagga.getId(), kagga);
            }

            return kaggaMap.get(kaggaNum);

        }
        catch(Exception er){
            Log.e("Kagga" , "Error Occured" + er.getStackTrace());
        }

        return null;
    }

    private HashMap<Integer, Integer> mKaggaRandomMap;
    final int KAGGA_TOTAL = 944;
    private String mKagga = null;
    private String mTransliteration = null;
    private String mTranslation = null;
}

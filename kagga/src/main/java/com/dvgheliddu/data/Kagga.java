package com.dvgheliddu.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import com.dvgheliddu.data.Utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by ppatthar on 28/08/14.
 */
public class Kagga implements Serializable {

    private static Kagga mKaggaSettings = null;
    private static final long serialVersionUID = 5267570603637142753L;

    protected  Kagga() {

    }

    public void save(){
        FileOutputStream outputStream;

        try {

            outputStream = mContext.openFileOutput(this.getClass().getName(), mContext.MODE_PRIVATE);
            ObjectOutputStream writer = new ObjectOutputStream(outputStream);
            writer.writeObject(mKaggaSettings);
            writer.close();
            outputStream.close();
            Log.i("File", "serialization successful");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void read(Context ctx) {
        FileInputStream inputStream;
        Kagga rKagga = null;

        try {
            inputStream = ctx.openFileInput(this.getClass().getName());
            ObjectInputStream reader = new ObjectInputStream(inputStream);
            rKagga = (Kagga) reader.readObject();
        } catch (Exception e){
            e.printStackTrace();
        }

        //The read can fail. No harm if we cannot but dont let
        //the app crash.
        if(rKagga != null) {
            mKaggaSettings = rKagga;
        }
    }


    public static Kagga getInstance(Context ctx) {
        if(mKaggaSettings == null ) {
            mKaggaSettings = new Kagga();
            mKaggaSettings.read(ctx);
            mKaggaSettings.mContext = ctx;
        }

        return mKaggaSettings;
    }

    public void setmKaggaRandomMap(HashMap<Integer, Integer> mKaggaRandomMap) {
        mKaggaSettings.mKaggaRandomMap = mKaggaRandomMap;
    }

    public Integer rollDice() {
        //randomize and return the value
        Random rand = new Random();
        Integer randomNum = rand.nextInt(KAGGA_TOTAL);
        if(mKaggaSettings.getmKaggaRandomMap() != null) {
            while (mKaggaSettings.getmKaggaRandomMap().containsKey(randomNum)) {
                randomNum = rand.nextInt(KAGGA_TOTAL);
            }
        }
        return randomNum;
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

            if(mKaggaSettings != null) {
                mKaggaSettings.getmKaggaRandomMap().put(kaggaNum, 1);
            }
            save();
            return kaggaMap.get(kaggaNum);

        }
        catch(Exception er){
            Log.e("Kagga" , "Error Occured" + er.getStackTrace());
        }

        return null;
    }

    private HashMap<Integer, Integer> mKaggaRandomMap = new HashMap<Integer, Integer>();
    final int KAGGA_TOTAL = 944;
    public HashMap<Integer, Integer> getmKaggaRandomMap() {
        return mKaggaRandomMap;
    }
    transient  Context  mContext = null;

    public Integer getKaggaAlarmTimeHour() {
        return kaggaAlarmTimeHour;
    }

    public void setKaggaAlarmTimeHour(Integer kaggaAlarmTimeHour) {
        this.kaggaAlarmTimeHour = kaggaAlarmTimeHour;
        this.save();
    }

    public Integer getKaggaAlarmTimeMinute() {
        return kaggaAlarmTimeMinute;
    }

    public void setKaggaAlarmTimeMinute(Integer kaggaAlarmTimeMinute) {
        this.kaggaAlarmTimeMinute = kaggaAlarmTimeMinute;
        this.save();
    }

    Integer kaggaAlarmTimeHour = Constants.TIME_TO_DELIVER_NOTIFICATION_HOUR;
    Integer kaggaAlarmTimeMinute = Constants.TIME_TO_DELIVER_NOTIFICATION_MINUTE;


}

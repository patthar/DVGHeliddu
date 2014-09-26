package com.dvgheliddu.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppatthar on 25/09/14.
 */
public class KaggaDeserializer {

    String content;
    String translation;
    String title;
    int id;
    String transliteration;
    String kagga;

    private KaggaDeserializer(String content, String translation, String title, String transliteration, String mkagga, int id) {
        setContent(content);
        setTranslation(translation);
        setTransliteration(transliteration);
        setTitle(title);
        setKagga(mkagga);
        setId(id);
    }


    public String getContent() {
        return content;
    }

    public String getTranslation() {
        return translation;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getTransliteration() {
        transliteration = String.valueOf(addNewlineToDanda(transliteration));
        transliteration = String.valueOf(addNewlineToDheergaDanda(transliteration));
        return transliteration;
    }

    public String getKagga() {
        //String transformData = kagga;
        kagga = String.valueOf(addNewlineToDanda(kagga));
        kagga = String.valueOf(addNewlineToDheergaDanda(kagga));
        return kagga;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public void setKagga(String mkagga) {
        this.kagga = mkagga;
    }

    public static StringBuffer addNewlineToDanda(String data){
        Pattern p = Pattern.compile("(\\u0964)");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {

            m.appendReplacement(buf, m.group(1) + "\n");
        }
        m.appendTail(buf);
        return buf;
    }

    public static StringBuffer addNewlineToDheergaDanda(String data){
        Pattern p = Pattern.compile("(\\u0965)(.*\n)");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {

            m.appendReplacement(buf, m.group(1) + "\n" + m.group(2));
        }
        m.appendTail(buf);
        return buf;
    }
}

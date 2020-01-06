package com.sunland.new_im.websocket;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {

    public static Gson gson = new GsonBuilder()
        .create();

    static{
    }


    /**
     * parse json string to object
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws java.io.IOException
     */
    public static <T> T parseJsonObject(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    public static <T> T parseJsonObject(JsonElement json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    /**
     * parse json string to Array
     *
     * @param clz
     * @return
     * @throws Exception
     */
    public static <T> T[] parseJsonArray(String json, Class<T> clz) {
    	T[] result = gson.fromJson(json, new TypeToken<T[]>(){}.getType());
        return result;
    }
    
    /**
     * parse json string to List
     *
     * @param clz
     * @return
     * @throws Exception
     */
//    public static <T> List<T> parseJsonList(String json, Class<T> clz) {
//    	List<T> result = gson.fromJson(json,
//                new TypeToken<List<T>>(){}.getType());
//        return result;
//    }

    /**
     * parse json string to Map
     * @param json
     * @param keyType
     * @param valueType
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<K,V> parseJsonMap(String json, Class<K> keyType, Class<V> valueType) {
    	Map<K,V> result = gson.fromJson(json,
                new TypeToken<Map<K,V>>(){}.getType());
        return result;
    }


    public static String toJson(Object obj) {
        try {
            return gson.toJson(obj);
        }catch(Exception e){
            Log.e("JsonParser", "wangsong", e);
            return "{}";
        }
    }


    public static <T> List<T> parseJsonList(String json, Class<T> clz) throws Exception {
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray array = element.getAsJsonArray();
        List<T> data = new ArrayList<T>();
        for (JsonElement jo : array) {
            data.add(gson.fromJson(jo, clz));
        }
        return data;
    }

    public static <T> List<T> parseJsonList(JsonArray array, Class<T> clz) throws Exception {
        List<T> data = new ArrayList<T>();
        for (JsonElement jo : array) {
            data.add(parseJsonObject(jo, clz));
        }
        return data;
    }

}

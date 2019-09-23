package com.ghs.ghspm.tools;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author:wang_sir
 * Time:2018/7/7 18:16
 * Description:This is mGsonManager
 */
public class GsonManager {
    private static Gson mGson = null;
    private static String s;


    private GsonManager() {
        if (mGson == null) {
            mGson = new Gson();
        }
    }

    public static GsonManager getInstance() {
        return GsonManagerHolder.MGSON;
    }

    private static class GsonManagerHolder {
        private static GsonManager MGSON = new GsonManager();
    }

    /**
     * 把一个map变成json字符串
     *
     * @param map
     * @return
     */
    public String parseMapToJson(Map<?, ?> map) {
        try {
            return mGson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把一个json字符串变成对象
     *
     * @param json
     * @param cls
     * @return
     */
    public <T> T parseJsonToBean(String json, Class<T> cls) {
        T t = null;
        try {
            t = mGson.fromJson(json, cls);
        } catch (Exception e) {
            Log.i("TTT", "解析失败" + e.getMessage());
        }
        return t;
    }

    /**
     * 把json字符串变成map
     *
     * @param json
     * @return
     */
    public static HashMap<String, String> parseJsonToMap(String json) {
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        HashMap<String, String> map = null;
        try {
            map = mGson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return map;
    }

    /**
     * 把json字符串变成集合
     * params: new TypeToken<List<yourbean>>(){}.getType(),
     *
     * @param json
     * @param type new TypeToken<List<yourbean>>(){}.getType()
     * @return
     */
    public List<?> parseJsonToList(String json, Type type) {
        return mGson.fromJson(json, type);
    }

    /**
     * 将List变成json串
     */
    public String parseListToJson(List<?> list) {
        return mGson.toJson(list);
    }

    /**
     * 获取json串中某个字段的值，注意，只能获取同一层级的value
     *
     * @param json
     * @param key
     * @return
     */
    public static String getFieldValue(String json, String key) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        if (!json.contains(key)) {
            return "";
        }
        JSONObject jsonObject = null;
        String value = null;
        try {
            jsonObject = new JSONObject(json);
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

//    /**
//     * 格式化json
//     * @param uglyJSONString
//     * @return
//     */
//    public static String jsonFormatter(String uglyJSONString){
//        mGson mGson = new mGsonBuilder().setPrettyPrinting().create();
//        JsonParser jp = new JsonParser();
//        JsonElement je = jp.parse(uglyJSONString);
//        String prettyJsonString = mGson.toJson(je);
//        return prettyJsonString;
//    }


    /**
     * 判断是否是json串
     *
     * @param json
     * @param cls
     * @return
     */
    public static boolean isJson(String json, Object cls) {
        Object t = null;
        try {
            t = mGson.fromJson(json, (Class<Object>) cls);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 将json键值对分别解析到数组中
     *
     * @param jsonject 需要解析的json对象
     * @return type="key"：返回json对象中"键"的字符串， type="key""value":返回json对象中"值"的字符串
     */
    public List<String> analyzeJsonToArray(String jsonject) {
        List<String> arrayList = new ArrayList<>();
        String string = jsonject.toString();
        string = string.replace("}", "");
        string = string.replace("{", "");
        string = string.replace("\"", "");
        String[] strings = string.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(strings[0])) {
                String replace = strings[i].replace("call_center:", "");
                arrayList.add(replace);
            } else {
                arrayList.add(strings[i]);
            }
        }
        return arrayList;
    }


    public static String getStringContent(String str) {

        List<String> arrayList = new ArrayList<>();
        arrayList.clear();
        String string = str.toString();
        string = string.replace("}", "");
        string = string.replace("{", "");
        string = string.replace("\"", "");
        String[] strings = string.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(strings[0])) {
                String replace = strings[i].replace("call_center:", "");
                arrayList.add(replace);
            } else {
                arrayList.add(strings[i]);
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).contains("工单内容")) {
                s = arrayList.get(i).replace("工单内容:", "");
                break;
            }

        }

        return s;
    }


    /**
     * @param content json字符串
     * @return 如果转换失败返回null,
     */
    public static HashMap<String, String> jsonToMap(String content) {
        HashMap<String, String> jsonToMap;
        String substring = content.substring(15, content.length() - 1);
        jsonToMap = parseJsonToMap(substring);
        return jsonToMap;

    }


}

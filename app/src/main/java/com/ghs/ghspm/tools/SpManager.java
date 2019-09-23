/**
 * @Title: PreferenceManager.java
 * @Package com.hutu.localfile.manager
 * @Description:
 * @author Long Li
 * @date 2015-5-13 下午5:45:40
 * @version V1.0
 */
package com.ghs.ghspm.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpManager {

    private static SpManager mPrefManager = null;
    private SharedPreferences prefs;
    private Context mContext;


    private SpManager(Context mContext, String filename) {
        this.mContext = mContext.getApplicationContext();
        prefs = mContext.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    public static SpManager getInstance(Context mContext, String filename) {
        if (mPrefManager == null) {
            mPrefManager = new SpManager(mContext, filename);
        }
        return mPrefManager;
    }

    /**
     * 存储String类型的数据`
     *
     * @param key
     * @param vaule
     */
    public void put(String key, Object vaule, String type) {
        Editor editor = null;
        if ((null != key) && (key.length() != 0)) {
            editor = prefs.edit();
        }
        if (vaule != null) {
            switch (type) {
                case "STRING"://String 类型
                    editor.putString(key, (String) vaule);
                    break;
                case "INT"://int 类型
                    editor.putInt(key, (Integer) vaule);
                    break;
                case "BOOLEAN"://Boolean 类型
                    editor.putBoolean(key, (Boolean) vaule);
                    break;
                case "LONG"://Long 类型
                    editor.putLong(key, (Long) vaule);
                    break;
                case "FLOAT"://Float 类型
                    editor.putFloat(key, (Float) vaule);
                    break;
                default:
                    break;
            }

            editor.commit();
        }
    }

    /**
     * 获取String类型对应的值
     *
     * @param key
     * @return
     */
    public Object get(String key, String type) {
        Object obj = null;
        switch (type) {
            case "STRING"://String 类型
                obj = prefs.getString(key, "");
                break;
            case "INT"://int 类型
                obj = prefs.getInt(key, 0);
                break;
            case "BOOLEAN"://Boolean 类型
                obj = prefs.getBoolean(key, false);
                break;
            case "LONG"://Long 类型
                obj = prefs.getLong(key, 0);
                break;
            case "FLOAT"://Float 类型
                obj = prefs.getFloat(key, 0);
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * 移除
     *
     * @param key
     */
    public void remove(String key) {
        if ((null != key) && (key.length() != 0)) {
            Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        }
    }
}

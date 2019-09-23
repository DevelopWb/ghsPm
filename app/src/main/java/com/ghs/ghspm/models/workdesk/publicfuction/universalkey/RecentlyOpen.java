package com.ghs.ghspm.models.workdesk.publicfuction.universalkey;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentlyOpen {

    private final Map<String, String> keyMap;
    private final List<String> mapKey;

    // 私有构造
    private RecentlyOpen() {
        keyMap = new HashMap<>();
        mapKey = new ArrayList<>();
    }

    private static RecentlyOpen single = new RecentlyOpen();

    // 静态工厂方法
    public static RecentlyOpen getInstance() {
        return single;
    }


    public void addCell(String id, String str) {
        boolean b = keyMap.containsKey(id);
        if (!b) {
            keyMap.put(id, str);
        }
        boolean contains = mapKey.contains(id);
        if (!contains) {
            mapKey.add(id);
        }

        Hawk.put("Door", keyMap);
        Hawk.put("Door_KEY", mapKey);

    }


    public String findCell(String id) {

        return keyMap.get(id);
    }


    public int MapLength() {

        return keyMap.size();

    }

    public int ListLenth() {

        return mapKey.size();

    }


}

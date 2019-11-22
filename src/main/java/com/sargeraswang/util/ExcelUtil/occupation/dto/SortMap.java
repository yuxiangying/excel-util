package com.sargeraswang.util.ExcelUtil.occupation.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SortMap<K, V> extends HashMap<K, V> {

    private List<String> keyList = new ArrayList<>();

    public List<String> keyList() {
        return keyList;
    }

    @Override
    public V put(K key, V value) {
        if (key != null && (key instanceof String)) {
            keyList.add((String) key);
        }
        return super.put(key, value);
    }
}

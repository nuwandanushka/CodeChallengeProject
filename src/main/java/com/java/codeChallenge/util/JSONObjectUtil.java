package com.java.codeChallenge.util;

import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JSONObjectUtil {

    public static List<JSONObject> getSortListIfCompareValueIsInt(String key, List<JSONObject> list) {
        Collections.sort(list, getComparatorIfSortedValueInt(key));
        return list;
    }

    public static Comparator<JSONObject> getComparatorIfSortedValueInt(String key) {
        Comparator<JSONObject> comparator = Collections.reverseOrder((o1, o2) -> {
            if (null == o1.get(key)) {
                return (null == o2.get(key)) ? 0 : -1;
            }
            if (null == o2.get(key)) {
                return 1;
            }
            return ((Integer)Integer.parseInt(String.valueOf(o2.get(key)))).compareTo(Integer.parseInt(String.valueOf(o1.get(key))));
        });
        return comparator;
    }

    public static Comparator<JSONObject> getComparatorByKey(String key) {
        Comparator<JSONObject> comparator = Comparator.comparing(o -> String.valueOf(o.get(key)));
        return comparator;
    }
    public static Comparator<JSONObject> getComparatorByKeyIfIntValue(String key) {
        Comparator<JSONObject> comparator = Comparator.comparingInt(o -> Integer.parseInt(String.valueOf(o.get(key))));
        return comparator;
    }

    public static String getStringByObject(Object o) {
        StringBuilder builder = new StringBuilder();
        if(null == o) return "";
        if(o.getClass().isArray()) {
            for (String a : (String[]) o)
                builder.append(builder.toString().isEmpty() ? a : ", " + a);
        }
        return String.valueOf(o);
    }
}

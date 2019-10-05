package com.java.codeChallenge.framework.service;

import com.java.codeChallenge.storage.JsonObjectStorage;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseJSONObjectService {

    private JsonObjectStorage jsonObjectStorage;

    public BaseJSONObjectService(){
        jsonObjectStorage = JsonObjectStorage.getInstance();
    }

    protected List<JSONObject> getBinarySearchMultipleMatches(List<JSONObject> sortedObjectList, JSONObject searchingObject, String searchKey, Comparator<JSONObject> comparator){
        List<JSONObject> sortedList = sortedObjectList;
        List<JSONObject> values = new ArrayList<>();
        int index = Collections.binarySearch (sortedList, searchingObject, comparator);
        int first = index;
        int last = index;
        if (index >= 0) {
            values.add(sortedList.get(index));
            while (first > 0 && sortedList.get(first - 1).get(searchKey) == searchingObject.get(searchKey)) {
                values.add(sortedList.get(first - 1));
                first--;
            }
            while (last < sortedList.size() - 1 && String.valueOf(sortedList.get(last + 1).get(searchKey))
                    .equals(String.valueOf(searchingObject.get(searchKey)))) {
                values.add(sortedList.get(last + 1));
                last++;
            }
            return values;
        }
        return null;
    }

    protected JSONObject getBinarySearchMatch(List<JSONObject> sortedObjectList, JSONObject searchingObject, Comparator<JSONObject> comparator){
        int index = Collections.binarySearch(sortedObjectList
                , searchingObject, comparator);
        return (index >= 0) ? sortedObjectList.get(index) : null;
    }

    protected String getStringByListAndKey(String key , List<JSONObject> objects){
        StringBuilder builder = new StringBuilder();
        for(JSONObject obj: objects)
            builder.append(builder.toString().isEmpty()? obj.get(key) : ", "+obj.get(key));

        return builder.toString();
    }

    public JsonObjectStorage getJsonObjectStorage() {
        return jsonObjectStorage;
    }

    public void setJsonObjectStorage(JsonObjectStorage jsonObjectStorage) {
        this.jsonObjectStorage = jsonObjectStorage;
    }
}

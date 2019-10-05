package com.java.codeChallenge.storage;


import com.java.codeChallenge.util.FileUtil;
import com.java.codeChallenge.util.JSONObjectUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JsonObjectStorage {

    private static JsonObjectStorage instance = null;
    private List<JSONObject> userList;
    private List<JSONObject> ticketList;
    private List<JSONObject> organizationList;

    public void initializeObjects(){
        userList = getJsonObjectListByFileName("users.json", JSONObjectUtil.getComparatorByKeyIfIntValue("_id"));
        ticketList = getJsonObjectListByFileName("tickets.json", JSONObjectUtil.getComparatorByKey("_id"));
        organizationList = getJsonObjectListByFileName("organizations.json", JSONObjectUtil.getComparatorByKeyIfIntValue("_id"));
    }

    public static JsonObjectStorage getInstance(){
        if (instance == null) {
            instance = new JsonObjectStorage();
        }
        return instance;
    }
    private JsonObjectStorage (){
        initializeObjects();
    }

    private static List<JSONObject> getJsonObjectListByFileName(String fileName, Comparator<JSONObject> comparator) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(new FileUtil().getFileFromResources(fileName))) {
            Object obj = jsonParser.parse(reader);
            JSONArray objectList = (JSONArray) obj;
            objectList.sort(comparator);
            return objectList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<JSONObject> getUserList() {
        return userList;
    }

    public List<JSONObject> getTicketList() {
        return ticketList;
    }

    public List<JSONObject> getOrganizationList() {
        return organizationList;
    }
}

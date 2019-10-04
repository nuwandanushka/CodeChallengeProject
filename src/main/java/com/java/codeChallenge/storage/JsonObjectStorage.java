package com.java.codeChallenge.storage;


import com.java.codeChallenge.util.FileUtil;
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

    private static List<JSONObject> userList;
    private static List<JSONObject> ticketList;
    private static List<JSONObject> organizationList;

    public static void initializeObjects(){
        userList = getJsonArrayByFileName("users.json", getComparatorByKey("_id"));
        ticketList = getJsonArrayByFileName("tickets.json", getComparatorByKey("_id"));
        organizationList = getJsonArrayByFileName("organizations.json", getComparatorByKey("_id"));
    }

    private static List<JSONObject> getJsonArrayByFileName(String fileName, Comparator<JSONObject> comparator) {
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


    public static Comparator<JSONObject> getComparatorByKey(String key) {
        Comparator<JSONObject> comparator = Comparator.comparing(o -> String.valueOf(o.get(key)));
        return comparator;
    }
    public static void sortUserListBySortingKey(String key) {
        Collections.sort(userList, Comparator.comparing(o -> String.valueOf(o.get(key))));
    }

    public static void sortTicketListBySortingKey(String key) {
        Collections.sort(ticketList, Comparator.comparing(o -> String.valueOf(o.get(key))));
    }

    public static void sortOrganizationListBySortingKey(String key) {
        Collections.sort(organizationList, Comparator.comparing(o -> String.valueOf(o.get(key))));
    }

    public static List<JSONObject> getUserList() {
        return userList;
    }

    public static List<JSONObject> getTicketList() {
        return ticketList;
    }

    public static List<JSONObject> getOrganizationList() {
        return organizationList;
    }
}

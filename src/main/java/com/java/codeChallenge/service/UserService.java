package com.java.codeChallenge.service;

import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;

public interface UserService {
    Set<JSONObject> fetchUsersByCriteria(Map<String, String> searchValues);

    void displayResultsByCriteria(Map<String, String> searchValues);
}

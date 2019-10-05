package com.java.codeChallenge.validation;

import com.java.codeChallenge.enums.ErrorCodeEnum;
import com.java.codeChallenge.exception.CodeChallengeException;
import org.json.simple.JSONObject;

import java.util.Map;

public interface ConsoleValidation {

    default boolean isJsonObjectContains(JSONObject model, Map<String, String> searchValues) {
        int i = 0;
        String key = null;
        for(Map.Entry<String,String> entry : searchValues.entrySet()){
            if(entry.getValue().equalsIgnoreCase(String.valueOf(model.get(entry.getKey())))){
                i++;
                continue;
            }else
                break;
        }
        if(i == searchValues.size())
            return true;
        return false;
    }
}

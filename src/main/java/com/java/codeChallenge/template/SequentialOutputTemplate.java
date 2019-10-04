package com.java.codeChallenge.template;

import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SequentialOutputTemplate implements OutputTemplate {

    @Override
    public void createOutput(Collection<? extends Map> valueMap) {
        Set<JSONObject> records = (Set<JSONObject>) valueMap;
        records.forEach(i-> {
            for(Object entry : i.entrySet()){
                System.out.println(String.valueOf(entry).replace("=",": "));
            }
            System.out.println("==========================================================");
        });

    }
}

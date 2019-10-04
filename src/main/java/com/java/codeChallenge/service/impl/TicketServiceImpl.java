package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.service.TicketService;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.SequentialOutputTemplate;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;

public class TicketServiceImpl implements TicketService, ConsoleOutputTemplate<SequentialOutputTemplate> {
    @Override
    public SequentialOutputTemplate getTemplate() {
        return new SequentialOutputTemplate();
    }


    @Override
    public Set<JSONObject> fetchUsersByCriteria(Map<String, String> searchValues) {
        return null;
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues) {

    }
}

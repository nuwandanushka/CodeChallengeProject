package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.service.UserService;
import com.java.codeChallenge.storage.JsonObjectStorage;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

public class UserServiceImpl implements ConsoleOutputTemplate, UserService, ConsoleValidation {

    @Override
    public Set<JSONObject> fetchUsersByCriteria(Map<String, String> searchValues) {
        Set<JSONObject> usersByCriteria = new HashSet<>();
        Optional.ofNullable(searchValues).ifPresent(i ->{
                JsonObjectStorage.getUserList().stream()
                        .filter(user -> isJsonObjectContains(user, searchValues))
                        .forEach(filteredUser ->{
                            setOrganizationNameByOrgId(filteredUser);
                            setSubmittedSubjectByUserId(filteredUser);
                            setAssigneeSubjectByUserId(filteredUser);
                            usersByCriteria.add(filteredUser);
                });
        });
       return usersByCriteria;
    }

    private void setAssigneeSubjectByUserId(JSONObject filteredUser) {
        JsonObjectStorage.sortTicketListBySortingKey("assignee_id");
        JSONObject orgId = new JSONObject();
        orgId.put("assignee_id", filteredUser.get("_id"));
        int ticketIndex = Collections.binarySearch(JsonObjectStorage.getTicketList()
                , orgId, JsonObjectStorage.getComparatorByKey("assignee_id"));
        if(0 <= ticketIndex)
            filteredUser.put("assignee_ticket_subject"
                    ,JsonObjectStorage.getTicketList().get(ticketIndex).get("subject"));
    }

    private void setSubmittedSubjectByUserId(JSONObject filteredUser) {
        JsonObjectStorage.sortTicketListBySortingKey("submitter_id");
        JSONObject orgId = new JSONObject();
        orgId.put("submitter_id", filteredUser.get("_id"));
        int ticketIndex = Collections.binarySearch(JsonObjectStorage.getTicketList()
                , orgId, JsonObjectStorage.getComparatorByKey("submitter_id"));
        if(0 <= ticketIndex)
            filteredUser.put("submitted_ticket_subject"
                    ,JsonObjectStorage.getTicketList().get(ticketIndex).get("subject"));
    }

    private void setOrganizationNameByOrgId(JSONObject filteredUser) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredUser.get("organization_id"));
        int orgIdIndex = Collections.binarySearch(JsonObjectStorage.getOrganizationList()
                , orgId, JsonObjectStorage.getComparatorByKey("_id"));
        if(0 <= orgIdIndex)
            filteredUser.put("organization_name"
                    ,JsonObjectStorage.getOrganizationList().get(orgIdIndex).get("name"));
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues){
        displayResults(fetchUsersByCriteria(searchValues));
    }


    @Override
    public OutputTemplate getTemplate() {
        return new OutputTemplate() {
            @Override
            public void createOutput(Collection<? extends Map> valueMap) {
                valueMap.stream().forEach(i -> {
                    Arrays.asList(JSONObjectEnum.User.values()).forEach(j -> {
                        System.out.println(j.value+": "+i.get(j.key));
                    });
                    System.out.println("======================================================");
                });
            }
        };
    }
}

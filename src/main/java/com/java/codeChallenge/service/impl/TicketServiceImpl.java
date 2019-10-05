package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.service.TicketService;
import com.java.codeChallenge.storage.JsonObjectStorage;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

public class TicketServiceImpl implements TicketService, ConsoleOutputTemplate, ConsoleValidation {



    @Override
    public Set<JSONObject> fetchTicketsByCriteria(Map<String, String> searchValues) {
        Set<JSONObject> ticketByCriteria = new HashSet<>();
        Optional.ofNullable(searchValues).ifPresent(i ->{
            JsonObjectStorage.getTicketList().stream()
                    .filter(ticket -> isJsonObjectContains(ticket, searchValues))
                    .forEach(filteredTicket ->{
                        setOrganizationNameByTicket(filteredTicket);
                        setSubmitterNameByTicket(filteredTicket);
                        setAssigneeNameByTicket(filteredTicket);
                        ticketByCriteria.add(filteredTicket);
                    });
        });
        return ticketByCriteria;

    }

    private void setAssigneeNameByTicket(JSONObject filteredTicket) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredTicket.get("assignee_id"));
        int orgIdIndex = Collections.binarySearch(JsonObjectStorage.getUserList()
                , orgId, JsonObjectStorage.getComparatorByKey("_id"));
        filteredTicket.put("assignee_name"
                ,(0 <= orgIdIndex) ? JsonObjectStorage.getUserList().get(orgIdIndex).get("name"):"");

    }

    private void setSubmitterNameByTicket(JSONObject filteredTicket) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredTicket.get("submitter_id"));
        int orgIdIndex = Collections.binarySearch(JsonObjectStorage.getUserList()
                , orgId, JsonObjectStorage.getComparatorByKey("_id"));
            filteredTicket.put("submitter_name"
                    ,(0 <= orgIdIndex) ? JsonObjectStorage.getUserList().get(orgIdIndex).get("name"):"");
    }

    private void setOrganizationNameByTicket(JSONObject filteredTicket) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredTicket.get("organization_id"));
        int orgIdIndex = Collections.binarySearch(JsonObjectStorage.getOrganizationList()
                , orgId, JsonObjectStorage.getComparatorByKey("_id"));
            filteredTicket.put("organization_name"
                    ,(0 <= orgIdIndex) ? JsonObjectStorage.getOrganizationList().get(orgIdIndex).get("name"):"");
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues) {
        displayResults(fetchTicketsByCriteria(searchValues));
    }

    @Override
    public OutputTemplate getTemplate() {
        return new OutputTemplate() {
            @Override
            public void createOutput(Collection<? extends Map> valueMap) {
                valueMap.stream().forEach(i -> {
                    Arrays.asList(JSONObjectEnum.Ticket.values()).forEach(j -> {
                        System.out.println(j.value+": "+i.get(j.key));
                    });
                    System.out.println("======================================================");
                });
            }
        };
    }
}

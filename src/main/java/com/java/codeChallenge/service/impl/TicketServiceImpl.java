package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.TicketService;
import com.java.codeChallenge.storage.JsonObjectStorage;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.util.JSONObjectUtil;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

public class TicketServiceImpl extends BaseJSONObjectService implements TicketService, ConsoleOutputTemplate, ConsoleValidation {



    @Override
    public Set<JSONObject> fetchTicketsByCriteria(Map<String, String> searchValues) {
        Set<JSONObject> ticketByCriteria = new HashSet<>();
        Optional.ofNullable(searchValues).ifPresent(i ->{
            getJsonObjectStorage().getTicketList().stream()
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
        JSONObject assigneeId = new JSONObject();
        assigneeId.put("_id", filteredTicket.get("assignee_id"));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getUserList(),assigneeId
                , JSONObjectUtil.getComparatorByKeyIfIntValue("_id"))).ifPresent(i-> filteredTicket.put("assignee_name", i.get("name")));

    }

    private void setSubmitterNameByTicket(JSONObject filteredTicket) {
        JSONObject submitterId = new JSONObject();
        submitterId.put("_id", filteredTicket.get("submitter_id"));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getUserList(),submitterId
                , JSONObjectUtil.getComparatorByKeyIfIntValue("_id"))).ifPresent(i-> filteredTicket.put("submitter_name", i.get("name")));
    }

    private void setOrganizationNameByTicket(JSONObject filteredTicket) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredTicket.get("organization_id"));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getOrganizationList(),orgId
                , JSONObjectUtil.getComparatorByKeyIfIntValue("_id"))).ifPresent(i-> filteredTicket.put("organization_name", i.get("name")));
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues) {
        displayResults(fetchTicketsByCriteria(searchValues));
    }

    @Override
    public OutputTemplate getTemplate() {
        return valueMap -> valueMap.stream().forEach(i -> {
            Arrays.asList(JSONObjectEnum.Ticket.values()).forEach(j -> {
                System.out.println(j.value+": "+JSONObjectUtil.getStringByObject(i.get(j.key)));
            });
            System.out.println("======================================================");
        });
    }
}

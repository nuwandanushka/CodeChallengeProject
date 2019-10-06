package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.TicketService;
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
            getJsonObjectStorage().getTicketList()
                    .stream()
                    .filter(ticket -> isJsonObjectContains(ticket, searchValues))
                    .forEach(filteredTicket ->{
                        setOrganizationNameByTicket(filteredTicket);
                        setAssigneeNameByTicket(filteredTicket);
                        setSubmitterNameByTicket(filteredTicket);
                        ticketByCriteria.add(filteredTicket);
                    });
        });
        return ticketByCriteria;

    }

    private void setAssigneeNameByTicket(JSONObject filteredTicket) {
        JSONObject assigneeId = new JSONObject();
        assigneeId.put(JSONObjectEnum.User.ID.key, filteredTicket.get(JSONObjectEnum.Ticket.ASSIGNEE_ID.key));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getUserList(),assigneeId
                , JSONObjectUtil.getComparatorByKeyIfIntValue(JSONObjectEnum.User.ID.key)))
                .ifPresent(i-> filteredTicket.put(JSONObjectEnum.Ticket.ASSIGNEE_NAME.key, i.get(JSONObjectEnum.User.NAME.key)));

    }

    private void setSubmitterNameByTicket(JSONObject filteredTicket) {
        JSONObject submitterId = new JSONObject();
        submitterId.put(JSONObjectEnum.User.ID.key, filteredTicket.get(JSONObjectEnum.Ticket.SUBMITTER_ID.key));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getUserList(),submitterId
                , JSONObjectUtil.getComparatorByKeyIfIntValue(JSONObjectEnum.User.ID.key)))
                .ifPresent(i-> filteredTicket.put(JSONObjectEnum.Ticket.SUBMITTER_NAME.key, i.get(JSONObjectEnum.User.NAME.key)));
    }

    private void setOrganizationNameByTicket(JSONObject filteredTicket) {
        JSONObject orgId = new JSONObject();
        orgId.put(JSONObjectEnum.Organization.ID.key, filteredTicket.get(JSONObjectEnum.Ticket.ORGANIZATION_ID.key));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getOrganizationList(),orgId
                , JSONObjectUtil.getComparatorByKeyIfIntValue(JSONObjectEnum.Organization.ID.key)))
                .ifPresent(i-> filteredTicket.put(JSONObjectEnum.Ticket.ORGANIZATION_NAME.key, i.get(JSONObjectEnum.Organization.NAME.key)));
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

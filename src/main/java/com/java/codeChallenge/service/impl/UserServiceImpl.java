package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.UserService;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.util.JSONObjectUtil;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

public class UserServiceImpl extends BaseJSONObjectService implements ConsoleOutputTemplate, UserService, ConsoleValidation {

    @Override
    public Set<JSONObject> fetchUsersByCriteria(Map<String, String> searchValues) {
        Set<JSONObject> usersByCriteria = new HashSet<>();
        Optional.ofNullable(searchValues).ifPresent(i ->{
                getJsonObjectStorage().getUserList()
                        .stream()
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
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt(JSONObjectEnum.Ticket.ASSIGNEE_ID.key, getJsonObjectStorage().getTicketList());
        JSONObject assigneeId = new JSONObject();
        assigneeId.put(JSONObjectEnum.Ticket.ASSIGNEE_ID.key, filteredUser.get(JSONObjectEnum.User.ID.key));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, assigneeId,JSONObjectEnum.Ticket.ASSIGNEE_ID.key
                ,JSONObjectUtil.getComparatorIfSortedValueInt(JSONObjectEnum.Ticket.ASSIGNEE_ID.key)))
                .ifPresent(i -> filteredUser.put(JSONObjectEnum.User.ASSIGNEE_NAME.key, getStringByListAndKey(JSONObjectEnum.Ticket.SUBJECT.key,i)));
    }

    private void setSubmittedSubjectByUserId(JSONObject filteredUser) {
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt(JSONObjectEnum.Ticket.SUBMITTER_ID.key, getJsonObjectStorage().getTicketList());
        JSONObject submitterId = new JSONObject();
        submitterId.put(JSONObjectEnum.Ticket.SUBMITTER_ID.key, filteredUser.get(JSONObjectEnum.User.ID.key));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, submitterId,JSONObjectEnum.Ticket.SUBMITTER_ID.key
                ,JSONObjectUtil.getComparatorIfSortedValueInt(JSONObjectEnum.Ticket.SUBMITTER_ID.key)))
                .ifPresent(i -> filteredUser.put(JSONObjectEnum.User.SUBMITTER_NAME.key, getStringByListAndKey(JSONObjectEnum.Ticket.SUBJECT.key,i)));
    }

    private void setOrganizationNameByOrgId(JSONObject filteredUser) {
        JSONObject orgId = new JSONObject();
        orgId.put(JSONObjectEnum.Organization.ID.key, filteredUser.get(JSONObjectEnum.User.ORGANIZATION_ID.key));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getOrganizationList(),orgId, JSONObjectUtil.getComparatorIfSortedValueInt(JSONObjectEnum.Organization.ID.key)))
                .ifPresent(i -> filteredUser.put(JSONObjectEnum.User.ORGANIZATION_NAME.key, i.get(JSONObjectEnum.Organization.NAME.key)));
       }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues){
        displayResults(fetchUsersByCriteria(searchValues));
    }


    @Override
    public OutputTemplate getTemplate() {
        return valueMap -> valueMap.stream().forEach(i -> {
            Arrays.asList(JSONObjectEnum.User.values()).forEach(j -> {
                System.out.println(j.value+": "+JSONObjectUtil.getStringByObject(i.get(j.key)));
            });
            System.out.println("======================================================");
        });
    }
}

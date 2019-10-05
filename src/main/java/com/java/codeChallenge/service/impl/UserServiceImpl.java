package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.UserService;
import com.java.codeChallenge.storage.JsonObjectStorage;
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
                getJsonObjectStorage().getUserList().stream()
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
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt("assignee_id", getJsonObjectStorage().getTicketList());
        JSONObject assigneeId = new JSONObject();
        assigneeId.put("assignee_id", filteredUser.get("_id"));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, assigneeId,"assignee_id"
                ,JSONObjectUtil.getComparatorIfSortedValueInt("assignee_id")))
                .ifPresent(i -> filteredUser.put("assignee_ticket_subject", getStringByListAndKey("subject",i)));
    }

    private void setSubmittedSubjectByUserId(JSONObject filteredUser) {
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt("submitter_id", getJsonObjectStorage().getTicketList());
        JSONObject submitterId = new JSONObject();
        submitterId.put("submitter_id", filteredUser.get("_id"));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, submitterId,"submitter_id"
                ,JSONObjectUtil.getComparatorIfSortedValueInt("submitter_id")))
                .ifPresent(i -> filteredUser.put("submitted_ticket_subject", getStringByListAndKey("subject",i)));
    }

    private void setOrganizationNameByOrgId(JSONObject filteredUser) {
        JSONObject orgId = new JSONObject();
        orgId.put("_id", filteredUser.get("organization_id"));
        Optional.ofNullable(getBinarySearchMatch(getJsonObjectStorage().getOrganizationList(),orgId, JSONObjectUtil.getComparatorIfSortedValueInt("_id")))
                .ifPresent(i -> filteredUser.put("organization_name", i.get("name")));
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

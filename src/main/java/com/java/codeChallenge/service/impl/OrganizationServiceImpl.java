package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.service.OrganizationService;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.storage.JsonObjectStorage;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.util.JSONObjectUtil;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

public class OrganizationServiceImpl extends BaseJSONObjectService implements OrganizationService, ConsoleOutputTemplate, ConsoleValidation {

    @Override
    public OutputTemplate getTemplate() {
        return valueMap -> valueMap.stream().forEach(i -> {
            Arrays.asList(JSONObjectEnum.Organization.values()).forEach(j -> {
                System.out.println(j.value+": "+JSONObjectUtil.getStringByObject(i.get(j.key)));
            });
            System.out.println("======================================================");
        });
    }

    @Override
    public Set<JSONObject> fetchOrganizationByCriteria(Map<String, String> searchValues) {
        Set<JSONObject> usersByCriteria = new HashSet<>();
        Optional.ofNullable(searchValues).ifPresent(i ->{
            getJsonObjectStorage().getOrganizationList().stream()
                    .filter(user -> isJsonObjectContains(user, searchValues))
                    .forEach(filteredUser ->{
                        setTicketSubjectByOrgId(filteredUser);
                        setUserNameByOrgId(filteredUser);
                        usersByCriteria.add(filteredUser);
                    });
        });
        return usersByCriteria;
    }

    private void setTicketSubjectByOrgId(JSONObject filteredUser) {
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt("organization_id", getJsonObjectStorage().getTicketList());
        JSONObject orgId = new JSONObject();
        orgId.put("organization_id", filteredUser.get("_id"));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, orgId,"organization_id"
                ,JSONObjectUtil.getComparatorIfSortedValueInt("organization_id")))
                .ifPresent(i -> filteredUser.put("ticket_subject", getStringByListAndKey("subject",i)));
    }

    private void setUserNameByOrgId(JSONObject filteredUser) {
        List<JSONObject> sortedUserList = JSONObjectUtil.getSortListIfCompareValueIsInt("organization_id", getJsonObjectStorage().getUserList());
        JSONObject orgId = new JSONObject();
        orgId.put("organization_id", filteredUser.get("_id"));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedUserList, orgId,"organization_id"
                ,JSONObjectUtil.getComparatorIfSortedValueInt("organization_id")))
                .ifPresent(i -> filteredUser.put("user_name", getStringByListAndKey("name",i)));
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues) {
        displayResults(fetchOrganizationByCriteria(searchValues));
    }
}

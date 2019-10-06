package com.java.codeChallenge.service.impl;

import com.java.codeChallenge.enums.JSONObjectEnum;
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.OrganizationService;
import com.java.codeChallenge.template.ConsoleOutputTemplate;
import com.java.codeChallenge.template.OutputTemplate;
import com.java.codeChallenge.util.JSONObjectUtil;
import com.java.codeChallenge.validation.ConsoleValidation;
import org.json.simple.JSONObject;

import java.util.*;

import static com.java.codeChallenge.enums.JSONObjectEnum.Ticket.ORGANIZATION_ID;

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
            getJsonObjectStorage().getOrganizationList()
                    .stream()
                    .filter(organization -> isJsonObjectContains(organization, searchValues))
                    .forEach(filteredOrganization ->{
                        setTicketSubjectByOrgId(filteredOrganization);
                        setUserNameByOrgId(filteredOrganization);
                        usersByCriteria.add(filteredOrganization);
                    });
        });
        return usersByCriteria;
    }

    private void setTicketSubjectByOrgId(JSONObject filteredOrganization) {
        List<JSONObject> sortedTicketList = JSONObjectUtil.getSortListIfCompareValueIsInt(ORGANIZATION_ID.key, getJsonObjectStorage().getTicketList());
        JSONObject orgId = new JSONObject();
        orgId.put(ORGANIZATION_ID.key, filteredOrganization.get(JSONObjectEnum.Organization.ID.key));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedTicketList, orgId,ORGANIZATION_ID.key
                ,JSONObjectUtil.getComparatorIfSortedValueInt(ORGANIZATION_ID.key)))
                .ifPresent(i -> filteredOrganization.put(JSONObjectEnum.Organization.TICKET_SUBJECT.key, getStringByListAndKey(JSONObjectEnum.Ticket.SUBJECT.key,i)));
    }

    private void setUserNameByOrgId(JSONObject filteredOrganization) {
        List<JSONObject> sortedUserList = JSONObjectUtil.getSortListIfCompareValueIsInt(ORGANIZATION_ID.key, getJsonObjectStorage().getUserList());
        JSONObject orgId = new JSONObject();
        orgId.put(ORGANIZATION_ID.key, filteredOrganization.get(JSONObjectEnum.Organization.ID.key));
        Optional.ofNullable(getBinarySearchMultipleMatches(sortedUserList, orgId,ORGANIZATION_ID.key
                ,JSONObjectUtil.getComparatorIfSortedValueInt(ORGANIZATION_ID.key)))
                .ifPresent(i -> filteredOrganization.put(JSONObjectEnum.Organization.USER_NAME.key, getStringByListAndKey(JSONObjectEnum.User.NAME.key,i)));
    }

    @Override
    public void displayResultsByCriteria(Map<String, String> searchValues) {
        displayResults(fetchOrganizationByCriteria(searchValues));
    }
}

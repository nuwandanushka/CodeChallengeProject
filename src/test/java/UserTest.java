
import com.java.codeChallenge.framework.service.BaseJSONObjectService;
import com.java.codeChallenge.service.impl.UserServiceImpl;
import com.java.codeChallenge.storage.JsonObjectStorage;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {


    @Test
    public void testUserById(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",2);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",3);

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonObject1);
        userList.add(jsonObject2);

        when(storage.getUserList()).thenReturn(userList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","2");


        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals(1, jsonObjects.size());


    }

    @Test
    public void testUserByName(){
        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",2);
        jsonObject1.put("name","Nuwan");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",3);
        jsonObject2.put("name","danushka");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonObject1);
        userList.add(jsonObject2);

        when(storage.getUserList()).thenReturn(userList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("name","Nuwan");

        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals(1, jsonObjects.size());

        Map<String, String> criteria2 = new HashMap<>();
        criteria2.put("name","sss");

        Set<JSONObject> jsonObjects2 = userService.fetchUsersByCriteria(criteria2);
        Assert.assertEquals(0, jsonObjects2.size());
    }

    @Test
    public void testUserWithOrganizationName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",2);
        jsonUser1.put("organization_id",15);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",3);
        jsonUser2.put("organization_id",16);

        JSONObject jsonOrganization1 = new JSONObject();
        jsonOrganization1.put("_id",15);
        jsonOrganization1.put("name","Rasmussen");

        JSONObject jsonOrganization2 = new JSONObject();
        jsonOrganization2.put("_id",3);
        jsonOrganization2.put("name","Barlow");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonOrganization1);
        orgList.add(jsonOrganization2);

        when(storage.getUserList()).thenReturn(userList);

        when(storage.getOrganizationList()).thenReturn(orgList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","2");

        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals("Rasmussen", jsonObjects.iterator().next().get("organization_name"));


    }

    @Test
    public void testUserWithSubmittedSubject(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",9);
        jsonUser1.put("organization_id",15);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",3);
        jsonUser2.put("organization_id",16);

        JSONObject jsonTicket1 = new JSONObject();
        jsonTicket1.put("submitter_id",9);
        jsonTicket1.put("assignee_id",12);
        jsonTicket1.put("subject","A Catastrophe in Hungary");

        JSONObject jsonTicket2 = new JSONObject();
        jsonTicket2.put("submitter_id",65);
        jsonTicket2.put("assignee_id",13);
        jsonTicket2.put("subject","A Problem in Morocco");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonTicket1);
        orgList.add(jsonTicket2);

        when(storage.getUserList()).thenReturn(userList);

        when(storage.getTicketList()).thenReturn(orgList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","9");

        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals("A Catastrophe in Hungary", jsonObjects.iterator().next().get("submitted_ticket_subject"));

    }

    @Test
    public void testUserWithAssigneeSubject(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",9);
        jsonUser1.put("organization_id",15);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",12);
        jsonUser2.put("organization_id",16);

        JSONObject jsonTicket1 = new JSONObject();
        jsonTicket1.put("submitter_id",15);
        jsonTicket1.put("assignee_id",9);
        jsonTicket1.put("subject","A Catastrophe in Hungary");

        JSONObject jsonTicket2 = new JSONObject();
        jsonTicket2.put("submitter_id",9);
        jsonTicket2.put("assignee_id",13);
        jsonTicket2.put("subject","A Problem in Morocco");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonTicket1);
        ticketList.add(jsonTicket2);

        when(storage.getUserList()).thenReturn(userList);

        when(storage.getTicketList()).thenReturn(ticketList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","9");

        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals("A Problem in Morocco", jsonObjects.iterator().next().get("submitted_ticket_subject"));

        Assert.assertEquals("A Catastrophe in Hungary", jsonObjects.iterator().next().get("assignee_ticket_subject"));

    }

    @Test
    public void testUserWithIntegratingTicketAndOrganization(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",9);
        jsonUser1.put("organization_id",15);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",12);
        jsonUser2.put("organization_id",16);

        JSONObject jsonTicket1 = new JSONObject();
        jsonTicket1.put("submitter_id",9);
        jsonTicket1.put("assignee_id",12);
        jsonTicket1.put("subject","A Catastrophe in Hungary");

        JSONObject jsonTicket2 = new JSONObject();
        jsonTicket2.put("submitter_id",12);
        jsonTicket2.put("assignee_id",13);
        jsonTicket2.put("subject","A Problem in Morocco");

        JSONObject jsonOrganization1 = new JSONObject();
        jsonOrganization1.put("_id",15);
        jsonOrganization1.put("name","Rasmussen");

        JSONObject jsonOrganization2 = new JSONObject();
        jsonOrganization2.put("_id",16);
        jsonOrganization2.put("name","Barlow");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonTicket1);
        ticketList.add(jsonTicket2);

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonOrganization1);
        orgList.add(jsonOrganization2);

        when(storage.getOrganizationList()).thenReturn(orgList);

        when(storage.getUserList()).thenReturn(userList);

        when(storage.getTicketList()).thenReturn(ticketList);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","12");

        Set<JSONObject> jsonObjects = userService.fetchUsersByCriteria(criteria);

        Assert.assertEquals("A Problem in Morocco", jsonObjects.iterator().next().get("submitted_ticket_subject"));

        Assert.assertEquals("A Catastrophe in Hungary", jsonObjects.iterator().next().get("assignee_ticket_subject"));

        Assert.assertEquals("Barlow", jsonObjects.iterator().next().get("organization_name"));

    }
}

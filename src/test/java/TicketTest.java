import com.java.codeChallenge.service.impl.TicketServiceImpl;
import com.java.codeChallenge.service.impl.UserServiceImpl;
import com.java.codeChallenge.storage.JsonObjectStorage;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketTest {

    @Test
    public void testTicketById(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        when(storage.getTicketList()).thenReturn(ticketList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals(1, jsonObjects.size());
        Assert.assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", jsonObjects.iterator().next().get("_id"));


    }

    @Test
    public void testTicketBySubject(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        jsonObject1.put("subject","A Catastrophe in Korea (North)");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        jsonObject2.put("subject","A Catastrophe in Korea (North)");

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        when(storage.getTicketList()).thenReturn(ticketList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("subject","A Catastrophe in Korea (North)");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals(2, jsonObjects.size());


    }

    @Test
    public void testTicketBySubmitterName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        jsonObject1.put("subject","A Catastrophe in Korea (North)");
        jsonObject1.put("organization_id",116);
        jsonObject1.put("submitter_id",112);
        jsonObject1.put("assignee_id",22);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        jsonObject2.put("subject","A Catastrophe in Micronesia");
        jsonObject2.put("organization_id",112);
        jsonObject2.put("submitter_id",4);
        jsonObject2.put("assignee_id",23);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",112);
        jsonUser1.put("name","Francisca Rasmussen");

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",23);
        jsonUser2.put("name","Cross Barlow");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        when(storage.getTicketList()).thenReturn(ticketList);

        when(storage.getUserList()).thenReturn(userList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals("Francisca Rasmussen", jsonObjects.iterator().next().get("submitter_name"));


    }

    @Test
    public void testTicketByOrganizationName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        jsonObject1.put("subject","A Catastrophe in Korea (North)");
        jsonObject1.put("organization_id",116);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        jsonObject2.put("subject","A Catastrophe in Micronesia");
        jsonObject2.put("organization_id",112);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        JSONObject jsonOrg1 = new JSONObject();
        jsonOrg1.put("_id",112);
        jsonOrg1.put("name","Enthaze");

        JSONObject jsonOrg2 = new JSONObject();
        jsonOrg2.put("_id",116);
        jsonOrg2.put("name","Nutralab");

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonOrg1);
        orgList.add(jsonOrg2);

        when(storage.getTicketList()).thenReturn(ticketList);

        when(storage.getOrganizationList()).thenReturn(orgList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals("Nutralab", jsonObjects.iterator().next().get("organization_name"));


    }

    @Test
    public void testTicketByAssigneeName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        jsonObject1.put("subject","A Catastrophe in Korea (North)");
        jsonObject1.put("organization_id",116);
        jsonObject1.put("submitter_id",66);
        jsonObject1.put("assignee_id",44);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        jsonObject2.put("subject","A Catastrophe in Micronesia");
        jsonObject2.put("organization_id",112);
        jsonObject2.put("submitter_id",21);
        jsonObject2.put("assignee_id",22);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",22);
        jsonUser1.put("name","Ingrid Wagner");

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",44);
        jsonUser2.put("name","Cross Barlow");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        when(storage.getTicketList()).thenReturn(ticketList);

        when(storage.getUserList()).thenReturn(userList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals("Cross Barlow", jsonObjects.iterator().next().get("assignee_name"));
        Assert.assertNull(jsonObjects.iterator().next().get("submitter_name"));

    }

    @Test
    public void testTicketWithIntegratingOrganizationAndUser(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        jsonObject1.put("subject","A Catastrophe in Korea (North)");
        jsonObject1.put("organization_id",116);
        jsonObject1.put("submitter_id",66);
        jsonObject1.put("assignee_id",44);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        jsonObject2.put("subject","A Catastrophe in Micronesia");
        jsonObject2.put("organization_id",112);
        jsonObject2.put("submitter_id",21);
        jsonObject2.put("assignee_id",22);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",66);
        jsonUser1.put("name","Ingrid Wagner");

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",44);
        jsonUser2.put("name","Cross Barlow");

        JSONObject jsonOrganization1 = new JSONObject();
        jsonOrganization1.put("_id",116);
        jsonOrganization1.put("name","Rasmussen");

        JSONObject jsonOrganization2 = new JSONObject();
        jsonOrganization2.put("_id",16);
        jsonOrganization2.put("name","Barlow");

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser2);
        userList.add(jsonUser1);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(jsonObject1);
        ticketList.add(jsonObject2);

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonOrganization1);
        orgList.add(jsonOrganization2);

        when(storage.getOrganizationList()).thenReturn(orgList);

        when(storage.getUserList()).thenReturn(userList);

        when(storage.getTicketList()).thenReturn(ticketList);

        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");

        Set<JSONObject> jsonObjects = ticketService.fetchTicketsByCriteria(criteria);

        Assert.assertEquals("Cross Barlow", jsonObjects.iterator().next().get("assignee_name"));

        Assert.assertEquals("Ingrid Wagner", jsonObjects.iterator().next().get("submitter_name"));

        Assert.assertEquals("Rasmussen", jsonObjects.iterator().next().get("organization_name"));

    }


}

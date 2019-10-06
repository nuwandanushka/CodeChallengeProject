import com.java.codeChallenge.service.impl.OrganizationServiceImpl;
import com.java.codeChallenge.storage.JsonObjectStorage;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrganizationTest {

    @Test
    public void testOrganizationById(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",101);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",102);

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonObject1);
        orgList.add(jsonObject2);

        when(storage.getOrganizationList()).thenReturn(orgList);

        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","101");

        Set<JSONObject> jsonObjects = organizationService.fetchOrganizationByCriteria(criteria);

        Assert.assertEquals(1, jsonObjects.size());



    }

    @Test
    public void testOrganizationByName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",101);
        jsonObject1.put("name","Enthaze");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",102);
        jsonObject2.put("name","MegaCorp");

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonObject1);
        orgList.add(jsonObject2);

        when(storage.getOrganizationList()).thenReturn(orgList);

        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("name","Enthaze");

        Set<JSONObject> jsonObjects = organizationService.fetchOrganizationByCriteria(criteria);

        Assert.assertEquals(1, jsonObjects.size());

    }

    @Test
    public void testOrganizationByTicketSubject(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",101);
        jsonObject1.put("name","Enthaze");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",102);
        jsonObject2.put("name","MegaCorp");

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonObject1);
        orgList.add(jsonObject2);

        JSONObject ticket1 = new JSONObject();
        ticket1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        ticket1.put("subject","A Catastrophe in Korea (North)");
        ticket1.put("organization_id",101);

        JSONObject ticket2 = new JSONObject();
        ticket2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        ticket2.put("subject","A Catastrophe in Micronesia");
        ticket2.put("organization_id",112);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        when(storage.getOrganizationList()).thenReturn(orgList);
        when(storage.getTicketList()).thenReturn(ticketList);

        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("name","Enthaze");

        Set<JSONObject> jsonObjects = organizationService.fetchOrganizationByCriteria(criteria);

        Assert.assertEquals("A Catastrophe in Korea (North)", jsonObjects.iterator().next().get("ticket_subject"));

    }

    @Test
    public void testOrganizationByUserName(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",101);
        jsonObject1.put("name","Enthaze");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",102);
        jsonObject2.put("name","MegaCorp");

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonObject1);
        orgList.add(jsonObject2);

        JSONObject ticket1 = new JSONObject();
        ticket1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        ticket1.put("subject","A Catastrophe in Korea (North)");
        ticket1.put("organization_id",101);

        JSONObject ticket2 = new JSONObject();
        ticket2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        ticket2.put("subject","A Catastrophe in Micronesia");
        ticket2.put("organization_id",112);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",22);
        jsonUser1.put("name","Ingrid Wagner");
        jsonUser1.put("organization_id",101);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",44);
        jsonUser2.put("name","Cross Barlow");
        jsonUser2.put("organization_id",122);

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        when(storage.getOrganizationList()).thenReturn(orgList);
        when(storage.getTicketList()).thenReturn(ticketList);
        when(storage.getUserList()).thenReturn(userList);

        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("name","Enthaze");

        Set<JSONObject> jsonObjects = organizationService.fetchOrganizationByCriteria(criteria);

        Assert.assertEquals("Ingrid Wagner", jsonObjects.iterator().next().get("user_name"));

    }

    @Test
    public void testOrganizationIntegratingUserAndTicket(){

        JsonObjectStorage storage = mock(JsonObjectStorage.class);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",101);
        jsonObject1.put("name","Enthaze");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("_id",102);
        jsonObject2.put("name","MegaCorp");

        List<JSONObject> orgList = new ArrayList<>();
        orgList.add(jsonObject1);
        orgList.add(jsonObject2);

        JSONObject ticket1 = new JSONObject();
        ticket1.put("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        ticket1.put("subject","A Catastrophe in Korea (North)");
        ticket1.put("organization_id",101);

        JSONObject ticket2 = new JSONObject();
        ticket2.put("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
        ticket2.put("subject","A Catastrophe in Micronesia");
        ticket2.put("organization_id",112);

        List<JSONObject> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        JSONObject jsonUser1 = new JSONObject();
        jsonUser1.put("_id",22);
        jsonUser1.put("name","Ingrid Wagner");
        jsonUser1.put("organization_id",101);

        JSONObject jsonUser2 = new JSONObject();
        jsonUser2.put("_id",44);
        jsonUser2.put("name","Cross Barlow");
        jsonUser2.put("organization_id",122);

        List<JSONObject> userList = new ArrayList<>();
        userList.add(jsonUser1);
        userList.add(jsonUser2);

        when(storage.getOrganizationList()).thenReturn(orgList);
        when(storage.getTicketList()).thenReturn(ticketList);
        when(storage.getUserList()).thenReturn(userList);

        OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
        organizationService.setJsonObjectStorage(storage);

        Map<String, String> criteria = new HashMap<>();
        criteria.put("name","Enthaze");

        Set<JSONObject> jsonObjects = organizationService.fetchOrganizationByCriteria(criteria);

        Assert.assertEquals("Ingrid Wagner", jsonObjects.iterator().next().get("user_name"));
        Assert.assertEquals("A Catastrophe in Korea (North)", jsonObjects.iterator().next().get("ticket_subject"));

    }
}

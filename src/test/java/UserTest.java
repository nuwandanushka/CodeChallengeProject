
import com.java.codeChallenge.service.impl.UserServiceImpl;
import com.java.codeChallenge.storage.JsonObjectStorage;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    @Test
    public void testUserById(){
        /*Map<String, String> criteria = new HashMap<>();
        criteria.put("_id","2");

        Set<JSONObject> fetchList = new HashSet<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("_id","2");

        JSONObjectModel objectModel= new JSONObjectModel();
        objectModel.setId("2");
        objectModel.setUserJsonObject(jsonObject);

        List<JSONObjectModel> userList = new ArrayList<>();
        userList.add(objectModel);

        fetchList.add(jsonObject);

        when(JsonObjectStorage.getUserList()).thenReturn(userList);

        when(JsonObjectStorage.getOrganizationList()).thenReturn(userList);*/


    }
}

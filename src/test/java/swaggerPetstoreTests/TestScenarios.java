package swaggerPetstoreTests;

import api.AssertionsClass;
import api.APIActions;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import modules.model.UsersData;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;


public class TestScenarios extends AssertionsClass {
    APIActions action;



    @DataProvider(name = "testData1")
    public static Object[][] testData() {
        return new Object[][]{
                {"hussienthabet", "hussien","thabet","hussien@gmail.com","45678"},

        };
    }

    @Test(priority = 1)
    public void creatingUser() {

        UsersData data = new UsersData();
        data.setUsername("alaahussien");
        data.setFirstName("alaa");
        data.setUserLastName("hussien");
        data.setEmail("alaa@gmail.com");
        data.setPassword("1234567");


        action= new APIActions("http://localhost:8080/api/v3") ;
        action.requestWithoutParameters(data,ContentType.JSON);
        Response response = action.response("user", Method.POST);
        response.getBody().asString();

        response.then().
                extract().response();

        printResponse(response);
        checkResponseHttpStatusCode(response,200);
    }

    @Test(priority = 2)
    public void getUserByUserName() {

        HashMap<String,String> data=
                new HashMap<String,String>();
        data.put("username","alaahussien");


        action= new APIActions("http://localhost:8080/api/v3") ;
        action.requestWithPathParameters(data);

        Response response = action.response("user/{username}", Method.GET);
        response.getBody().asString();

        printResponse(response);
        checkResponseHttpStatusCode(response,200);
    }

    @Test(dataProvider = "testData1",priority = 3)
    public void updateUserByUserName(String userName, String firstName, String lastName, String email,String password
    ) {
        JSONObject dataObject = new JSONObject();

        dataObject.put("username", userName);
        dataObject.put("firstName", firstName);
        dataObject.put("lastName", lastName);
        dataObject.put("email", email);
        dataObject.put("password", password);

        HashMap<String,String> data=
                new HashMap<String,String>();
        data.put("username","alaahussien");

        action= new APIActions("http://localhost:8080/api/v3") ;
        action.requestWithPathParameterAndSendBody(data,dataObject,ContentType.JSON);

        Response response = action.response("user/{username}", Method.PUT);
        response.getBody().asString();

        printResponse(response);
        checkResponseHttpStatusCode(response,200);
    }

    @Test(priority = 4)
    public void deleteUserByUserName() {

        HashMap<String,String> data=
                new HashMap<String,String>();
        data.put("username","hussienthabet");


        action= new APIActions("http://localhost:8080/api/v3") ;
        action.requestWithPathParameters(data);

        Response response = action.response("user/{username}", Method.DELETE);
        response.getBody().asString();

        printResponse(response);
        checkResponseHttpStatusCode(response,200);
    }
}

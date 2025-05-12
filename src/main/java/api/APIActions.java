package api;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modules.model.UsersData;
import org.json.simple.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class APIActions {

    RequestSpecification baseRequest;
    RequestSpecification request;
    Response response;

    /**
     * base URL request
     * @param url
     */

    public APIActions(String url) {

        baseRequest = given().baseUri(url).and();

    }

    /**
     * send request with parameters
     * @param data
     * @return
     */
    public RequestSpecification requestWithParameters(HashMap<String, String> data) {

        request = baseRequest.params(data);
        return request;

    }
    /**
     * send request without parameters
     * @param data
     *
     */
    public void requestWithoutParameters(UsersData data, ContentType type ) {

        request = baseRequest
                .contentType(type).body(data);

    }

    /**
     * send request with path parameters
     * @param data
     * @return
     */

    public RequestSpecification requestWithPathParameters(HashMap<String, String> data) {

        request = baseRequest.pathParams(data);
        return request;

    }

    /**
     * send request with Query parameters
     * @param data
     * @return
     */

    public RequestSpecification requestWithQueryParameters(HashMap<String, String> data) {
        request = baseRequest.queryParams(data);
        return request;

    }

    /**
     * send request with Query parameters, headers and body
     * @param data
     * @param object
     * @param type
     * @return
     */

    public RequestSpecification requestWithQueryParameterAndSendBody(HashMap<String, String> data, JSONObject object, ContentType type) {

        request = requestWithQueryParameters(data).contentType(type)
                .body(object.toJSONString());
        return request;

    }

    /**
     * send request with Path parameters, headers and body
     *
     * @param data
     * @param object
     * @param type
     */

    public void requestWithPathParameterAndSendBody(HashMap<String, String> data, JSONObject object, ContentType type) {
        request = requestWithPathParameters(data).contentType(type).
                body(object.toJSONString());
    }

    /**
     * send request with parameters, headers and body
     * @param data
     * @param object
     * @param type
     * @return
     */

    public RequestSpecification requestWithParameterAndSendBody(HashMap<String, String> data, JSONObject object, ContentType type) {
        request = requestWithParameters(data).contentType(type)
                .body(object.toJSONString());

        return request;
    }

    /**
     * send method and caption response
     * @param path
     * @param method
     * @return
     */

    public Response response(String path, Method method) {
        response = request.when().request(method, path);

        return response;
    }

}


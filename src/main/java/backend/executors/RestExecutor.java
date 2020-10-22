package backend.executors;

import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RestExecutor {

    static final Logger logger = Logger.getLogger(RestExecutor.class.getName());
    public static Response delete(String url){
        Response response = given().delete(url);
        logger.info(url+" Response"+response.asString());
        return response;
    }

    public static Response create(String url, String body){
        Map<String,String> headers = new HashMap<>();
        headers.put("content-type", "backend/application/json");
        logger.info("Request for "+url+body);
        Response response = given().headers(headers).body(body).post(url);
        logger.info("Response for "+url+response.asString());
        return response;

    }

    public static Response update(String url, Map paramMap){
        Response response = given().params(paramMap).post(url);
        logger.info("Response for "+url+response.asString());
        return response;
    }

    public static Response get(String url){
        Response response = given().get(url);
        logger.info("Response for "+url+response.asString());
        return response;
    }
}

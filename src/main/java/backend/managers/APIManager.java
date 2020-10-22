package backend.managers;

import backend.application.Constants;
import backend.executors.RestExecutor;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIManager {

    public static String getPets(List<String> statusList){
        String getUrl = Constants.petStoreEndpoint+Constants.findByStatusEndpoint;
        String status  ="";
        for(String s: statusList){
            status = String.join("&",status,String.format(Constants.statusParam,s));
        }
        Response response = RestExecutor.get(getUrl+status.replaceFirst("&",""));
        Assert.assertEquals(200,response.statusCode());
        return response.asString();
    }

    public static String createPet(String body) {
        Response response = RestExecutor.create(Constants.petStoreEndpoint,body);
        Assert.assertEquals(200,response.statusCode());
        return response.asString();

    }

    public static String getPetById(String id){
        Response response = RestExecutor.get(Constants.petStoreEndpoint+String.format(Constants.id,id));
        Assert.assertEquals(200,response.statusCode());
        return response.asString();
    }

    public static String updatePet(String id, String status) {
        Map<String,String> paramMap = new HashMap();
        paramMap.put("status",status);
        Response response = RestExecutor.update(Constants.petStoreEndpoint+String.format(Constants.id,id),paramMap);
        Assert.assertEquals(200,response.statusCode());
        return response.asString();
    }

    public static String deletPet(String id) {
        Response response = RestExecutor.delete(Constants.petStoreEndpoint+String.format(Constants.id,id));
        Assert.assertEquals(200,response.statusCode());
        return response.asString();
    }
}

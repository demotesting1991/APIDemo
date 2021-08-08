package APITest;

import Resources.Constants;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GoogleGetTest {
    public static Properties prp;

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void getGoogleAPI(){
//        RestAssured.baseURI = prp.getProperty(Constants.HOST);
        RestAssured.baseURI = System.getProperty("Host");
        Response response = given().log().all().
                queryParam(Constants.KEY,prp.getProperty("Key")).
                queryParam("place_id",prp.getProperty(Constants.PLACEID)).
        when().
                get("/maps/api/place/get/json").
        then().log().all().
               assertThat().statusCode(200).extract().response();

        String responseString = response.asString();
        JsonPath jp = new JsonPath(responseString);
        Assert.assertEquals("Frontline house",jp.getString("name"));
        Assert.assertTrue(jp.getString("phone_number").contains("(+91"));
    }
}

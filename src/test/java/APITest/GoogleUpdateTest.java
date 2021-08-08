package APITest;

import Resources.Constants;
import Resources.Payload;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleUpdateTest
{

    public static Properties prp;

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googlePut() throws IOException, ParseException {
        RestAssured.baseURI = prp.getProperty(Constants.HOST);
        JSONObject payload = Utility.getJsonObject("/src/main/java/APITest/update.json");
        payload.put("place_id",prp.getProperty(Constants.PLACEID));
        Response res = given().log().all().
                queryParam(Constants.KEY,prp.getProperty("Key")).
                queryParam("place_id",prp.getProperty(Constants.PLACEID)).
                body(payload).
                when().
                put("/maps/api/place/update/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).
                extract().response();

        String resString = res.asString();
        JsonPath js = new JsonPath(resString);
        Assert.assertTrue(js.getString("msg").contains("successfully"));
    }
}

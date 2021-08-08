package APITest;

import Resources.Constants;
import Resources.Payload;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoogleDeleteTest {
    public static Properties prp;

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googleDelete(){
        RestAssured.baseURI = prp.getProperty(Constants.HOST);
        Map<String, String> map = new HashMap<>();
        map.put("place_id",prp.getProperty(Constants.PLACEID));
        Response res = given().log().all().
                queryParam(Constants.KEY,prp.getProperty("Key")).
                body(map).
                when().
                delete("/maps/api/place/delete/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).and().
                body(Constants.STATUS,equalTo("OK")).
                extract().response();

       System.out.println(map+" got deleted successfully.");
    }
}

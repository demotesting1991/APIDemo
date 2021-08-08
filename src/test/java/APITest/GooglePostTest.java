package APITest;

import static io.restassured.RestAssured.given;

import Resources.Constants;
import Resources.Payload;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Properties;

public class GooglePostTest
{

    public static Properties prp;

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googlePost() throws IOException {
//        RestAssured.baseURI = prp.getProperty(Constants.HOST);
        RestAssured.baseURI = System.getProperty("Host");
        Response res = given().
                //header("Authorization","Basic 54765868798676uytdjhgghgchxfjhgfdx").
//                auth().oauth2("dsfdghgfdfs").
                        queryParam(Constants.KEY,prp.getProperty("Key")).
                body(Payload.googlePostPayload()).
                when().
                post("/maps/api/place/add/json").
                then().
                assertThat().statusCode(200).contentType(ContentType.JSON).and().
                body(Constants.STATUS,equalTo("OK")).
                extract().response();

        //String sessionId = res.getCookies().get("JSESSIONID");

        //given().cookie("JSESSIONID",sessionId).body("").post("");

        String resString = res.asString();
        JsonPath js = new JsonPath(resString);
        String placeId = js.getString("place_id");
        System.out.println("Place created with ID"+placeId);
        prp.setProperty(Constants.PLACEID,placeId);
        Utility.setPropertyFile(prp);
    }
}

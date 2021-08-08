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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class GoogleDeleteTest {
    public static Properties prp;
    private String pId = null;

    public GoogleDeleteTest(String pId){
        this.pId = pId;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
        Collection<Object[]> params = new ArrayList<>(2);
        params.add(new Object[]{"xyzsadsfgdgsad"});
        params.add(new Object[]{"sajdjahfdjhaksj"});
        return params;
    }

    @BeforeClass
    public static void startup() throws IOException {
        prp = Utility.getPropertyFile();
    }

    @Test
    public void googleDelete(){
        RestAssured.baseURI = prp.getProperty(Constants.HOST);
//        RestAssured.baseURI = System.getProperty("Host");  needed for jenkins
        Map<String, String> map = new HashMap<>();
        map.put("place_id",pId);
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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class baseClass {

    protected String boardName="BoardFresh";
    protected String key ="0697ace29da135af1009cc535346c753";
    protected String token="d3315e4c7c57e824f9af077fb911ceb3993d906e35e98cef035a6985729cbbf7";
    protected String boardID;
    protected RequestSpecification request;

    @BeforeClass(alwaysRun = true)
    public void setUp()
    {
        RestAssured.baseURI="https://api.trello.com/1";
        request = given()
                .queryParam("key", key)
                .queryParam("token", token)
                .contentType(ContentType.JSON);
                request.log().all();

    }

    /*@AfterClass
    public void tearDown()
    {
        //Deleting the board created in Test
        RequestSpecification request = given()
                .queryParam("key", key)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .pathParam("id", boardID);
                request.log().all();

        Response response = request.when().delete("/boards/{id}");

    }*/

}

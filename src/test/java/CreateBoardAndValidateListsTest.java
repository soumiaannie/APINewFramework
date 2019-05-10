import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;


public class CreateBoardAndValidateListsTest extends baseClass {

    String boardName="BoardFresh";

    @Test
    public void createBoard()
    {

        request.queryParam("name", boardName);
        Response response = request.when().post(Constants.createBoardResource);

        Assert.assertEquals(response.getStatusCode(), 200);


        HashMap<String, String> result = response.then().extract().body().jsonPath().get();
        boardID=result.get("id");
        System.out.println(boardID);
        String boardNameActual = result.get("name");
        Assert.assertEquals(boardNameActual, boardName);

        //SCHEMA VALIDATION
        response.then().body(matchesJsonSchemaInClasspath("JsonScema.json"));

        //HAMCREST ASSERTION
        response.then().body("name", equalTo(boardName));
        response.then().body("prefs.background", equalTo("blue"));

    }

    @Test
    public void getDefaultListsFromBoard()
    {
        //Creating the Expected Results
        ArrayList<String> expectedListNames = new ArrayList<String>();
        expectedListNames.add("To Do");
        expectedListNames.add("Doing");
        expectedListNames.add("Done");

        Set<String> expectedListAttributes= new HashSet<String>();
        expectedListAttributes.add("subscribed");
        expectedListAttributes.add("idBoard");
        expectedListAttributes.add("pos");
        expectedListAttributes.add("name");
        expectedListAttributes.add("closed");
        expectedListAttributes.add("id");
        expectedListAttributes.add("softLimit");


        request.pathParam("boardId", boardID);
        Response response=request.when().get(Constants.getBoardsList);

        response.then().body("[0].name", equalTo("To Do"));

        Assert.assertEquals(200, response.getStatusCode());

        ArrayList<HashMap<String, String>> actualBoardLists = new ArrayList<HashMap<String, String>>();
        actualBoardLists=response.jsonPath().get();

        ArrayList<String> actualListNames = new ArrayList<String>();
        Set<String> actualListAttributes = new HashSet<String>();


        for(int i=0; i<actualBoardLists.size(); i++) {
            actualListNames.add(actualBoardLists.get(i).get("name"));
            actualListAttributes=actualBoardLists.get(i).keySet();

        }

        Assert.assertTrue(actualListNames.containsAll(expectedListNames));
        Assert.assertTrue(actualListAttributes.containsAll(expectedListAttributes));

    }
}

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.*;

import java.util.HashMap;

public class ValidateCard extends baseClass {

    String listID="";
    String cardID="";
    String cardName="myTestCard";
    CommonFunctions commonFunctions = new CommonFunctions();


    @Test
    public void createCard()
    {
        listID = commonFunctions.createList("myTestList", commonFunctions.createBoard("myTestBoard"));

        request.queryParam("idList", listID)
                .queryParam("keepFromSource", "all").queryParam("name", cardID);


                Response response = request.when().post(Constants.createCardResource);


        HashMap<String, String> result = response.then().extract().body().jsonPath().get();
        cardID=result.get("id");
        System.out.println(cardID);

    }

    @Test
    public void updateCard()
    {

    }

    @Test
    public void deleteCard()
    {

    }
}

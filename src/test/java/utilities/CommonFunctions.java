package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CommonFunctions {
    String boarID;
    String listName;
    String listID;

    public String createBoard(String boardName)
    {
         RequestSpecification request = given()
                .queryParam("key", "0697ace29da135af1009cc535346c753")
                .queryParam("token", "97201f0259e3faff9858b18d8afd6acbc63549b3037cfd01eca2f88fb183ad7a")
                .contentType(ContentType.JSON).queryParam("name", boardName);

         Response response = request.when().post(Constants.createBoardResource);

        HashMap<String, String> result = response.then().extract().body().jsonPath().get();
        boarID=result.get("id");
        return boarID;

    }



    public String createList(String listName, String boarID)
    {
        RequestSpecification request = given()
                .queryParam("key", "0697ace29da135af1009cc535346c753")
                .queryParam("token", "97201f0259e3faff9858b18d8afd6acbc63549b3037cfd01eca2f88fb183ad7a")
                .contentType(ContentType.JSON).queryParam("name", listName).queryParam("idBoard", boarID);

        Response response = request.when().post(Constants.createListResource);

        HashMap<String, String> result = response.then().extract().body().jsonPath().get();
        listID=result.get("id");
        return listID;

    }
}

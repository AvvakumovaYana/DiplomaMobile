package helpers.api.trello;

import models.BoardModel;
import models.ListModel;

import static io.restassured.RestAssured.given;
import static specs.HttpSpec.requestSpec;
import static specs.HttpSpec.responseSpec;

public class Lists {
    public ListModel createList(String name, BoardModel board) {
        return given(requestSpec)
                .when()
                .post("/lists?name=" + name + "&idBoard=" + board.getId())
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(ListModel.class);
    }
}

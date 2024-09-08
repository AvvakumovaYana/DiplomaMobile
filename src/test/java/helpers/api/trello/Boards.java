package helpers.api.trello;

import models.BoardModel;
import models.ListModel;

import static io.restassured.RestAssured.given;
import static specs.HttpSpec.requestSpec;
import static specs.HttpSpec.responseSpec;

public class Boards {
    public BoardModel createBoard(String name) {
        return given(requestSpec)
                .when()
                .post("/boards/?name=" + name + "&defaultLists=false")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(BoardModel.class);
    }

    public void deleteBoard(String boardId) {
        given(requestSpec)
                .when()
                .delete("/boards/" + boardId)
                .then()
                .spec(responseSpec)
                .statusCode(200);
    }

    public void deleteBoard(BoardModel board) {
        deleteBoard(board.getId());
    }

    public ListModel[] getLists(BoardModel board) {
        return given(requestSpec)
                .when()
                .get("/boards/" + board.getId() + "/lists")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(ListModel[].class);
    }
}

package tests.mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.mobile.BoardsScreen;

import java.time.LocalDateTime;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Аввакумова Яна")
@Tag("mobile")
public class BoardTests extends TestBase {
    private final BoardsScreen boardsScreen = new BoardsScreen();

    @Test
    @DisplayName("Проверка создания колонки")
    void addListTest() {
        String newListName = "List name " + LocalDateTime.now();

        step("-", () -> {
            boardsScreen.clickBoardRow();
        });
        step("-", () -> {
            boardsScreen.clickAddListButton();
        });
        step("-", () -> {
            boardsScreen.setListName(newListName);
        });
        step("-", () -> {
            boardsScreen.clickSaveButton();
        });
        step("Проверяем что колонка присутствует", () -> {
            Thread.sleep(1000);
            var lists = boardsApi.getLists(board);
            assertThat(lists).anyMatch(l -> l.getName().equals(newListName));
        });
    }

    @Test
    @DisplayName("Проверка удаления колонки")
    void deleteListTest() {
        String newListName = "List name " + LocalDateTime.now();

        step("-", () -> {
            listsApi.createList(newListName, board);
        });
        step("-", () -> {
            boardsScreen.clickBoardRow();
        });
        step("-", () -> {
            boardsScreen.clickListMenuButton();
        });
        step("-", () -> {
            boardsScreen.clickArchiveListMenuButton();
        });
        step("Проверяем что колонка отсутствует", () -> {
            Thread.sleep(1000);
            var lists = boardsApi.getLists(board);
            assertThat(lists).noneMatch(l -> l.getName().equals(newListName));
        });
    }


    @Test
    @DisplayName("Проверка удаления карточки")
    void deleteCardTest() {
        var list = step("-", () -> {
            return listsApi.createList("List name " + LocalDateTime.now(), board);
        });
        var card = step("-", () -> {
            return cardsApi.createCard("Card name " + LocalDateTime.now(), list);
        });
        step("-", () -> {
            boardsScreen.clickBoardRow();
        });
        step("-", () -> {
            boardsScreen.clickFirstCard();
        });
        step("-", () -> {
            boardsScreen.clickCardMenuButton(card);
        });
        step("-", () -> {
            boardsScreen.clickDeleteCardButton();
        });
        step("-", () -> {
            boardsScreen.clickApproveDeleteCardButton();
        });
        step("Проверяем что карточка отсутствует", () -> {
            Thread.sleep(1000);
            var newCard = cardsApi.getCard(card);
            assertThat(newCard).isNull();
        });
    }
}

package tests.mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.mobile.BoardsScreen;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Аввакумова Яна")
@Tag("mobile")
public class BoardTests extends TestBase {
    private final BoardsScreen boardsScreen = new BoardsScreen();

    @Test
    @DisplayName("Проверка создания колонки на доске")
    void addListTest() {
        String newListName = "List name " + LocalDateTime.now();

        step("Нажимаем на доску", () -> {
            boardsScreen.clickBoardRow();
        });
        step("Нажимаем на кнопку создания колонки", () -> {
            boardsScreen.clickAddListButton();
        });
        step("Вводим имя колонки", () -> {
            boardsScreen.setListName(newListName);
        });
        step("Нажимаем на кнопку сохранения", () -> {
            boardsScreen.clickSaveButton();
        });
        step("Проверяем через API, что колонка присутствует на доске", () -> {
            sleep(1000);
            var lists = boardsApi.getLists(board);
            assertThat(lists).anyMatch(l -> l.getName().equals(newListName));
        });
    }

    @Test
    @DisplayName("Проверка удаления колонки с доски")
    void deleteListTest() {
        String newListName = "List name " + LocalDateTime.now();

        step("Создаем колонку через API", () -> {
            listsApi.createList(newListName, board);
        });
        step("Нажимаем на доску", () -> {
            boardsScreen.clickBoardRow();
        });
        step("Нажимаем на кнопку меню колонки", () -> {
            boardsScreen.clickListMenuButton();
        });
        step("Нажимаем на кнопку архивирования колонки", () -> {
            boardsScreen.clickArchiveListMenuButton();
        });
        step("Проверяем через API, что колонка отсутствует на доске", () -> {
            sleep(1000);
            var lists = boardsApi.getLists(board);
            assertThat(lists).noneMatch(l -> l.getName().equals(newListName));
        });
    }

    @Test
    @DisplayName("Проверка удаления карточки из колонки")
    void deleteCardTest() {
        var list = step("Создаем колонку через API", () -> {
            return listsApi.createList("List name " + LocalDateTime.now(), board);
        });
        var card = step("Создаем карточку через API", () -> {
            return cardsApi.createCard("Card name " + LocalDateTime.now(), list);
        });
        step("Нажимаем на доску", () -> {
            boardsScreen.clickBoardRow();
        });
        step("Нажимаем на карточку", () -> {
            boardsScreen.clickFirstCard();
        });
        step("Нажимаем на кнопку меню карточки", () -> {
            boardsScreen.clickCardMenuButton(card);
        });
        step("Нажимаем на кнопку удаления карточки", () -> {
            boardsScreen.clickDeleteCardButton();
        });
        step("Подтверждаем удаление", () -> {
            boardsScreen.clickApproveDeleteCardButton();
        });
        step("Проверяем через API, что карточка отсутствует", () -> {
            sleep(1000);
            var newCard = cardsApi.getCard(card);
            assertThat(newCard).isNull();
        });
    }
}

package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.mobile.credentials.ApplicationCredentials;
import drivers.AppiumDriver;
import drivers.BrowserstackDriver;
import helpers.api.trello.Boards;
import helpers.api.trello.Cards;
import helpers.api.trello.Lists;
import helpers.mobile.AttachMobile;
import io.qameta.allure.selenide.AllureSelenide;
import models.BoardModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.mobile.AuthorizationScreen;
import pages.mobile.BoardsScreen;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestBase {
    private static final ApplicationCredentials applicationCredentials = ConfigFactory.create(ApplicationCredentials.class, System.getProperties());
    private static final AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    private static final BoardsScreen boardsScreen = new BoardsScreen();

    protected final static Boards boardsApi = new Boards();
    protected final static Lists listsApi = new Lists();
    protected final static Cards cardsApi = new Cards();

    private static final String boardName = "Board for mobile test";
    protected static BoardModel board;

    @BeforeAll
    static void beforeAll() {
        if (System.getProperty("device") == null)
            System.setProperty("device", "browserstack");

        Configuration.browserSize = null;
        Configuration.browser = switch (System.getProperty("device")) {
            case "browserstack" -> BrowserstackDriver.class.getName();
            case "emulated", "real" -> AppiumDriver.class.getName();
            default -> throw new IllegalStateException("Unexpected value: " + System.getProperty("device"));
        };

        board = boardsApi.createBoard(boardName);
    }

    @AfterAll
    static void DeleteBoard() {
        boardsApi.deleteBoard(board);
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        login();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);

        AttachMobile.pageSource();
        closeWebDriver();

        if (Objects.equals(System.getProperty("device"), "browserstack"))
            AttachMobile.addVideo(sessionId);
    }

    private static void login() {
        open();

        step("Нажимаем на кнопку 'Log in'", () -> {
            authorizationScreen.clickLogInButton();
        });
        step("Нажимаем на кнопку 'SIGN IN WITH EMAIL'", () -> {
            authorizationScreen.clickSignInWithEmailButton();
        });

        step("Нажимаем на кнопку 'Add another account', если она есть", () -> {
            authorizationScreen.clickAddAnotherAccountButtonIfExists();
        });
        step("Заполняем поле 'Enter your email'", () -> {
            authorizationScreen.fillEnterYourEmailField(applicationCredentials.login());
        });
        step("Нажимаем на текст страницы, чтобы убрать подсказку для ввода", () -> {
            authorizationScreen.clickLogInToContinueLabel();
        });
        step("Нажимаем на кнопку 'Continue'", () -> {
            authorizationScreen.clickContinueButton();
        });

        step("Заполняем поле 'Enter password'", () -> {
            authorizationScreen.fillEnterPasswordField(applicationCredentials.password());
        });
        step("Нажимаем на кнопку 'Log in'", () -> {
            authorizationScreen.clickNextLogInButton();
        });
        step("Проверяем заголовок экрана Boards", () -> {
            boardsScreen.checkPageLabel();
        });
    }
}

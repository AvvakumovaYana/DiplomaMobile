package tests.mobile;

import config.mobile.credentials.ApplicationCredentials;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.mobile.AuthorizationScreen;
import pages.mobile.BoardsScreen;

import static io.qameta.allure.Allure.step;

@Owner("Аввакумова Яна")
@Tag("mobile")
public class AuthorizationTest extends TestBase {
    private static final ApplicationCredentials applicationCredentials = ConfigFactory.create(ApplicationCredentials.class, System.getProperties());
    private final AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    private final BoardsScreen boardsScreen = new BoardsScreen();

    @Test
    @AllureId("34130")
    @DisplayName("Проверка авторизации в приложении Trello")
    void loginTest() {
        step("Нажимаем на кнопку 'Log in'", () -> {
            authorizationScreen.clickLogInButton();
        });
        step("Нажимаем на кнопку 'SIGN IN WITH EMAIL'", () -> {
            authorizationScreen.clickSingInWithEmailButton();
        });

        step("Нажимаем на кнопку 'Add another account', если она есть", () -> {
            authorizationScreen.checkAndClickAddAnotherAccountButton();
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
            authorizationScreen.fillEnterPasswordButton(applicationCredentials.password());
        });
        step("Нажимаем на кнопку 'Log in'", () -> {
            authorizationScreen.clickNextLogInButton();
        });

        step("Проверяем заголовок экрана Boards", () -> {
            boardsScreen.checkPageLabel();
        });
    }
}



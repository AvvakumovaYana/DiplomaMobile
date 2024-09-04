package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class AuthorizationScreen extends BaseScreen {
    private final SelenideElement logInButton = $(xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button"));
    private final SelenideElement singInWithEmailButton = $(id("com.trello:id/email"));
    private final SelenideElement enterYourEmailField = $(xpath("//android.widget.EditText[@resource-id=\"username\"]"));
    private final SelenideElement logInToContinueLabel = $(xpath("//*[@text=\"Log in to continue\"]"));
    private final SelenideElement continueButton = $(xpath("//android.widget.Button[@resource-id=\"login-submit\"]"));
    private final SelenideElement enterPasswordButton = $(xpath("//android.widget.EditText[@resource-id=\"password\"]"));
    private final SelenideElement nextLogInButton = $(xpath("//android.widget.Button[@resource-id=\"login-submit\"]"));
    private final SelenideElement trelloLogo = $(xpath("//android.widget.Image[@text=\"Trello\"]"));

    public void clickLogInButton() {
        logInButton.click();
    }

    public void clickSingInWithEmailButton() throws InterruptedException {
        singInWithEmailButton.click();
        ensureElement(trelloLogo, 20);
    }

    public void fillEnterYourEmailField(String login) {
        enterYourEmailField.click();
        enterYourEmailField.sendKeys(login);
    }

    public void clickLogInToContinueLabel() {
        logInToContinueLabel.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void fillEnterPasswordButton(String password) throws InterruptedException {
        ensureElement(enterPasswordButton, 10);
        enterPasswordButton.sendKeys(password);
    }

    public void clickNextLogInButton() {
        nextLogInButton.click();
    }

    public void checkAndClickAddAnotherAccountButton() {
        var list = $$(xpath("//android.widget.Button[@resource-id=\"navigate-to-login-prompt\"]"));
        if (!list.isEmpty())
            list.first().click();
    }
}

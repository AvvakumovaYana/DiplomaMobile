package pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.CardModel;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class BoardsScreen {
    private final SelenideElement hamburgerButton = $(xpath("//android.widget.ImageButton[@content-desc=\"Open drawer\"]"));
    private final SelenideElement pageLabel = $(xpath("//android.widget.TextView[@text=\"Boards\"]"));
    private final SelenideElement boardRow = $(xpath("//android.view.View[@resource-id=\"boardRow\"]"));

    private final SelenideElement addListButton = $(xpath("//android.widget.Button[@resource-id=\"com.trello:id/add_list_button\"]"));
    private final SelenideElement listNameField = $(xpath("//android.widget.EditText[@resource-id=\"com.trello:id/list_name_edit_text\"]"));
    private final SelenideElement listMenu = $(xpath("//android.widget.ImageView[@content-desc=\"List actions\"]"));
    private final SelenideElement archiveListMenuButton = $(xpath("//android.widget.TextView[@resource-id=\"com.trello:id/title\" and @text=\"Archive list\"]"));

    private final SelenideElement firstCardButton = $(xpath("//android.widget.TextView[@resource-id=\"com.trello:id/cardText\"]"));
    private final SelenideElement deleteCardButton = $(xpath("//android.widget.TextView[@text=\"Delete\"]"));
    private final SelenideElement approveDeleteCardButton = $(xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button"));

    private final SelenideElement saveButton = $(xpath("//android.widget.Button[@content-desc=\"Save\"]"));

    public void checkPageLabel() {
        hamburgerButton.shouldBe(Condition.visible, Duration.ofSeconds(30));
        pageLabel.shouldHave(text("Boards"));
    }

    public void clickBoardRow() {
        boardRow.click();
    }

    public void clickAddListButton() {
        addListButton
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickListMenuButton() {
        listMenu
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickArchiveListMenuButton() {
        archiveListMenuButton
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void setListName(String name) {
        listNameField.sendKeys(name);
    }

    public void clickFirstCard() {
        firstCardButton
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickCardMenuButton(CardModel card) {
        var selector = "//android.view.View[@content-desc=\"Viewing card " + card.getName() + "\"]/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.Button";
        $(xpath(selector))
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickDeleteCardButton() {
        deleteCardButton
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickApproveDeleteCardButton() {
        approveDeleteCardButton
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }
}

package pages.mobile;

import com.codeborne.selenide.SelenideElement;
import models.CardModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class BoardsScreen extends BaseScreen {
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

    public void checkPageLabel() throws InterruptedException {
        ensureElement(hamburgerButton, 30);
        pageLabel.shouldHave(text("Boards"));
    }

    public void clickBoardRow() {
        boardRow.click();
    }

    public void clickAddListButton() throws InterruptedException {
        ensureElement(addListButton, 30);
        addListButton.click();
    }

    public void clickListMenuButton() throws InterruptedException {
        ensureElement(listMenu, 30);
        listMenu.click();
    }

    public void clickArchiveListMenuButton() throws InterruptedException {
        ensureElement(archiveListMenuButton, 30);
        archiveListMenuButton.click();
    }

    public void setListName(String name) {
        listNameField.sendKeys(name);
    }

    public void clickFirstCard() throws InterruptedException {
        ensureElement(firstCardButton, 30);
        firstCardButton.click();
    }

    public void clickCardMenuButton(CardModel card)  throws InterruptedException {
        var selector = "//android.view.View[@content-desc=\"Viewing card " + card.getName() + "\"]/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.Button";
        var cardMenuButton = $(xpath(selector));
        ensureElement(cardMenuButton, 30);
        cardMenuButton.click();
    }

    public void clickDeleteCardButton()  throws InterruptedException {
        ensureElement(deleteCardButton, 30);
        deleteCardButton.click();
    }

    public void clickApproveDeleteCardButton()  throws InterruptedException {
        ensureElement(approveDeleteCardButton, 30);
        approveDeleteCardButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }
}

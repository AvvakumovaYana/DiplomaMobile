package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class BoardsScreen extends BaseScreen {
    private final SelenideElement hamburgerButton = $(xpath("//android.widget.ImageButton[@content-desc=\"Open drawer\"]"));
    private final SelenideElement pageLabel = $(xpath("//android.widget.TextView[@text=\"Boards\"]"));

    public void checkPageLabel() throws InterruptedException {
        ensureElement(hamburgerButton, 30);
        pageLabel.shouldHave(text("Boards"));
    }
}

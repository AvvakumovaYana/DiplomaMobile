package pages.mobile;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class NotificationScreen extends BaseScreen {
    private final SelenideElement closeButton = $(xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button"));

    public void clickCloseButton() throws InterruptedException {
        ensureElement(closeButton, 30);
        closeButton.click();
    }
}

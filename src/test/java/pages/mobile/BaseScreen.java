package pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.sleep;

public class BaseScreen {

    protected static void ensureElement(SelenideElement element, int waitingPeriodInSeconds) throws InterruptedException {
        while (!(element.is(Condition.exist) && element.isDisplayed()) && waitingPeriodInSeconds > 0) {
            sleep(1000);
            waitingPeriodInSeconds--;
            System.out.println("Wait for " + waitingPeriodInSeconds);
        }
    }
}
